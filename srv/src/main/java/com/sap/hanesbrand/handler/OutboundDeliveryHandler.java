package com.sap.hanesbrand.handler;


import cds.gen.outbounddeliveryservice.*;
import com.sap.cds.Result;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.messaging.TopicMessageEventContext;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import com.sap.hanesbrand.client.dto.OutboundDeliveryEventDto;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import com.sap.hanesbrand.mapper.OutboundDeliveryMapper;
import com.sap.hanesbrand.service.OutboundDeliveryS4Service;
import com.sap.hanesbrand.util.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static com.sap.cds.ResultBuilder.selectedRows;

@Component
@Slf4j
@AllArgsConstructor
@ServiceName(OutboundDeliveryService_.CDS_NAME)
public class OutboundDeliveryHandler implements EventHandler {

    private final OutboundDeliveryS4Service outboundS4Service;
    private final OutboundDeliveryMapper mapper;
    private final OutboundDeliveryDao outboundDeliveryRepository;

    public final static String OUTBOUND_DELIVERY = "OutboundDelivery";
    private static final String SHIPMENT_CONFIRM = "2";
    private static final String STATUS_DESCRIPTION = "Shipment Confirmed";

    @After(event = {CqnService.EVENT_READ}, entity = OutboundDelivery_.CDS_NAME)
    public void afterOutboundCreated(CdsReadEventContext context, List<OutboundDelivery> outboundDeliveryList) {
        log.info("OutboundDeliveryHandler: afterOutboundCreated");
        outboundDeliveryList.forEach(outboundDelivery -> {
            if (outboundDelivery.getShipmentConfirmedDate() == null) {
                setCriticality(outboundDelivery, outboundDelivery.getSendToWMSDate());
            } else {
                outboundDelivery.setCriticalityCode(3);
            }
        });

        Result r = selectedRows(outboundDeliveryList).inlineCount(context.getResult().inlineCount()).result();
        context.setResult(r);
    }

    @On(service = "hanesbrand-em", event = "com/sap/hanesbrand/ce/sap/s4/beh/outbounddelivery/v1/OutboundDelivery/Created/v1")
    private void outboundEvent(TopicMessageEventContext outboundEventContext) {
        log.info("OutboundDeliveryEvent");
        try {
            OutboundDeliveryEventDto event = JsonUtils.outboundDeliveryMapper.readValue(outboundEventContext.getData(), OutboundDeliveryEventDto.class);
            String deliveryDocument = event.getData().get(OUTBOUND_DELIVERY);
            String eventType = event.getType();
            if (eventType.contains("OutboundDelivery")) {
                log.info("OutboundDelivery");
                OutboundDeliveryDto outboundDeliveryDto = outboundS4Service.getOutboundDeliveryById(deliveryDocument);
                log.info("DDMessageListener: outboundDeliveryDto =" + outboundDeliveryDto.toString());
                OutboundDelivery outboundDelivery = mapper.s4DocumentToOutboundDelivery(outboundDeliveryDto.getDocument());
                outboundDeliveryRepository.saveOutboundDelivery(outboundDelivery);
            }
            if (eventType.contains("ConfirmShipment")) {
                log.info("ConfirmShipment");
                OutboundDelivery outboundDelivery = outboundDeliveryRepository.selectOutboundDeliveryById(deliveryDocument);
                outboundDeliveryRepository.updateConfirmShipmentStatus(outboundDelivery.getDeliveryDocument(), SHIPMENT_CONFIRM, STATUS_DESCRIPTION);
            }
        } catch (Exception e) {
            log.error("DDMessageListener: --Cannot receive event: " + e.getMessage());
        }
    }

    public void setCriticality(OutboundDelivery outboundDelivery, LocalDate sendToWMSDate) {
        LocalDate today = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(sendToWMSDate, today);
        if (daysDifference > 1 && daysDifference <= 3) {
            outboundDelivery.setCriticalityCode(2);
        } else if (daysDifference >= 4) {
            outboundDelivery.setCriticalityCode(1);
        } else {
            outboundDelivery.setCriticalityCode(3);
        }
    }
    public void setDefaultCriticality(OutboundDelivery outboundDelivery){
        outboundDelivery.setCriticalityCode(1);
    }
}
