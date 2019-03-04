package mp01.examples.messaging.ibmmq.participantsecurityenquiry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.mq.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class PSEMQProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PSEMQProducerService.class);

    @Value("${mq.queue.css}")
    private String cssJsonQueue;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessageToCss(AccountResponse accountResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Sending Account='{}", accountResponse.toString());
        String accountAsString;
        try {
            accountAsString = objectMapper.writeValueAsString(accountResponse);
        } catch (JsonProcessingException ex) {
            accountAsString = "ERROR";
        }

        jmsTemplate.convertAndSend(cssJsonQueue, accountAsString);
    }
}
