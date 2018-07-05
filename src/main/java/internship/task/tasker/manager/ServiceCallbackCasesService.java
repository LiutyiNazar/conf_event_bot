package internship.task.tasker.manager;

import internship.task.tasker.domain.events.api.ContextState;
import internship.task.tasker.domain.generic.GenericPlainMessage;
import internship.task.tasker.domain.plain.models.Messaging;
import internship.task.tasker.domain.plain.models.PlainMessage;
import internship.task.tasker.interfaces.*;
import lombok.extern.slf4j.Slf4j;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ServiceCallbackCasesService {
    @Autowired
    private AnswerInterface answerer;
    @Autowired
    private ButtonServiceInterface buttonsService;
    @Autowired
    private QuickRepliesInterface quickReplies;
    @Autowired
    private GenericInterface genericService;
    @Autowired
    private DecisionMakingAnswerInterface service;
    @Autowired
    private EventsApiManagingInterface eventsApiManagingService;
    @Autowired
    private ContextServiceInterface contextService;
    @Autowired
    private Environment environment;
    @Autowired
    private ListTemplateInterface listTemplateService;
    @Autowired
    private ServiceCallbackExecutor serviceCallbackExecutor;

    private String answer;
    private String recipientId;
    private PlainMessage plainMessage;
    private String postback;

    public void initText(Messaging messaging) {

        this.answer = messaging.getMessage().getText();
        LOGGER.info(answer);
        recipientId = messaging.getSender().getId();
        plainMessage = serviceCallbackExecutor.initTextMessage(recipientId, messaging);
        LOGGER.info("Received " + postback + " command ");

    }

    public void initPostback(Messaging messaging) {

        recipientId = messaging.getSender().getId();
        plainMessage = serviceCallbackExecutor.initPostback(recipientId);
        postback = messaging.getPostback().getPayload();
        LOGGER.info("Received " + postback + " command ");
    }

    public void addNewSpeaker() {
        contextService.setContextOrCreate(recipientId, ContextState.SET_SPEAKER_FIRST_NAME);
        answerer.sendText(plainMessage, environment.getProperty("speaker_first_name"));
    }

    public void addNewSession() {
        contextService.setContextOrCreate(recipientId, ContextState.SET_SESSION_NAME);
        answerer.sendText(plainMessage, environment.getProperty("name_input"));
    }

    public void postbackStarted() {
        GenericPlainMessage genericPlainMessage = genericService.defineRecipientForGenericPlainMessage(recipientId);
        listTemplateService.sendHelloTab(genericPlainMessage);
    }


    public void postbackSpeakers() {

        List<SpeakerModel> speakers = eventsApiManagingService.getSpeakers();
        GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(recipientId);
        service.sendGenericOrListTemplateSpeakers(plainMessage1, speakers);
    }

    public void postbackSessions() {

        List<SessionModel> sessions = eventsApiManagingService.getSessions();
        GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(recipientId);
        service.sendGenericOrListTemplateSessions(plainMessage1, sessions);
    }

    public void started() {
        answerer.sendText(plainMessage, environment.getProperty("started"));
    }

    public void postbackMaker() {
        buttonsService.sendMakerTab(plainMessage);
    }

    public void postbackAddNew() {
        quickReplies.sendQuickReplyForAddNewTab(plainMessage);
    }

    public void caseDefault() {
        serviceCallbackExecutor.executeParamPostback(postback, plainMessage);
    }

    public void noSteackers() {
        answerer.sendText(plainMessage, environment.getProperty("no_steackers"));
    }

    public void caseHello() {
        answerer.sendText(plainMessage, environment.getProperty("hello_answer"));
    }

    public void howAre() {
        answerer.sendText(plainMessage, environment.getProperty("how_are_you"));
    }

    public void caseIncorrect() {
        answerer.sendText(plainMessage, environment.getProperty("incorect"));
    }
}
