package mp01.examples.messaging.ibmmq.participantsecurityenquiry.repository;

import mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PSERepository extends PagingAndSortingRepository<Account, Integer> {
    Account findAccountByParticipantId(int participantId);
}
