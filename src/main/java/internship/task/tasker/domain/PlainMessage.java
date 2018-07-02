package internship.task.tasker.domain;

import internship.task.tasker.Interfaces.PlainMessageInterface;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlainMessage implements PlainMessageInterface{

    private Recipient recipient;

    private Message message;

/*    public Recipient getRecipient() {
        return recipient;
    }

    public PlainMessage setRecipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public PlainMessage setMessage(Message message) {
        this.message = message;
        return this;
    }*/
}
