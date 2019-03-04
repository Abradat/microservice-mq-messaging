package mp01.examples.messaging.ibmmq.participantsecurityenquiry.domain.mq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequest {

    private int participantId;

    @JsonCreator
    public AccountRequest(@JsonProperty(value = "participantId") int participantId) {
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
