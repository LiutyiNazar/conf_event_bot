package internship.task.tasker.manager;

import internship.task.tasker.domain.generic.GenericPlainMessage;
import internship.task.tasker.domain.plain.models.WebHookRepresentationJSON;
import internship.task.tasker.interfaces.DecisionMakingAnswerInterface;
import internship.task.tasker.interfaces.GenericInterface;
import internship.task.tasker.interfaces.ListTemplateInterface;
import internship.task.tasker.interfaces.ServiceCallbackInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DecisionMakingAnswerService implements DecisionMakingAnswerInterface {

    @Autowired
    private ServiceCallbackInterface executor;
    @Autowired
    private ListTemplateInterface listTemplateService;
    @Autowired
    private GenericInterface genericService;

    @Override
    public void makeDecision(WebHookRepresentationJSON hook) {

        LOGGER.info("Received message");
        hook.getEntry().forEach(entry -> entry.getMessaging().forEach(messaging -> {

            if (messaging.getMessage() != null) {
                LOGGER.info("Calling text executor");
                executor.executeText(messaging);

            } else {
                LOGGER.info("Calling postback executor");
                executor.executePostback(messaging);
            }


        }));


    }

    @Override
    public void sendGenericOrListTemplateSessions(GenericPlainMessage plainMessage, List<models.SessionModel> something) {
        LOGGER.info("Size = " + something.size());
        if ((something.size() > 1)) {
            if (something.size() < 4) {
                listTemplateService.createAndSendListOfSessions(plainMessage, something);
            } else {
                genericService.createAndSendGenericOfSessions(plainMessage, something);
            }
        } else {
            genericService.createAndSendGenericOfSessions(plainMessage, something);
        }
    }

    @Override
    public void sendGenericOrListTemplateSpeakers(GenericPlainMessage plainMessage, List<models.SpeakerModel> something) {
        LOGGER.info("Size = " + something.size());
        if ((something.size() > 1)) {

            if (something.size() < 5) {
                listTemplateService.createAndSendListOfSpeakers(plainMessage, something);
            } else {
                genericService.createAndSendGenericOfSpeakers(plainMessage, something);
            }

        } else {
            genericService.createAndSendGenericOfSpeakers(plainMessage, something);
        }
    }

}

