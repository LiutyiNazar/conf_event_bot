package internship.task.tasker.interfaces;

import internship.task.tasker.domain.generic.GenericPlainMessage;
import internship.task.tasker.domain.plain.models.WebHookRepresentationJSON;
import models.SessionModel;

import java.util.List;

public interface DecisionMakingAnswerInterface {

    void makeDecision(WebHookRepresentationJSON hook) ;

    void sendGenericOrListTemplateSessions(GenericPlainMessage plainMessage, List<SessionModel> something);

    void sendGenericOrListTemplateSpeakers(GenericPlainMessage plainMessage, List<models.SpeakerModel> something);

}

