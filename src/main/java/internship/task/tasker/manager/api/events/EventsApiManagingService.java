package internship.task.tasker.manager.api.events;


import internship.task.tasker.interfaces.EventsApiManagingInterface;
import lombok.extern.slf4j.Slf4j;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class EventsApiManagingService implements EventsApiManagingInterface {
    @Autowired
    private Environment environment;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<SpeakerModel> getSpeakers() {
        List<SpeakerModel> speakers = new ArrayList<>();
        LOGGER.info("Trying to get message from localhost speakers");
        try {
            SpeakerModel[] speakersArray = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("speakers_url")), SpeakerModel[].class);

            assert speakersArray != null;
            speakers = Arrays.asList(speakersArray);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetSpeakers", ex);
            LOGGER.warn(ex.getMessage());
        }

        return speakers;
    }

    @Override
    public List<SessionModel> getSessions() {
        List<SessionModel> sessions = new ArrayList<>();
        LOGGER.info("Trying to get message from localhost sessions");
        try {
            SessionModel[] sessionModels = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("sessions_url")), SessionModel[].class);
            assert sessionModels != null;
            sessions = Arrays.asList(sessionModels);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetSessions", ex);
            LOGGER.warn(ex.getMessage());
        }

        return sessions;
    }

    @Override
    public List<SessionModel> getSessionsBySpeakerId(int id) {
        List<SessionModel> sessions = new ArrayList<>();
        LOGGER.info("Trying to get Sessions by Speaker Id");
        try {
            SessionModel[] sessionModels = restTemplate.getForObject(environment.getProperty("speaker_by_id") + id, SessionModel[].class);

            assert sessionModels != null;
            sessions = Arrays.asList(sessionModels);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetSessionsBySpId", ex);
            LOGGER.warn(ex.getMessage());
        }
        return sessions;

    }

    @Override
    public List<SpeakerModel> getSpeakersBySessionId(int id) {
        List<SpeakerModel> speakers = new ArrayList<>();
        LOGGER.info("trying to get Speakers by Session Id");
        try {
            SpeakerModel[] speakersList = restTemplate.getForObject(environment.getProperty("session_by_id") + id, SpeakerModel[].class);
            assert speakersList != null;
            speakers = Arrays.asList(speakersList);

        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetSpeakersBySesId", ex);
            LOGGER.warn(ex.getMessage());
        }
        return speakers;
    }

    @Override
    public List<ContextModel> getContext() {
        List<ContextModel> contextList = new ArrayList<>();
        LOGGER.info("Trying to get all context ");
        try {
            ContextModel[] contextModels = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("context_url")), ContextModel[].class);
            assert contextModels != null;
            contextList = Arrays.asList(contextModels);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetContext", ex);
            LOGGER.warn(ex.getMessage());
        }
        return contextList;
    }

    @Override
    public ContextModel getContextByRecipientId(String id) {
        LOGGER.info("Trying to get context by recipient id");
        ContextModel contextModel = new ContextModel();
        try {
            contextModel = restTemplate.getForObject(environment.getProperty("context_by_id") + id, ContextModel.class);
            assert contextModel != null;
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("GetContextByRepId", ex);
            LOGGER.warn(ex.getMessage());
        }

        return contextModel;
    }

    @Override
    public Integer getLastSessionId() {
        Integer anser = null;
        try {
            anser = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("sessions_count")), Integer.class);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("LastSessionId", ex);
            LOGGER.warn(ex.getMessage());
        }
        return anser;
    }

    @Override
    public Integer getLastSpeakerId() {
        Integer answer = null;
        try {
            answer = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("speakers_count")), Integer.class);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("LastSpeakerId", ex);
            LOGGER.warn(ex.getMessage());
        }
        return answer;
    }
}
