package internship.task.tasker.manager;

import internship.task.tasker.domain.events.api.ContextState;
import internship.task.tasker.domain.plain.models.Messaging;
import internship.task.tasker.domain.plain.models.PlainMessage;
import internship.task.tasker.interfaces.*;
import lombok.*;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ContextExecutorCasesService {
    @Autowired
    private AnswerInterface answerer;
    @Autowired
    private ButtonServiceInterface buttonsService;
    @Autowired
    private GenericInterface genericService;
    @Autowired
    private EventsApiManagingInterface eventsApiManagingService;
    @Autowired
    private ContextServiceInterface contextService;
    @Autowired
    private EventsApiUpdateInterface updateService;
    @Autowired
    private Environment environment;
    @Autowired
    private ServiceCallbackInterface serviceCallback;

    private Messaging messaging;
    private ContextModel contextModel;
    private String recipientID;
    private  Integer sessionId;
    private Integer speakerId;
    private String value = "";
    private PlainMessage plainMessage;

    public void init(Messaging messaging ){
        this.messaging = messaging;
        recipientID = messaging.getSender().getId();
        sessionId = eventsApiManagingService.getLastSessionId();
       speakerId = eventsApiManagingService.getLastSpeakerId();
        if (messaging.getMessage() != null) {

            value = messaging.getMessage().getText();
        }
        plainMessage = serviceCallback.initTextMessage(recipientID, messaging);
    }

    public void setSessionName(){
        updateService.doUpdateForSession(sessionId + 1, "name", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SESSION_TIME);
        answerer.sendText(plainMessage, environment.getProperty("time_input"));
    }

    public void setSessionTime(){
        updateService.doUpdateForSession(sessionId, "time", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SESSION_DESCRIPTION);
        answerer.sendText(plainMessage, environment.getProperty("session_descript"));
    }

    public void setSessionDescription(){
        updateService.doUpdateForSession(sessionId, "description", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SESSION_SPEAKERS);
        answerer.sendText(plainMessage, environment.getProperty("session_add_speaker"));
        List<SpeakerModel> speakers = eventsApiManagingService.getSpeakers();
        genericService.sendSpeakersGenericToChoose(recipientID, speakers);
        buttonsService.createNewSpeakerButton(plainMessage);
    }

    public void setSessionSpeaker(){
        String request = messaging.getPostback().getPayload();
        String number = request.substring(14, request.length());
        if (Integer.parseInt(number) == 0) {
            updateService.doUpdateForSpeaker(speakerId + 1, "id", number);
            number = "" + eventsApiManagingService.getLastSpeakerId();
            updateService.doUpdateForSession(sessionId, "speakers", number);
            contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_FIRST_NAME_INSIDE);
            answerer.sendText(plainMessage, environment.getProperty("speaker_first_name"));
        } else if (Integer.parseInt(number) < 0) {
            contextService.setContextOrCreate(recipientID, ContextState.ENDED);
            answerer.sendText(plainMessage, environment.getProperty("ended_session"));
        } else {
            updateService.doUpdateForSession(sessionId, "speakers", number);
            contextService.setContextOrCreate(recipientID, ContextState.ENDED);
            answerer.sendText(plainMessage, environment.getProperty("ended_session"));
        }
    }

    public void setSpeakerFirstName(){
        updateService.doUpdateForSpeaker(speakerId + 1, "firstName", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_LAST_NAME);
        answerer.sendText(plainMessage, environment.getProperty("speaker_last_name"));
    }

    public void setSpeakerLastName(){
        updateService.doUpdateForSpeaker(speakerId, "lastName", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_IMAGE_URL);
        answerer.sendText(plainMessage, environment.getProperty("speaker_image_url"));
    }

    public void setSpeakerImageURL(){
        if (value.length() < 9) {
            answerer.sendText(plainMessage, "This is not valid url address! Please, try more");
        } else if (!"http".equals(value.substring(0, 4))) {
            answerer.sendText(plainMessage, "This is not valid url address! Please, try more");
        } else {
            updateService.doUpdateForSpeaker(speakerId, "imageUrl", value);
            contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_EMAIL);
            answerer.sendText(plainMessage, environment.getProperty("speaker_email"));
        }
    }

    public void setSpeakerDescription(){
        updateService.doUpdateForSpeaker(speakerId, "description", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_SESSIONS);
        answerer.sendText(plainMessage, environment.getProperty("speaker_add_session"));
        List<SessionModel> sessions = eventsApiManagingService.getSessions();
        genericService.sendSessionsGenericToChoose(recipientID, sessions);
        buttonsService.createNewSessionButton(plainMessage);
    }

    public void setSpeakerEmail(){
        Pattern pattern = Pattern.compile(Objects.requireNonNull(environment.getProperty("regex")));
        if (!pattern.matcher(value).matches()) {
            answerer.sendText(plainMessage, "This is not valid email address! Please, try more");
        } else {
            contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_DESCRIPTION);
            updateService.doUpdateForSpeaker(speakerId, "email", value);
            answerer.sendText(plainMessage, environment.getProperty("speaker_descript"));
        }
    }

    public void setSpeakerSessions(){
        String request = messaging.getPostback().getPayload();
        String number = request.substring(14, request.length());
        if (Integer.parseInt(number) == 0) {
            updateService.doUpdateForSession(sessionId + 1, "id", value);
            number = "" + eventsApiManagingService.getLastSessionId();
            updateService.doUpdateForSpeaker(speakerId, "sessions", number);
            contextService.setContextOrCreate(recipientID, ContextState.SET_SESSION_NAME_INSIDE);
            answerer.sendText(plainMessage, environment.getProperty("name_input"));
        } else if (Integer.parseInt(number) < 0) {
            contextService.setContextOrCreate(recipientID, ContextState.ENDED);
            answerer.sendText(plainMessage, environment.getProperty("ended_speaker"));
        } else {
            updateService.doUpdateForSpeaker(speakerId, "sessions", number);
            contextService.setContextOrCreate(recipientID, ContextState.ENDED);
            answerer.sendText(plainMessage, environment.getProperty("ended_speaker"));
        }
    }

    public void setSessionNameInside(){
        updateService.doUpdateForSession(sessionId, "name", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SESSION_TIME);
        answerer.sendText(plainMessage, environment.getProperty("time_input"));
    }

    public void setSpeakerFirstNameInside(){
        updateService.doUpdateForSpeaker(speakerId + 1, "firstName", value);
        contextService.setContextOrCreate(recipientID, ContextState.SET_SPEAKER_LAST_NAME);
        answerer.sendText(plainMessage, environment.getProperty("speaker_last_name"));
    }

    public void defaultCase(){
        answerer.sendText(plainMessage, environment.getProperty("incorect"));
    }




}
