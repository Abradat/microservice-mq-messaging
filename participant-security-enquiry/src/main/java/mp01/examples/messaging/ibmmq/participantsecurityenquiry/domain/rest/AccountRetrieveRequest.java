package mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.rest;

public class AccountRetrieveRequest {

    private int participantId;

    public AccountRetrieveRequest(int participantId) {
        this.participantId = participantId;
    }

    public AccountRetrieveRequest() {
    }

    public int getParticipantId() {
        return participantId;
    }
}
