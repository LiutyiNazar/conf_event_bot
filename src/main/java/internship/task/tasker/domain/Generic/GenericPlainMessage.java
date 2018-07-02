package internship.task.tasker.domain.Generic;

import internship.task.tasker.Interfaces.PlainMessageInterface;
import internship.task.tasker.domain.Recipient;


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
