package mp01.examples.messaging.ibmmq.centralsecuritysettlement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mp01.examples.messaging.ibmmq.centralsecuritysettlement.domain.mq.AccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableJms
public class CSSMQListenerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSSMQListenerService.class);

    @JmsListener(destination = "${mq.queue.css}")
    public void receiveMessageFromPSE(String accountAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountResponse accountResponse;
        try {
            accountResponse = objectMapper.readValue(accountAsString, AccountResponse.class);
        } catch (IOException ex) {
            accountResponse = new AccountResponse(0, "0", 0.0);
            ex.printStackTrace();
        }
        LOGGER.info("Received Account='{}'", accountResponse.toString());
    }
}
