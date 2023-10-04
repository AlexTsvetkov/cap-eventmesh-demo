package com.sap.hanesbrand.service;

import cds.gen.outbounddeliveryservice.OutboundDelivery;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import com.sap.hanesbrand.client.dto.OutboundDeliveryEventDto;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import com.sap.hanesbrand.mapper.OutboundDeliveryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import static com.sap.hanesbrand.client.dto.OutboundDeliveryEventDto.OUTBOUND_DELIVERY;

@Slf4j
@RequiredArgsConstructor
@Component
public class DDMessageListener implements MessageListener {

    private final OutboundDeliveryS4Service outboundDeliveryS4Service;
    private final OutboundDeliveryMapper mapper;
    private final OutboundDeliveryDao outboundDeliveryRepository;

    private static final String SHIPMENT_CONFIRM = "2";
    private static final String STATUS_DESCRIPTION = "Shipment Confirmed";

    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                messageText = ((TextMessage) message).getText();
            } else if (message instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] data = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(data);
                messageText = new String(data);
            }

            OutboundDeliveryEventDto event = objectMapper.readValue(messageText, OutboundDeliveryEventDto.class);
            String deliveryDocument = event.getData().get(OUTBOUND_DELIVERY);
            String eventType = event.getType();
            if(eventType.contains("ConfirmShipment")){
                log.info("ConfirmShipment");
                OutboundDelivery outboundDelivery = outboundDeliveryRepository.selectOutboundDeliveryById(deliveryDocument);
                outboundDeliveryRepository.updateConfirmShipmentStatus(outboundDelivery.getDeliveryDocument(), SHIPMENT_CONFIRM, STATUS_DESCRIPTION);
            }
            if(eventType.contains("OutboundDelivery")){
                log.info("OutboundDelivery");
                OutboundDeliveryDto outboundDeliveryDto = outboundDeliveryS4Service.getOutboundDeliveryById(deliveryDocument);
                log.info("DDMessageListener: outboundDeliveryDto =" + outboundDeliveryDto.toString());
                OutboundDelivery outboundDelivery = mapper.s4DocumentToOutboundDelivery(outboundDeliveryDto.getDocument());
                outboundDeliveryRepository.saveOutboundDelivery(outboundDelivery);
            }
        } catch (Exception e) {
            log.error("DDMessageListener: --Cannot receive event: " + e.getMessage());
        }
    }
}
