package internship.task.tasker.Interfaces;

import internship.task.tasker.domain.PlainMessage;

public interface ButtonServiceInterface {
    void sendMakerTab(PlainMessage plainMessage);

    void doSendForHelloTab(PlainMessage plainMessage);

    void createNewSpeakerButton(PlainMessage plainMessage);

    void createNewSessionButton(PlainMessage plainMessage);
}