package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.Generic.GenericPlainMessage;
import internship.task.tasker.domain.WebHookRepresentationJSON;
import models.SessionModel;

import java.util.List;

public interface DecisionMakingAnswerInterface {

    void makeDecision(WebHookRepresentationJSON hook) ;

    void sendGenericOrListTemplateSessions(GenericPlainMessage plainMessage, List<SessionModel> something);

    void sendGenericOrListTemplateSpeakers(GenericPlainMessage plainMessage, List<models.SpeakerModel> something);

}

