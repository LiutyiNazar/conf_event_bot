package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.Messaging;
import models.ContextModel;

public interface ContexxtExecutorInterface {
    void executeContextProcessing(Messaging messaging, ContextModel context);
}
