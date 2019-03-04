package mp01.examples.messaging.ibmmq.centralsecuritysettlement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mp01.examples.messaging.ibmmq.centralsecuritysettlement.domain.mq.AccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableJms
public class CSSMQProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSSMQProducerService.class);

    @Value("${mq.queue.pse}")
    private String pseJsonQueue;

    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMessageToPSE(AccountRequest accountRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        String accountRequestAsString;
        try {
            accountRequestAsString = objectMapper.writeValueAsString(accountRequest);
        } catch (JsonProcessingException e ) {
            accountRequestAsString = "ERROR";
        }
        LOGGER.info("Sending Account Request='{}'", accountRequest.toString());
        jmsTemplate.convertAndSend(pseJsonQueue, accountRequestAsString);
    }
}
