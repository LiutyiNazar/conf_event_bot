package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.ContextServiceInterface;
import internship.task.tasker.Interfaces.EventsApiManagingInterface;
import internship.task.tasker.Interfaces.EventsApiUpdateInterface;
import internship.task.tasker.domain.events.api.ContextState;
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

    public void setContextOrCreate(String recipientId, ContextState state) {
        updateService.doUpdateForContext(recipientId, state.getValue());
    }

    public List<ContextModel> getAllContext() {
        return eventsApiManagingService.getContext();
    }

    public ContextModel getContextByRecipientIdOrCreateIfNotExist(String recipientId) {
        ContextModel context = getContextByRecipientId(recipientId);
        if (context != null) return context;
        else return ContextModel.builder().contextState(ContextState.ENDED.getValue()).recipientId(recipientId).build();
    }
}
