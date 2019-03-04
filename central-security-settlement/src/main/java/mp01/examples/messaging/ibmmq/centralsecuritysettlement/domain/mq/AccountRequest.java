package mp01.examples.messaging.ibmmq.centralsecuritysettlement.domain.mq;

public class AccountRequest {

    private int participantId;

    public AccountRequest(int participantId) {
        this.participantId = participantId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "participantId=" + participantId +
                '}';
    }
}
