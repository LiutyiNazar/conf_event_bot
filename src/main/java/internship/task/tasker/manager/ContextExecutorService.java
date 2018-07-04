package internship.task.tasker.manager;

import internship.task.tasker.domain.plain.models.Messaging;
import internship.task.tasker.interfaces.ContexxtExecutorInterface;
import models.ContextModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContextExecutorService implements ContexxtExecutorInterface {

    @Autowired
    private ContextExecutorCasesService casesService;

    @Override
    public void contextProcessing(Messaging messaging, ContextModel context) {

        String check = context.getContextState();

        casesService.init(messaging);

        switch (check) {
            case "SetSessionName":
               casesService.setSessionName();
                break;
            case "SetSessionTime":
               casesService.setSessionTime();
                break;

            case "SetSessionDescription":
                casesService.setSessionDescription();
                break;

            case "SetSessionSpeaker":
                casesService.setSessionSpeaker();
                break;
            case "SetSpeakerFirstName":
                casesService.setSpeakerFirstName();
                break;

            case "SetSpeakerLastName":
                casesService.setSpeakerLastName();
                break;

            case "SetSpeakerImageUrl":
                casesService.setSpeakerImageURL();
                break;

            case "SetSpeakerDescription":
                casesService.setSpeakerDescription();
                break;

            case "SetSpeakerEmail":
                casesService.setSpeakerEmail();
                break;

            case "SetSpeakerSessions":
                casesService.setSpeakerSessions();
                break;

            case "SetSessionNameInside":
               casesService.setSessionNameInside();
                break;

            case "SetSpeakerFirstNameInside":
                casesService.setSpeakerFirstNameInside();
                break;

            default:
                casesService.defaultCase();


        }

    }
}
