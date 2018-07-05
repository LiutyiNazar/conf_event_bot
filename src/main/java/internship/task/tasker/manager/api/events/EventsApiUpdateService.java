package internship.task.tasker.manager.api.events;

import internship.task.tasker.domain.events.api.ContextUpdate;
import internship.task.tasker.domain.events.api.SessionUpdate;
import internship.task.tasker.domain.events.api.SpeakerUpdate;
import internship.task.tasker.interfaces.EventsApiUpdateInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class EventsApiUpdateService implements EventsApiUpdateInterface {
    @Autowired
    private Environment environment;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void doUpdateForSession(Integer id, String updateType, String value) {
        LOGGER.info("Trying to do update for Session ");
        try {
            SessionUpdate sessionUpdate = new SessionUpdate();
            sessionUpdate.setId(id);
            sessionUpdate.setUpdateType(updateType);
            sessionUpdate.setValue(value);
            restTemplate.put(environment.getProperty("session_update"), sessionUpdate);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("UpdForSession", ex);
            LOGGER.warn(ex.getMessage());
        }
    }

    @Override
    public void doUpdateForSpeaker(Integer id, String updateType, String value) {
        LOGGER.info("Trying to do update for Speaker");
        try {
            SpeakerUpdate speakerUpdate = new SpeakerUpdate();
            speakerUpdate.setId(id);
            speakerUpdate.setUpdateType(updateType);
            speakerUpdate.setValue(value);
            restTemplate.put(environment.getProperty("speaker_update"), speakerUpdate);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("UpdForSpeaker", ex);
            LOGGER.warn(ex.getMessage());
        }
    }

    @Override
    public void doUpdateForContext(String id, String state) {
        try {
            ContextUpdate contextUpdate = new ContextUpdate();
            contextUpdate.setId(id);
            contextUpdate.setState(state);
            restTemplate.put(environment.getProperty("context_update"), contextUpdate);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn("UpdForContext", ex);
            LOGGER.warn(ex.getMessage());
        }
    }


}
