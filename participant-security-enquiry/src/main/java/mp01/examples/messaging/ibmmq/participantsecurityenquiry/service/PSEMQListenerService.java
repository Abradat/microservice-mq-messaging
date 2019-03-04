package mp01.examples.messaging.ibmmq.participantsecurityenquiry.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.Account;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.mq.AccountRequest;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.mq.AccountResponse;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.repository.PSERepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@EnableJms
@Service
public class PSEMQListenerService {

    @Value("${mq.queue.pse}")
    private String pseJsonQueue;

    @Autowired
    private PSERepository pseRepository;

    @Autowired
    private PSEMQProducerService psemqProducerService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PSEMQListenerService.class);

    @JmsListener(destination = "${mq.queue.pse}")
    public void receiveMessageFromCSS(String accountRequestAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountRequest accountRequest;
        try {
            accountRequest = objectMapper.readValue(accountRequestAsString, AccountRequest.class);
        } catch (IOException ex) {
            accountRequest =  new AccountRequest(0);
            ex.printStackTrace();
        }

        LOGGER.info("Received Account Request='{}'", accountRequest.toString());

        Account auxAccount = pseRepository.findAccountByParticipantId(accountRequest.getParticipantId());
        if(auxAccount != null ) {
            AccountResponse accountResponse = new AccountResponse(auxAccount.getParticipantId(), auxAccount.getISIN(), auxAccount.getAmount());
            psemqProducerService.sendMessageToCss(accountResponse);
        } else {
            AccountResponse accountResponse = new AccountResponse(0, "0", 0.0);
            psemqProducerService.sendMessageToCss(accountResponse);
        }
    }
}
