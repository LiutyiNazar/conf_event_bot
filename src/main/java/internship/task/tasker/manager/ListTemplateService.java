package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.Button;
import internship.task.tasker.Interfaces.Element;
import internship.task.tasker.Interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.Interfaces.ListTemplateInterface;
import internship.task.tasker.domain.ElementListImpl;
import internship.task.tasker.domain.Generic.GenericAttachment;
import internship.task.tasker.domain.Generic.GenericMessage;
import internship.task.tasker.domain.Generic.GenericPlainMessage;
import internship.task.tasker.domain.PayloadListImpl;
import internship.task.tasker.domain.PostbackButton;
import internship.task.tasker.domain.URLButtonView;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListTemplateService implements ListTemplateInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;
    @Autowired
    private Environment environment;

    public void createAndSendListOfSessions(GenericPlainMessage plainMessage, List<SessionModel> sessions) {
        List<Element> elements = new ArrayList<>();

        for (SessionModel session : sessions) {
            List<Button> buttons = new ArrayList<>();
            PostbackButton button = new PostbackButton();
            button.setTitle("Speakers");
            button.setPayload("GetSpeakers?id=" + session.getId());
            buttons.add(button);
            ElementListImpl element = ElementListImpl.builder()
                    .imageUrl(environment.getProperty("default_image"))
                    .title(session.getName() + "  " + session.getTime())
                    .subtitle(session.getDescription())
                    .buttons(buttons).build();
            elements.add(element);
        }

        plainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                PayloadListImpl.builder().topElementStyle("compact").elements(elements).build()
                        )
                )
        );
        sender.sendMessage(plainMessage);

    }

    public void createAndSendListOfSpeakers(GenericPlainMessage genericPlainMessage, List<SpeakerModel> speakers) {
        List<Element> elements = new ArrayList<>();

        for (SpeakerModel speaker : speakers) {
            List<Button> buttons = new ArrayList<>();
            PostbackButton button = new PostbackButton();
            button.setTitle("Sessions");
            button.setPayload("GetSessions?id=" + speaker.getId());
            buttons.add(button);
            ElementListImpl element = ElementListImpl.builder()
                    .imageUrl(speaker.getImageUrl())
                    .title(speaker.getFirstName() + " " + speaker.getLastName())
                    .subtitle(speaker.getDescription())
                    .buttons(buttons).build();
            elements.add(element);
        }

        genericPlainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                PayloadListImpl.builder().topElementStyle("compact").elements(elements).build()
                        )
                )
        );
        sender.sendMessage(genericPlainMessage);
    }

    public void sendHelloTab(GenericPlainMessage plainMessage) {
        List<Element> elements = new ArrayList<>();

        Button button1 = PostbackButton.builder().payload("PostbackAddNew").title("Add New").build();
        Button button2 = PostbackButton.builder().payload("PostbackSpeakers").title("Speakers").build();
        Button button3 = PostbackButton.builder().payload("PostbackSessions").title("Sessions").build();
        Button button4 = URLButtonView.builder().messengerExtensions(true).title("JEE-CONF").url("https://jeeconf.com/").build();
        List<Button> buttons1 = new ArrayList<>();
        List<Button> buttons2 = new ArrayList<>();
        List<Button> buttons3 = new ArrayList<>();
        List<Button> buttons4 = new ArrayList<>();


        buttons1.add(button2);
        buttons2.add(button3);
        buttons3.add(button1);
        buttons4.add(button4);

        ElementListImpl element1 = ElementListImpl.builder().buttons(buttons1).
                title("Speakers").
                imageUrl(environment.getProperty("imageUrl")).build();

        ElementListImpl element2 = ElementListImpl.builder()
                .title("Sessions").buttons(buttons2).
                        imageUrl(environment.getProperty("imageUrl")).build();

        ElementListImpl element3 = ElementListImpl.builder().buttons(buttons3).title("Add new").
                imageUrl(environment.getProperty("imageUrl")).build();


        ElementListImpl element4 = ElementListImpl.builder().buttons(buttons4).title("JEE-CONF").
                subtitle("conference").imageUrl(environment.getProperty("imageUrl")).build();

        elements.add(element4);
        elements.add(element1);
        elements.add(element2);
        elements.add(element3);


        plainMessage.setMessage(
                new GenericMessage().setAttachment(
                        new GenericAttachment().setPayload(
                                PayloadListImpl.builder().elements(elements).topElementStyle("large").build()
                        )
                )
        );
        sender.sendMessage(plainMessage);

    }
}
