package com.sap.hanesbrand.config.vcap;

import com.sap.hanesbrand.exception.VcapReaderException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter
@Component
public class EMVcapReaderImpl implements EMVcapReader {

    private final EMVcapCredentials emVcapCredentials;

    public static final String PROTOCOL_AMQP = "amqp10ws";
    public static final String PROTOCOL_HTTP = "httprest";
    public static final String TOKEN_ENDPOINT = "/oauth/token";
    public static final String DOT = ".";
    public static final String PROTOCOL = "https://";
    private final String xsuaaDomain;
    public static final String XSUAA_DOMAIN = "${vcap.services.renegotiation-xsuaa-service.credentials.uaadomain:not_set}";


    public EMVcapReaderImpl(EMVcapCredentials emVcapCredentials, @Value(XSUAA_DOMAIN) String xsuaaDomain) {
        this.emVcapCredentials = emVcapCredentials;
        this.xsuaaDomain = xsuaaDomain;
    }

    @Override
    public EMVcapCredentials.Oa2 getAmqpMessagingServiceCredentials() {
        return getAmqpSection().getOa2();
    }

    @Override
    public String getAmqpUri() {
        return getAmqpSection().getUri();
    }

    @Override
    public String getEmNamespace() {
        return emVcapCredentials.getNamespace();
    }

    private EMVcapCredentials.Messaging getAmqpSection() {
        Optional<EMVcapCredentials.Messaging> amqpSectionOptional = getMessagingByProtocol(PROTOCOL_AMQP);
        return amqpSectionOptional.orElseThrow(() -> new VcapReaderException("Unable to read amqp protocol from environment variable (VCAP_SERVICES)"));
    }

    private EMVcapCredentials.Messaging getHttpSection() {
        Optional<EMVcapCredentials.Messaging> httpSectionOptional = getMessagingByProtocol(PROTOCOL_HTTP);
        return httpSectionOptional.orElseThrow(() -> new VcapReaderException("Unable to read http protocol from environment variable (VCAP_SERVICES)"));
    }

    private Optional<EMVcapCredentials.Messaging> getMessagingByProtocol(String protocolType) {
        final Map<String, EMVcapCredentials.Messaging> messaging = emVcapCredentials.getMessaging();
        return messaging.values().stream()
                .filter(v -> {
                    final List<String> protocol = v.getProtocol();
                    return protocol.contains(protocolType);
                })
                .findFirst();
    }
}
