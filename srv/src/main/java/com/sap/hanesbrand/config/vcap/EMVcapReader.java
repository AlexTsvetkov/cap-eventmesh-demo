package com.sap.hanesbrand.config.vcap;

public interface EMVcapReader {

    EMVcapCredentials.Oa2 getAmqpMessagingServiceCredentials();

    String getAmqpUri();

    String getEmNamespace();


}