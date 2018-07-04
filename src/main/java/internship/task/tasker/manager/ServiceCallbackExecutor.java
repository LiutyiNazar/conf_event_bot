package internship.task.tasker.manager;

import internship.task.tasker.domain.generic.GenericPlainMessage;
import internship.task.tasker.domain.plain.models.Messaging;
import internship.task.tasker.domain.plain.models.PlainMessage;
import internship.task.tasker.domain.plain.models.Recipient;
import internship.task.tasker.interfaces.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Slf4j
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
    @Autowired
    private ServiceCallbackCasesService  casesService;



    @Override
    public void executeText(Messaging messaging) {

        String answer = messaging.getMessage().getText();
        String recipientId = messaging.getSender().getId();
        ContextModel context = contextService.getContextByRecipientIdOrCreateIfNotExist(recipientId);

        casesService.initText(messaging);


        if (answer == null) {
           casesService.noSteackers();

        } else if ("Ended".equals(context.getContextState())) {
            switch (answer) {

                case "Hello":
                    casesService.caseHello();
                    break;
                case "How are you?":
                    casesService.howAre();
                    break;
                case "Add new Speaker":
                   casesService.addNewSpeaker();
                    break;
                case "Add new Session":
                    casesService.addNewSession();
                    break;
                default:
                    casesService.caseIncorrect();

            }

        } else {
            contextExecutorService.contextProcessing(messaging, context);
        }


    }

    @Override
    public void executePostback(Messaging messaging) {

        String recepientId = messaging.getSender().getId();
        String postback = messaging.getPostback().getPayload();
        ContextModel context = contextService.getContextByRecipientIdOrCreateIfNotExist(recepientId);

        casesService.initPostback(messaging);

        if ("Ended".equals(context.getContextState())) {


            switch (postback) {
                case "Started!":
                    casesService.started();
                    break;
                case "PostbackStarted":
                    casesService.postbackStarted();
                    break;
                case "PostbackSpeakers":
                    casesService.postbackSpeakers();
                    break;
                case "PostbackSessions":
                casesService.postbackSessions();
                    break;
                case "PostbackMaker":
                    casesService.postbackMaker();
                    break;
                case "PostbackAddNew":
                    casesService.postbackAddNew();
                    break;
                case "AddNewSpeaker":
                    casesService.addNewSpeaker();
                    break;
                case "AddNewSession":
                    casesService.addNewSession();
                    break;
                default:
                    casesService.caseDefault();

            }

        } else contextExecutorService.contextProcessing(messaging, context);
    }


    public PlainMessage initPostback(String recipientId) {
        PlainMessage plainMessage = new PlainMessage();
        plainMessage.setRecipient(Recipient.builder().ID(recipientId).build());
        return plainMessage;
    }

    @Override
    public PlainMessage initTextMessage(String recipientId, Messaging messaging) {
        return PlainMessage.builder()
                .message(messaging.getMessage())
                .recipient(Recipient.builder().ID(recipientId).build()).build();
    }

    public void executeParamPostback(String postback, PlainMessage plainMessage) {
        if (postback.length() > 14) {

            String number = postback.substring(15, postback.length());
            Integer num = Integer.parseInt(number);
            String text = postback.substring(0, 11);

             if("GetSessions".equals(text)){
                    List<SessionModel> sessions = eventsApiManagingService.getSessionsBySpeakerId(num);
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(plainMessage.getRecipient().getID());
                    service.sendGenericOrListTemplateSessions(plainMessage1, sessions);
                }
                if ("GetSpeakers".equals(text)){
                    List<SpeakerModel> speakers = eventsApiManagingService.getSpeakersBySessionId(num);
                    GenericPlainMessage plainMessage1 = genericService.defineRecipientForGenericPlainMessage(plainMessage.getRecipient().getID());
                    service.sendGenericOrListTemplateSpeakers(plainMessage1, speakers);
                }


        } else casesService.caseIncorrect();
    }




}
