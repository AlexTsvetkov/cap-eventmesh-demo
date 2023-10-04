package com.sap.hanesbrand.config;

import com.sap.cloud.security.xsuaa.token.SpringSecurityContext;
import com.sap.cloud.servicesdk.xbem.core.MessagingService;
import com.sap.cloud.servicesdk.xbem.core.MessagingServiceFactory;
import com.sap.cloud.servicesdk.xbem.core.exception.MessagingException;
import com.sap.cloud.servicesdk.xbem.core.impl.MessagingServiceFactoryCreator;
import com.sap.cloud.servicesdk.xbem.extension.sapcp.jms.MessagingServiceJmsConnectionFactory;
import com.sap.cloud.servicesdk.xbem.extension.sapcp.jms.MessagingServiceJmsSettings;
import com.sap.hanesbrand.config.vcap.EMVcapCredentials;
import com.sap.hanesbrand.config.vcap.EMVcapReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    private final String PROTOCOL_AMQP = "amqp10ws";
    private final EMVcapReader emVcapReader;

    public MessagingConfig(EMVcapReader emVcapReader) {
        this.emVcapReader = emVcapReader;
    }

    @Bean
    public MessagingService messagingService() {
        EMVcapCredentials.Oa2 oauthCredentialsNode = emVcapReader.getAmqpMessagingServiceCredentials();
        MessagingService messagingService = new MessagingService();
        messagingService.setClientId(oauthCredentialsNode.getClientId());
        messagingService.setClientSecret(oauthCredentialsNode.getClientSecret());
        messagingService.setOAuthTokenEndpoint(oauthCredentialsNode.getTokenEndpoint());
        messagingService.setProtocol(PROTOCOL_AMQP);
        messagingService.setServiceUrl(emVcapReader.getAmqpUri());
        return messagingService;
    }

    @Bean
    public MessagingServiceFactory messagingServiceFactory (MessagingService messagingService) {
        return MessagingServiceFactoryCreator.createFactory(messagingService);
    }

    @Bean
    public MessagingServiceJmsConnectionFactory messagingServiceJmsConnectionFactory (MessagingServiceFactory messagingServiceFactory) {
        try {
            MessagingServiceJmsSettings settings = new MessagingServiceJmsSettings();
            settings.setFailoverMaxReconnectAttempts(5);
            settings.setFailoverInitialReconnectDelay(3000);
            settings.setFailoverReconnectDelay(3000);
            settings.setJmsRequestTimeout(30000);
            settings.setAmqpIdleTimeout(-1);

            return messagingServiceFactory.createConnectionFactory(MessagingServiceJmsConnectionFactory.class, settings);
        } catch (MessagingException e) {
            throw new IllegalStateException("Unable to create the Connection Factory", e);
        }
    }

}
