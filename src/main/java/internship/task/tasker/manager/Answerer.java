package internship.task.tasker.manager;

import internship.task.tasker.Interfaces.AnswerInterface;
import internship.task.tasker.Interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.domain.Message;
import internship.task.tasker.domain.PlainMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Лютий on 03.05.2018.
 */
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Answerer implements AnswerInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void sendText(PlainMessage plainMessage, String text) {
        logger.info("Sending text message .......");
        plainMessage.setMessage(Message.builder().text(text).build());
        sender.sendMessage(plainMessage);
    }


}
