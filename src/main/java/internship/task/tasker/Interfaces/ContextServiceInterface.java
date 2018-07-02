package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.events.api.ContextState;
import models.ContextModel;

public interface ContextServiceInterface {


    void setContextOrCreate(String recipientId, ContextState state);

    ContextModel getContextByRecipientIdOrCreateIfNotExist(String recipientId);
}