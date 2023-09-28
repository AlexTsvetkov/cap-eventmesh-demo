package com.sap.hanesbrand.controller;


import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OutboundDeliveryController {

    private final OutboundDeliveryDao outboundDeliveryRepository;
    private static final String SHIPMENT_CONFIRM = "Shipment Confirmed";

    @PostMapping("/confirmShipment/{id}")
    public void confirmShipment(@PathVariable String id) {
        log.info("OutboundDeliveryController: confirmShipment for: {} ", id);
        OutboundDeliveryEvent outboundDeliveryEvent = outboundDeliveryRepository.selectOutboundDeliveryById(id);
        outboundDeliveryRepository.updateStatus(outboundDeliveryEvent.getDeliveryDocument(), SHIPMENT_CONFIRM);
    }


}
