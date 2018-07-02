package internship.task.tasker.manager.api.events;


import internship.task.tasker.Interfaces.EventsApiManagingInterface;
import models.ContextModel;
import models.SessionModel;
import models.SpeakerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class EventsApiManagingService implements EventsApiManagingInterface {
    @Autowired
    private Environment environment;

    private RestTemplate restTemplate = new RestTemplate();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public List<SpeakerModel> getSpeakers() {
        List<SpeakerModel> speakers = new ArrayList<>();
        logger.info("Trying to get message from localhost speakers");
        try {
            SpeakerModel[] speakersArray = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("speakers_url")), SpeakerModel[].class);

            assert speakersArray != null;
            speakers = Arrays.asList(speakersArray);
        } catch (HttpClientErrorException ex) {
            logger.warn("GetSpeakers", ex);
            logger.warn(ex.getMessage());
        }

        return speakers;
    }

    public List<SessionModel> getSessions() {
        List<SessionModel> sessions = new ArrayList<>();
        logger.info("Trying to get message from localhost sessions");
        try {
            SessionModel[] sessionModels = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("sessions_url")), SessionModel[].class);
            assert sessionModels != null;
            sessions = Arrays.asList(sessionModels);
        } catch (HttpClientErrorException ex) {
            logger.warn("GetSessions", ex);
            logger.warn(ex.getMessage());
        }

        return sessions;
    }

    public List<SessionModel> getSessionsBySpeakerId(int id) {
        List<SessionModel> sessions = new ArrayList<>();
        logger.info("Trying to get Sessions by Speaker Id");
        try {
            SessionModel[] sessionModels = restTemplate.getForObject(environment.getProperty("speaker_by_id") +id, SessionModel[].class);

            assert sessionModels != null;
            sessions = Arrays.asList(sessionModels);
        } catch (HttpClientErrorException ex) {
            logger.warn("GetSessionsBySpId", ex);
            logger.warn(ex.getMessage());
        }
        return sessions;

    }

    public List<SpeakerModel> getSpeakersBySessionId(int id) {
        List<SpeakerModel> speakers = new ArrayList<>();
        logger.info("trying to get Speakers by Session Id");
        try {
            SpeakerModel[] speakersList = restTemplate.getForObject(environment.getProperty("session_by_id") + id, SpeakerModel[].class);
            assert speakersList != null;
            speakers = Arrays.asList(speakersList);

        } catch (HttpClientErrorException ex) {
            logger.warn("GetSpeakersBySesId", ex);
            logger.warn(ex.getMessage());
        }
        return speakers;
    }

    public List<ContextModel> getContext() {
        List<ContextModel> contextList = new ArrayList<>();
        logger.info("Trying to get all context ");
        try {
            ContextModel[] contextModels = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("context_url")), ContextModel[].class);
            assert contextModels != null;
            contextList = Arrays.asList(contextModels);
        } catch (HttpClientErrorException ex) {
            logger.warn("GetContext", ex);
            logger.warn(ex.getMessage());
        }
        return contextList;
    }

    public ContextModel getContextByRecipientId(String id) {
        logger.info("Trying to get context by recipient id");
        ContextModel contextModel = new ContextModel();
        try {
            contextModel = restTemplate.getForObject(environment.getProperty("context_by_id") + id, ContextModel.class);
            assert contextModel != null;
        } catch (HttpClientErrorException ex) {
            logger.warn("GetContextByRepId", ex);
            logger.warn(ex.getMessage());
        }

        return contextModel;
    }

    public Integer getLastSessionId() {
        Integer anser = null;
        try {
            anser = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("sessions_count")), Integer.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("LastSessionId", ex);
            logger.warn(ex.getMessage());
        }
        return anser;
    }

    public Integer getLastSpeakerId() {
        Integer answer = null;
        try {
            answer = restTemplate.getForObject(Objects.requireNonNull(environment.getProperty("speakers_count")), Integer.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("LastSpeakerId", ex);
            logger.warn(ex.getMessage());
        }
        return answer;
    }
}
