package mp01.examples.messaging.ibmmq.participantsecurityenquiry.controller;

import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.Account;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.rest.AccountCreateRequest;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.rest.AccountCreateResponse;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.rest.AccountRetrieveResponse;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.repository.PSERepository;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.service.PSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pse")
public class PSEController {
    @Autowired
    private PSERepository pseRepository;

    @Autowired
    private PSEService pseService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountCreateResponse> createAccount(@RequestBody AccountCreateRequest accountCreateRequest) {
        Account account = new Account(accountCreateRequest.getParticipantId(), accountCreateRequest.getIsinNumber(), accountCreateRequest.getAmount());
        Account resultAccount = pseService.createAccount(account);
        if(resultAccount == null) {return new ResponseEntity<>(HttpStatus.CONFLICT);}
        else {return new ResponseEntity<>(HttpStatus.CREATED);}
    }

    @RequestMapping(method = RequestMethod.GET, path = "/accounts/{participantId}")
    public ResponseEntity<AccountRetrieveResponse> retrieveAccount(@PathVariable("participantId") int participantId) {
        Account resultAccount = pseRepository.findAccountByParticipantId(participantId);
        AccountRetrieveResponse accountRetrieveResponse = new AccountRetrieveResponse(resultAccount.getParticipantId(), resultAccount.getISIN(), resultAccount.getAmount());
        if(resultAccount != null) { return new ResponseEntity<>(accountRetrieveResponse, HttpStatus.OK); }
        else { return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;}
    }
}
