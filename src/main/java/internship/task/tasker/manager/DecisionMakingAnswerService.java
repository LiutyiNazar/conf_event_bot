package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.DecisionMakingAnswerInterface;
import internship.task.tasker.Interfaces.GenericInterface;
import internship.task.tasker.Interfaces.ListTemplateInterface;
import internship.task.tasker.Interfaces.ServiceCallbackInterface;
import internship.task.tasker.domain.Generic.GenericPlainMessage;
import internship.task.tasker.domain.WebHookRepresentationJSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DecisionMakingAnswerService implements DecisionMakingAnswerInterface {

    @Autowired
    private ServiceCallbackInterface executor;
    @Autowired
    private ListTemplateInterface listTemplateService;
    @Autowired
    private GenericInterface genericService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void makeDecision(WebHookRepresentationJSON hook) {

        logger.info("Received message");
        hook.getEntry().forEach(entry -> entry.getMessaging().forEach(messaging -> {
            if (messaging.getMessage() != null) {
                logger.info("Calling text executor");
                executor.executeText(messaging);

            } else {
                logger.info("Calling postback executor");
                executor.executePostback(messaging);
            }


        }));


    }

    public void sendGenericOrListTemplateSessions(GenericPlainMessage plainMessage, List<models.SessionModel> something) {
        logger.info("Size = " + something.size());
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

    public void sendGenericOrListTemplateSpeakers(GenericPlainMessage plainMessage, List<models.SpeakerModel> something) {
        logger.info("Size = " + something.size());
        if ((something.size() > 1)) {

            if (something.size() < 4) {
                listTemplateService.createAndSendListOfSpeakers(plainMessage, something);
            } else {
                genericService.createAndSendGenericOfSpeakers(plainMessage, something);
            }

        } else {
            genericService.createAndSendGenericOfSpeakers(plainMessage, something);
        }
    }

}

