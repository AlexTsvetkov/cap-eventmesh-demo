package com.sap.hanesbrand.mapper;

import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import org.mapstruct.Mapper;

@Mapper
public interface OutboundDeliveryMapper {

    OutboundDeliveryEvent s4DocumentToOutboundDelivery(OutboundDeliveryDto.Document documentDeliveryDto);

    default OutboundDeliveryEvent createNegotiationProjectHeader() {
        return OutboundDeliveryEvent.create();
    }

}
