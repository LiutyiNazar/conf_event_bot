package internship.task.tasker.manager;

import internship.task.tasker.domain.plain.models.Message;
import internship.task.tasker.domain.plain.models.PlainMessage;
import internship.task.tasker.interfaces.AnswerInterface;
import internship.task.tasker.interfaces.FacebookResponseMessageInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@NoArgsConstructor
@AllArgsConstructor
@Component
@Slf4j
public class Answerer implements AnswerInterface {

    @Autowired
    private FacebookResponseMessageInterface sender;

    @Override
    public void sendText(PlainMessage plainMessage, String text) {
        LOGGER.info("Sending text message .......");
        plainMessage.setMessage(Message.builder().text(text).build());
        sender.sendMessage(plainMessage);
    }


}
