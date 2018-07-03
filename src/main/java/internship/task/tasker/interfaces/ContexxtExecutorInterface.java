package internship.task.tasker.interfaces;

import internship.task.tasker.domain.plain.models.Messaging;
import models.ContextModel;

public interface ContexxtExecutorInterface {
    void contextProcessing(Messaging messaging, ContextModel context);
}
