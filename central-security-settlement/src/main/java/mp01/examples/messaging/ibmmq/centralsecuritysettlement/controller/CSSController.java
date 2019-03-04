package mp01.examples.messaging.ibmmq.centralsecuritysettlement.controller;

import mp01.examples.messaging.ibmmq.centralsecuritysettlement.domain.mq.AccountRequest;
import mp01.examples.messaging.ibmmq.centralsecuritysettlement.service.CSSMQProducerService;
import mp01.examples.messaging.ibmmq.centralsecuritysettlement.service.CSSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/css")
public class CSSController {

    @Autowired
    private CSSService cssService;

    @Autowired
    private CSSMQProducerService cssmqProducerService;

    @RequestMapping(method = RequestMethod.GET, path = "/accounts/{participantId}")
    public ResponseEntity<Void> retrieveAccountInfo(@PathVariable("participantId") int participantId) {
        AccountRequest accountRequest = new AccountRequest(participantId);
        cssmqProducerService.sendMessageToPSE(accountRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
