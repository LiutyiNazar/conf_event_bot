package internship.task.tasker.domain.generic;

import internship.task.tasker.domain.plain.models.Recipient;
import internship.task.tasker.interfaces.PlainMessageInterface;


public class GenericPlainMessage implements PlainMessageInterface{

    private Recipient recipient;

    private GenericMessage message;

    public Recipient getRecipient() {
        return recipient;
    }

    public GenericPlainMessage setRecipient(Recipient recipient) {
        this.recipient = recipient;
        return this;
    }

    public GenericMessage getMessage() {
        return message;
    }

    public GenericPlainMessage setMessage(GenericMessage message) {
        this.message = message;
        return this;
    }
}
