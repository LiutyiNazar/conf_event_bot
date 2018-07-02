package internship.task.tasker.Interfaces;

public interface EventsApiUpdateInterface {
    void doUpdateForSession(Integer id, String updateType, String value);

    void doUpdateForSpeaker(Integer id, String updateType, String value);

    void doUpdateForContext(String id, String state);

}
