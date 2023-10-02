package com.sap.hanesbrand.handler;


import cds.gen.outbounddeliveryservice.OutboundDelivery;
import cds.gen.outbounddeliveryservice.OutboundDeliveryService_;
import cds.gen.outbounddeliveryservice.OutboundDelivery_;
import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CqnService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
@ServiceName(OutboundDeliveryService_.CDS_NAME)
public class OutboundDeliveryHandler implements EventHandler {

    @After(event = {CqnService.EVENT_READ, CqnService.EVENT_CREATE, CqnService.EVENT_UPSERT, CqnService.EVENT_UPDATE}, entity = OutboundDelivery_.CDS_NAME)
    public List<OutboundDelivery> afterOutboundCreated(EventContext context, List<OutboundDelivery> outboundDeliveryList) {
        log.info("OutboundDeliveryHandler: afterOutboundCreated");
        outboundDeliveryList.forEach(outboundDelivery -> setCriticality(outboundDelivery, outboundDelivery.getSendToWMSDate()));
        return outboundDeliveryList;
    }

    public void setCriticality(OutboundDelivery outboundDelivery, LocalDate sendToWMSDate) {
        LocalDate today = LocalDate.now();
        LocalDate date1DayAgo = today.minusDays(1);
        LocalDate date3DaysAgo = today.minusDays(3);
        LocalDate date5DaysAgo = today.minusDays(5);

        if (sendToWMSDate.isBefore(date1DayAgo)) {
            outboundDelivery.setCriticalityCode(1);
        }
        if (sendToWMSDate.isBefore(date3DaysAgo.minusDays(3))) {
            outboundDelivery.setCriticalityCode(3);
        }
        if (sendToWMSDate.isBefore(date5DaysAgo.minusDays(5))) {
            outboundDelivery.setCriticalityCode(5);
        }
        outboundDelivery.setCriticalityCode(0);
    }

}
