package com.sap.hanesbrand.mapper;

import cds.gen.outbounddeliveryservice.OutboundDelivery;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Duration;
import java.time.LocalTime;

@Mapper
public interface OutboundDeliveryMapper {


    OutboundDelivery s4DocumentToOutboundDelivery(OutboundDeliveryDto.Document documentDeliveryDto);

    default OutboundDelivery createNegotiationProjectHeader() {
        return OutboundDelivery.create();
    }

}
