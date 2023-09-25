package com.sap.hanesbrand.mapper;

import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import com.sap.hanesbrand.client.dto.DocumentDeliveryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OutboundDeliveryMapper {

    OutboundDeliveryEvent s4DocumentToOutboundDelivery(DocumentDeliveryDto.Document documentDeliveryDto);

    default OutboundDeliveryEvent createNegotiationProjectHeader() {
        return OutboundDeliveryEvent.create();
    }

}
