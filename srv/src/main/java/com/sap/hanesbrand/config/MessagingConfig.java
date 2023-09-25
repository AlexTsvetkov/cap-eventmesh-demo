package com.sap.hanesbrand.config;

import com.sap.cloud.servicesdk.xbem.core.MessagingService;
import com.sap.cloud.servicesdk.xbem.core.MessagingServiceFactory;
import com.sap.cloud.servicesdk.xbem.core.exception.MessagingException;
import com.sap.cloud.servicesdk.xbem.core.impl.MessagingServiceFactoryCreator;
import com.sap.cloud.servicesdk.xbem.extension.sapcp.jms.MessagingServiceJmsConnectionFactory;
import com.sap.cloud.servicesdk.xbem.extension.sapcp.jms.MessagingServiceJmsSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${service.url}")
    private String serviceUrl;

    @Value("${token.url}")
    private String tokenUrl;

    private final String PROTOCOL = "amqp10ws";

    @Bean
    public MessagingService messagingService() {
        MessagingService messagingService = new MessagingService();
        messagingService.setClientId(clientId);
        messagingService.setClientSecret(clientSecret);
        messagingService.setOAuthTokenEndpoint(tokenUrl);
        messagingService.setProtocol(PROTOCOL);
        messagingService.setServiceUrl(serviceUrl);

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
