package com.sap.hanesbrand.handler;


import cds.gen.outbounddeliveryservice.OutboundDelivery;
import cds.gen.outbounddeliveryservice.OutboundDeliveryService_;
import cds.gen.outbounddeliveryservice.OutboundDelivery_;
import com.sap.cds.Result;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.sap.cds.ResultBuilder.selectedRows;

@Component
@Slf4j
@AllArgsConstructor
@ServiceName(OutboundDeliveryService_.CDS_NAME)
public class OutboundDeliveryHandler implements EventHandler {

    @After(event = {CqnService.EVENT_READ}, entity = OutboundDelivery_.CDS_NAME)
    public void afterOutboundCreated(CdsReadEventContext context, List<OutboundDelivery> outboundDeliveryList) {
        log.info("OutboundDeliveryHandler: afterOutboundCreated: " + outboundDeliveryList);

        outboundDeliveryList.forEach(outboundDelivery -> setCriticality(outboundDelivery, outboundDelivery.getSendToWMSDate()));
        Result r = selectedRows(outboundDeliveryList).inlineCount(context.getResult().inlineCount()).result();
        context.setResult(r);
    }

    public void setCriticality(OutboundDelivery outboundDelivery, LocalDate sendToWMSDate) {
        LocalDate today = LocalDate.now();
        long daysDifference = ChronoUnit.DAYS.between(sendToWMSDate, today);
        if (daysDifference == 1) {
            outboundDelivery.setCriticalityCode(1);
        }
        else if(daysDifference >= 1 && daysDifference <= 3) {
           outboundDelivery.setCriticalityCode(3);
        }
        else if(daysDifference < 5){
            outboundDelivery.setCriticalityCode(5);
        }
    }
}
