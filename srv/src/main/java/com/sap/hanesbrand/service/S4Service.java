package com.sap.hanesbrand.service;

import com.sap.hanesbrand.client.S4HttpDestinationClientBasicAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sap.hanesbrand.client.dto.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class S4Service {

    private static final String FORMAT = "?$format=json";
    private static final String GET_HEADER_BY_ID = "/A_OutbDeliveryHeader('%s')";

    private final S4HttpDestinationClientBasicAuth clientBasicAuth;

    public OutboundDeliveryDto getOutboundDeliveryById(String id){
        String entityPath = String.format(GET_HEADER_BY_ID, id);
            return clientBasicAuth.get(entityPath + FORMAT, OutboundDeliveryDto.class);
    }
}
