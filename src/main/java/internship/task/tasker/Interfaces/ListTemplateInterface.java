package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.Generic.GenericPlainMessage;
import models.SessionModel;
import models.SpeakerModel;

import java.util.List;

public interface ListTemplateInterface {

    void createAndSendListOfSessions(GenericPlainMessage plainMessage, List<SessionModel> sessions);

    void createAndSendListOfSpeakers(GenericPlainMessage genericPlainMessage, List<SpeakerModel> speakers);

    void sendHelloTab(GenericPlainMessage plainMessage);


}
