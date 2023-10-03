package com.sap.hanesbrand.config.vcap;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties("vcap.services.hanesbrand-em.credentials")
public class EMVcapCredentials {

    @JsonProperty("xsappname")
    private String xsappname;

    @JsonProperty("namespace")
    private String namespace;

    @JsonProperty("messaging")
    private Map<String, Messaging> messaging;

    @Data
    public static class Messaging {
        @JsonProperty("oa2")
        private Oa2 oa2;
        @JsonProperty("protocol")
        private List<String> protocol;
        @JsonProperty("broker")
        private Broker broker;
        @JsonProperty("uri")
        private String uri;
    }

    @Data
    public static class Oa2 {
        @JsonProperty("clientid")
        private String clientId;
        @JsonProperty("clientsecret")
        private String clientSecret;
        @JsonProperty("tokenendpoint")
        private String tokenEndpoint;
        @JsonProperty("granttype")
        private String grantType;
    }

    @Data
    public static class Broker {
        @JsonProperty("type")
        private String type;
    }
}
