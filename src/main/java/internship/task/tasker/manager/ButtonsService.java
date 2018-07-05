package internship.task.tasker.manager;

import internship.task.tasker.domain.plain.models.*;
import internship.task.tasker.interfaces.Button;
import internship.task.tasker.interfaces.ButtonServiceInterface;
import internship.task.tasker.interfaces.FacebookResponseMessageInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ButtonsService implements ButtonServiceInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;

    @Autowired
    private Environment environment;

    private final String template = "template";

    private final String button = "button";

    @Override
    public void sendMakerTab(PlainMessage plainMessage) {
        //creating and sending URL buttons

        LOGGER.info("Received Maker Tab command");
        Button button1 = URLButton.builder().url(environment.getProperty("company_url")).
                title("BotsCrew").build();


        List<Button> buttonses = new ArrayList<>();

        buttonses.add(button1);

        plainMessage.setMessage(
                Message.builder().text(null).attachment(
                        Attachment.builder().type("template").payload(
                                PayloadPlainImpl.builder().
                                        templateType("button").text(
                                        environment.getProperty("company_text")).
                                        buttons(buttonses).build()).
                                build()
                ).build());

        sender.sendMessage(plainMessage);
    }

    @Override
    public void doSendForHelloTab(PlainMessage plainMessage) {

        LOGGER.info("Received Hello Tab command");

        Button button1 = PostbackButton.builder().payload("PostbackAddNew").title("Add New").build();
        Button button2 = PostbackButton.builder().payload("PostbackSpeakers").title("Speakers").build();
        Button button3 = PostbackButton.builder().payload("PostbackSessions").title("Sessions").build();

        List<Button> buttonses = new ArrayList<>();


        buttonses.add(button2);
        buttonses.add(button3);
        buttonses.add(button1);

        plainMessage.setMessage(
                Message.builder().text(null).attachment(
                        Attachment.builder().type(template).payload(
                                PayloadPlainImpl.builder().templateType(button).
                                        text(environment.getProperty("HelloTabText")).buttons(buttonses).build()).
                                build()
                ).build());

        sender.sendMessage(plainMessage);
    }

    @Override
    public void createNewSpeakerButton(PlainMessage plainMessage) {

        Button addNew = PostbackButton.builder().title("Create new and add").payload("AddSpeaker?id=0").build();
        Button skip = PostbackButton.builder().title("Skip").payload("AddSession?id=-1").build();
        List<Button> buttons = new ArrayList<>();

        buttons.add(addNew);
        buttons.add(skip);
        plainMessage.setMessage(
                Message.builder().text(null).attachment(
                        Attachment.builder().type(template).payload(
                                PayloadPlainImpl.builder().templateType(button).
                                        text("You also can add create new Speaker, and it will be added to your Session").buttons(buttons).build()).
                                build()
                ).build());

        sender.sendMessage(plainMessage);
    }

    @Override
    public void createNewSessionButton(PlainMessage plainMessage) {
        Button addNew = PostbackButton.builder().title("Create new and add").payload("AddSession?id=0").build();
        Button skip = PostbackButton.builder().title("Skip").payload("AddSession?id=-1").build();
        List<Button> buttons = new ArrayList<>();

        buttons.add(addNew);
        buttons.add(skip);
        plainMessage.setMessage(
                Message.builder().text(null).attachment(
                        Attachment.builder().type(template).payload(
                                PayloadPlainImpl.builder().templateType(button).
                                        text("You also can create new Session, and it will be added to your Speaker\n Or, you are able to skip this step").buttons(buttons).build()).
                                build()
                ).build());

        sender.sendMessage(plainMessage);
    }


}

