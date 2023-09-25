package com.sap.hanesbrand.service;

import com.sap.hanesbrand.client.S4HttpDestinationClientBasicAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.sap.hanesbrand.client.dto.*;

import java.text.MessageFormat;

@RequiredArgsConstructor
@Slf4j
@Service
public class S4Service {

    private static final String GET_HEADER_BY_ID_TEMPLATE = "/A_OutbDeliveryHeader({0})";
    private final S4HttpDestinationClientBasicAuth clientBasicAuth;

    public DocumentDeliveryDto getOutboundDeliveryById(String id){
       return clientBasicAuth.get(MessageFormat.format(GET_HEADER_BY_ID_TEMPLATE, id), DocumentDeliveryDto.class);
    }
}
