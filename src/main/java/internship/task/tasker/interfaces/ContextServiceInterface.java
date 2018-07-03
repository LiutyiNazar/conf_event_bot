package internship.task.tasker.interfaces;

import internship.task.tasker.domain.events.api.ContextState;
import models.ContextModel;

public interface ContextServiceInterface {


    void setContextOrCreate(String recipientId, ContextState state);

    ContextModel getContextByRecipientIdOrCreateIfNotExist(String recipientId);
}