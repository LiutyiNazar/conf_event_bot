package internship.task.tasker.manager;

import internship.task.tasker.domain.events.api.ContextState;
import internship.task.tasker.interfaces.ContextServiceInterface;
import internship.task.tasker.interfaces.EventsApiManagingInterface;
import internship.task.tasker.interfaces.EventsApiUpdateInterface;
import models.ContextModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextService implements ContextServiceInterface {

    @Autowired
    private EventsApiManagingInterface eventsApiManagingService;

    @Autowired
    private EventsApiUpdateInterface updateService;

    private ContextModel getContextByRecipientId(String recipientId) {
        return eventsApiManagingService.getContextByRecipientId(recipientId);
    }

    @Override
    public void setContextOrCreate(String recipientId, ContextState state) {
        updateService.doUpdateForContext(recipientId, state.getValue());
    }

    public List<ContextModel> getAllContext() {
        return eventsApiManagingService.getContext();
    }

    @Override
    public ContextModel getContextByRecipientIdOrCreateIfNotExist(String recipientId) {
        ContextModel context = getContextByRecipientId(recipientId);
        if (context != null)
            return context;
        else
            return ContextModel.builder().contextState(ContextState.ENDED.getValue()).recipientId(recipientId).build();
    }
}
