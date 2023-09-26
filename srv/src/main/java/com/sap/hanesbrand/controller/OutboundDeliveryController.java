package com.sap.hanesbrand.controller;


import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import com.sap.hanesbrand.mapper.OutboundDeliveryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OutboundDeliveryController {

    private final OutboundDeliveryMapper documentDeliveryMapper;
    private final OutboundDeliveryDao outboundDeliveryRepository;
    private static final String SHIPMENT_CONFIRM = "Shipment Confirmed";

    //Not needed at all, it's for local testing dto/mapper
    @PostMapping("/webhook/save-document")
    public void saveDocument(@RequestBody OutboundDeliveryDto outboundDeliveryDto) {
        OutboundDeliveryDto.Document document = outboundDeliveryDto.getDocument();
        OutboundDeliveryEvent outboundDeliveryEvent = documentDeliveryMapper.s4DocumentToOutboundDelivery(document);
        outboundDeliveryRepository.saveOutboundDelivery(outboundDeliveryEvent);
    }

    @PostMapping("/confirmShipment/{id}")
    public void confirmShipment(@PathVariable String id){
        OutboundDeliveryEvent outboundDeliveryEvent = outboundDeliveryRepository.selectOutboundDeliveryById(id);
        outboundDeliveryRepository.updateStatus(outboundDeliveryEvent.getDeliveryDocument(), SHIPMENT_CONFIRM);
    }


}
