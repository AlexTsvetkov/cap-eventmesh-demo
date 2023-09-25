package com.sap.hanesbrand.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class S4HttpDestinationClientBasicAuth extends HttpDestinationClient {

    @Value("${spring.destination.name.s4Outbound}")
    private String S4_BASIC_DESTINATION_NAME;

    @Override
    protected String destinationName() {
        return S4_BASIC_DESTINATION_NAME;
    }
}