package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.PlainMessage;

public interface AnswerInterface {

    void sendText(PlainMessage plainMessage, String text);
}