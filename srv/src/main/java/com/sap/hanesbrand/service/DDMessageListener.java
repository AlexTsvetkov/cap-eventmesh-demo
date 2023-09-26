package com.sap.hanesbrand.service;

import cds.gen.documentdeliveryservice.OutboundDeliveryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sap.hanesbrand.client.dto.OutboundDeliveryDto;
import com.sap.hanesbrand.dao.OutboundDeliveryDao;
import com.sap.hanesbrand.mapper.OutboundDeliveryMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
@AllArgsConstructor
@Service
public class DDMessageListener implements MessageListener {

    private final S4Service s4Service;
    private final OutboundDeliveryMapper documentDeliveryMapper;
    private final OutboundDeliveryDao outboundDeliveryRepository;

    @Override
    public void onMessage(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                messageText = ((TextMessage) message).getText();
            } else if (message instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage) message;
                byte[] data = new byte[(int) bytesMessage.getBodyLength()];
                bytesMessage.readBytes(data);
                messageText = new String(data);
            }

            OutboundDeliveryEvent event = objectMapper.readValue(messageText, OutboundDeliveryEvent.class);
            log.info("--Event received: " + event);
            //TODO Check logic for get by Id in BTP
            //Received from s4
            OutboundDeliveryDto outboundDeliveryDto = s4Service.getOutboundDeliveryById(event.getDeliveryDocument());
            OutboundDeliveryEvent outboundDeliveryEvent = documentDeliveryMapper.s4DocumentToOutboundDelivery(outboundDeliveryDto.getDocument());
            outboundDeliveryRepository.saveOutboundDelivery(outboundDeliveryEvent);

        } catch (JMSException | JsonProcessingException e) {
            log.error("--Cannot receive event: " + e.getMessage());
        }

    }
}
