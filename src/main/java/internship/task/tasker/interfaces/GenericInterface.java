package internship.task.tasker.interfaces;


import internship.task.tasker.domain.generic.GenericPlainMessage;
import models.SessionModel;
import models.SpeakerModel;

import java.util.List;

public interface GenericInterface {
    void createAndSendGenericOfSessions(GenericPlainMessage plainMessage, List<SessionModel> sessions);

    void createAndSendGenericOfSpeakers(GenericPlainMessage genericPlainMessage, List<SpeakerModel> speakers);

    GenericPlainMessage defineRecipientForGenericPlainMessage(String recipientId);

    void sendSpeakersGenericToChoose(String recipientId, List<SpeakerModel> speakers);

    void sendSessionsGenericToChoose(String recipientId, List<SessionModel> sessions);
}
