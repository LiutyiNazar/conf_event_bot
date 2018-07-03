package internship.task.tasker.interfaces;

import internship.task.tasker.domain.plain.models.PlainMessage;

public interface AnswerInterface {

    void sendText(PlainMessage plainMessage, String text);
}