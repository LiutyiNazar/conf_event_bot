package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.Messaging;
import internship.task.tasker.domain.PlainMessage;

public interface ServiceCallbackInterface {

    void executeText( Messaging messaging);

    void executePostback( Messaging messaging);

    PlainMessage initTextMessage(String recipientId, Messaging messaging);
}
