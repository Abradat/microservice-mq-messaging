package mp01.examples.messaging.ibmmq.participantsecurityenquiry.service;

import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.Account;
import mp01.examples.messaging.ibmmq.participantsecurityenquiry.repository.PSERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PSEService {

    @Autowired
    private PSERepository pseRepository;

    @Transactional
    public Account createAccount(Account account) {
        if(pseRepository.findAccountByParticipantId(account.getParticipantId()) == null) {
            Account resultAccount = pseRepository.save(account);
            return resultAccount;
        } else { return null; }
    }
}
