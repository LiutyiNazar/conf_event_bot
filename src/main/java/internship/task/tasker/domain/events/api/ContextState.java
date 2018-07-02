package internship.task.tasker.domain.events.api;

public enum ContextState {

    SET_SESSION_NAME("SetSessionName"),
    SET_SESSION_NAME_INSIDE("SetSessionNameInside"),
    SET_SESSION_TIME("SetSessionTime"),
    SET_SESSION_DESCRIPTION("SetSessionDescription"),
    SET_SESSION_SPEAKERS("SetSessionSpeaker"),
    ENDED("Ended"),
    SET_SPEAKER_FIRST_NAME("SetSpeakerFirstName"),
    SET_SPEAKER_FIRST_NAME_INSIDE("SetSpeakerFirstNameInside"),
    SET_SPEAKER_LAST_NAME("SetSpeakerLastName"),
    SET_SPEAKER_IMAGE_URL("SetSpeakerImageUrl"),
    SET_SPEAKER_EMAIL("SetSpeakerEmail"),
    SET_SPEAKER_DESCRIPTION("SetSpeakerDescription"),
    SET_SPEAKER_SESSIONS("SetSpeakerSessions");

    private String value;

    ContextState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
