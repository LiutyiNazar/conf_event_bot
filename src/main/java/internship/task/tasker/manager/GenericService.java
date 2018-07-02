package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.Button;
import internship.task.tasker.Interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.Interfaces.GenericInterface;
import internship.task.tasker.domain.ElementPlainImpl;
import internship.task.tasker.domain.Generic.GenericAttachment;
import internship.task.tasker.domain.Generic.GenericMessage;
import internship.task.tasker.domain.Generic.GenericPayload;
import internship.task.tasker.domain.Generic.GenericPlainMessage;
import internship.task.tasker.domain.PostbackButton;
import internship.task.tasker.domain.Recipient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class GenericService implements GenericInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;

    public void createAndSendGenericOfSessions(GenericPlainMessage plainMessage, List<SessionModel> sessions) {

        List<ElementPlainImpl> elements = new ArrayList<>();

        for (SessionModel session : sessions) {
            Button button = PostbackButton.builder().title("Speakers")
                    .payload("GetSpeakers?id=" + session.getId()).build();
            List<Button> buttons = new ArrayList<>();
            buttons.add(button);
            ElementPlainImpl element = new ElementPlainImpl();
            element.setTitle(session.getName() + " " + session.getTime());
            element.setSubtitle(session.getDescription());
            element.setButtons(buttons);
            elements.add(element);
            plainMessage.setMessage(
                    new GenericMessage().setAttachment(
                            new GenericAttachment().setPayload(
                                    new GenericPayload().setTemplateType("generic").setElements(elements)
                            )
                    )
            );

        }
        sender.sendMessage(plainMessage);
    }

    public void createAndSendGenericOfSpeakers(GenericPlainMessage genericPlainMessage, List<SpeakerModel> speakers) {

        List<ElementPlainImpl> elements = new ArrayList<>();

        for (SpeakerModel speaker : speakers) {

            Button button = PostbackButton.builder().title("Sessions")
                    .payload("GetSessions?id=" + speaker.getId()).build();

            List<Button> buttons = new ArrayList<>();
            buttons.add(button);

            ElementPlainImpl element = new ElementPlainImpl();
            element.setTitle(speaker.getFirstName() + " " + speaker.getLastName());
            element.setSubtitle(speaker.getDescription());
            element.setImageUrl(speaker.getImageUrl());
            element.setButtons(buttons);
            elements.add(element);
        }
        genericPlainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                new GenericPayload().setTemplateType("generic").setElements(elements)
                        )
                )
        );
        sender.sendMessage(genericPlainMessage);
    }

    public GenericPlainMessage defineRecipientForGenericPlainMessage(String recipientId) {
        GenericPlainMessage plainMessage1 = new GenericPlainMessage();
        return plainMessage1.setRecipient(Recipient.builder().ID(recipientId).build());
    }

    public void sendSpeakersGenericToChoose(String recipientId, List<SpeakerModel> speakers) {
        List<ElementPlainImpl> elements = new ArrayList<>();
        GenericPlainMessage genericPlainMessage = new GenericPlainMessage();
        genericPlainMessage.setRecipient(Recipient.builder().ID(recipientId).build());
        for (SpeakerModel speaker : speakers) {

            Button button = PostbackButton.builder().title("Add")
                    .payload("AddSpeaker?id=" + speaker.getId()).build();

            List<Button> buttons = new ArrayList<>();
            buttons.add(button);

            ElementPlainImpl element = new ElementPlainImpl();
            element.setTitle(speaker.getFirstName() + " " + speaker.getLastName());
            element.setSubtitle(speaker.getDescription());
            element.setImageUrl(speaker.getImageUrl());
            element.setButtons(buttons);
            elements.add(element);
        }
        genericPlainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                new GenericPayload().setTemplateType("generic").setElements(elements)
                        )
                )
        );
        sender.sendMessage(genericPlainMessage);
    }

    public void sendSessionsGenericToChoose(String recipientId, List<SessionModel> sessions) {
        List<ElementPlainImpl> elements = new ArrayList<>();
        GenericPlainMessage plainMessage = new GenericPlainMessage();
        plainMessage.setRecipient(Recipient.builder().ID(recipientId).build());

        for (SessionModel session : sessions) {
            Button button = PostbackButton.builder().title("Add")
                    .payload("AddSpeaker?id=" + session.getId()).build();
            List<Button> buttons = new ArrayList<>();
            buttons.add(button);
            ElementPlainImpl element = new ElementPlainImpl();
            element.setTitle(session.getName() + " " + session.getTime());
            element.setSubtitle(session.getDescription());
            element.setButtons(buttons);
            elements.add(element);
        }

        plainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                new GenericPayload().setTemplateType("generic").setElements(elements)
                        )
                )
        );


        sender.sendMessage(plainMessage);
    }

}
