package internship.task.tasker.manager;

import internship.task.tasker.domain.plain.models.Message;
import internship.task.tasker.domain.plain.models.PlainMessage;
import internship.task.tasker.domain.plain.models.QuickReply;
import internship.task.tasker.interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.interfaces.QuickRepliesInterface;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Component
public class QuickRepliesService implements QuickRepliesInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;
    @Autowired
    private Environment environment;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendQuickReplyForAddNewTab(PlainMessage plainMessage) {
        logger.info("Received send quick replies for new tab command");
        QuickReply quickReply = QuickReply.builder().title("Add new Speaker").contentType("text").payload("AddNewSpeaker").build();
        QuickReply quickReply1 = QuickReply.builder().title("Add new Session").contentType("text").payload("AddNewSession").build();
        List<QuickReply> quickReplies = new ArrayList<>();
        quickReplies.add(quickReply);
        quickReplies.add(quickReply1);
        plainMessage.setMessage(
                Message.builder().text(environment.getProperty("want_to_add")).quickReplies(quickReplies).build()
        );
        sender.sendMessage(plainMessage);
    }

}
