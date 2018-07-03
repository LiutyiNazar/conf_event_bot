package internship.task.tasker.interfaces;

import internship.task.tasker.domain.plain.models.Messaging;
import internship.task.tasker.domain.plain.models.PlainMessage;

public interface ServiceCallbackInterface {

    void executeText( Messaging messaging);

    void executePostback( Messaging messaging);

    PlainMessage initTextMessage(String recipientId, Messaging messaging);
}
