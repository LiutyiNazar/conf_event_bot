package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.*;
import internship.task.tasker.domain.Generic.GenericPlainMessage;
import internship.task.tasker.domain.Messaging;
import internship.task.tasker.domain.PlainMessage;
import internship.task.tasker.domain.Recipient;
import internship.task.tasker.domain.events.api.ContextState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ServiceCallbackExecutor implements ServiceCallbackInterface {

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
    private EventsApiUpdateInterface updateService;
    @Autowired
    private Environment environment;
    @Autowired
    private ListTemplateInterface listTemplateService;
    @Autowired
    private ContexxtExecutorInterface contextExecutorService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void executeText(Messaging messaging) {
        String answer = messaging.getMessage().getText();
        logger.info(answer);
        String recipientId = messaging.getSender().getId();
        ContextModel context = contextService.getContextByRecipientIdOrCreateIfNotExist(recipientId);

        //creating message, which will contain answer
        PlainMessage plainMessage = initTextMessage(recipientId, messaging);

        //processing, what text we have, and answering to it
        if (answer == null) {
            answerer.sendText(plainMessage, environment.getProperty("no_steackers"));
            //if state is not Ended, so we will process adding new Entity
        } else if (context.getContextState().equals("Ended")) {
            switch (answer) {

                case "Hello": {
                    answerer.sendText(plainMessage, environment.getProperty("hello_answer"));
                    break;
                }
                case "How are you?": {
                    answerer.sendText(plainMessage, environment.getProperty("how_are_you"));
                    break;
                }
                case "Redirect": {
                    buttonsService.sendMakerTab(plainMessage);
                    break;
                }
                case "Add new Speaker": {
                    contextService.setContextOrCreate(recipientId, ContextState.SET_SPEAKER_FIRST_NAME);
                    answerer.sendText(plainMessage, environment.getProperty("speaker_first_name"));
                    break;
                }
                case "Add new Session": {
                    contextService.setContextOrCreate(recipientId, ContextState.SET_SESSION_NAME);
                    answerer.sendText(plainMessage, environment.getProperty("name_input"));
                    break;
                }
                default: {
                    answerer.sendText(plainMessage, environment.getProperty("incorect"));
                }

            }

        } else {
            contextExecutorService.executeContextProcessing(messaging, context);
        }


    }

    public void executePostback(Messaging messaging) {
        String recepientId = messaging.getSender().getId();
        PlainMessage plainMessage = initPostback(recepientId);
        String postback = messaging.getPostback().getPayload();
        ContextModel context = contextService.getContextByRecipientIdOrCreateIfNotExist(recepientId);

        if (context.getContextState().equals("Ended")) {
            switch (postback) {
                case "Started!": {
                    answerer.sendText(plainMessage, environment.getProperty("started"));
                    break;
                }
                case "PostbackStarted": {
                    GenericPlainMessage genericPlainMessage = genericService.defineRecipientForGenericPlainMessage(recepientId);
                    listTemplateService.sendHelloTab(genericPlainMessage);
                    break;
                }
                case "PostbackSpeakers": {
                    logger.info("Received get speakers message");
                    List<SpeakerModel> speakers = eventsApiManagingService.getSpeakers();
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(recepientId);
                    service.sendGenericOrListTemplateSpeakers(plainMessage1, speakers);
                    break;
                }

                case "PostbackSessions": {
                    logger.info("Received get sessions message");
                    List<SessionModel> sessions = eventsApiManagingService.getSessions();
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(recepientId);
                    service.sendGenericOrListTemplateSessions(plainMessage1, sessions);
                    break;
                }
                case "PostbackBotsCrew": {
                    buttonsService.sendMakerTab(plainMessage);
                    break;
                }
                case "PostbackAddNew": {
                    logger.info("Received add new tab command ");
                    quickReplies.sendQuickReplyForAddNewTab(plainMessage);
                    break;
                }
                case "AddNewSpeaker": {
                    contextService.setContextOrCreate(recepientId, ContextState.SET_SPEAKER_FIRST_NAME);
                    answerer.sendText(plainMessage, environment.getProperty("speaker_first_name"));
                    break;
                }
                case "AddNewSession": {
                    contextService.setContextOrCreate(recepientId, ContextState.SET_SESSION_NAME);
                    answerer.sendText(plainMessage, environment.getProperty("name_input"));
                    break;
                }
                default: {
                    executePostbackWithParams(postback, plainMessage);
                }
            }
        } else contextExecutorService.executeContextProcessing(messaging, context);
    }


    private PlainMessage initPostback(String recipientId) {
        PlainMessage plainMessage = new PlainMessage();
        plainMessage.setRecipient(Recipient.builder().ID(recipientId).build());
        return plainMessage;
    }

    public PlainMessage initTextMessage(String recipientId, Messaging messaging) {
        return PlainMessage.builder()
                .message(messaging.getMessage())
                .recipient(Recipient.builder().ID(recipientId).build()).build();
    }

    private void executePostbackWithParams(String postback, PlainMessage plainMessage) {
        if (postback.length() > 14) {

            String number = postback.substring(15, postback.length());
            Integer num = Integer.parseInt(number);
            String text = postback.substring(0, 11);

            switch (text) {
                case "GetSessions": {
                    List<SessionModel> sessions = eventsApiManagingService.getSessionsBySpeakerId(num);
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(plainMessage.getRecipient().getID());
                    service.sendGenericOrListTemplateSessions(plainMessage1, sessions);
                    break;
                }
                case "GetSpeakers": {
                    List<SpeakerModel> speakers = eventsApiManagingService.getSpeakersBySessionId(num);
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(plainMessage.getRecipient().getID());
                    service.sendGenericOrListTemplateSpeakers(plainMessage1, speakers);
                    break;
                }
            }

        } else answerer.sendText(plainMessage, environment.getProperty("incorect"));
    }




}
