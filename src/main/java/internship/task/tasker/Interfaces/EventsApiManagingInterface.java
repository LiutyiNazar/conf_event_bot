package internship.task.tasker.Interfaces;

import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;

import java.util.List;

public interface EventsApiManagingInterface {

    List<SpeakerModel> getSpeakers();

    List<SessionModel> getSessions();

    List<SessionModel> getSessionsBySpeakerId(int id);

    List<SpeakerModel> getSpeakersBySessionId(int id);

    List<ContextModel> getContext();

    ContextModel getContextByRecipientId(String id);

    Integer getLastSessionId();

    Integer getLastSpeakerId();


}
