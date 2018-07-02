package internship.task.tasker.manager.api.events;

import internship.task.tasker.Interfaces.EventsApiUpdateInterface;
import internship.task.tasker.domain.events.api.ContextUpdate;
import internship.task.tasker.domain.events.api.SessionUpdate;
import internship.task.tasker.domain.events.api.SpeakerUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class EventsApiUpdateService implements EventsApiUpdateInterface {
    @Autowired
    private Environment environment;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate = new RestTemplate();

    public void doUpdateForSession(Integer id, String updateType, String value) {
        logger.info("Trying to do update for Session ");
        try {
            SessionUpdate sessionUpdate = new SessionUpdate();
            sessionUpdate.setId(id);
            sessionUpdate.setUpdateType(updateType);
            sessionUpdate.setValue(value);
            restTemplate.put(environment.getProperty("session_update"),sessionUpdate);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForSession", ex);
            logger.warn(ex.getMessage());
        }
    }

    public void doUpdateForSpeaker(Integer id, String updateType, String value) {
        logger.info("Trying to do update for Speaker");
        try {
            SpeakerUpdate speakerUpdate = new SpeakerUpdate();
            speakerUpdate.setId(id);
            speakerUpdate.setUpdateType(updateType);
            speakerUpdate.setValue(value);
            restTemplate.put(environment.getProperty("speaker_update"), speakerUpdate);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForSpeaker", ex);
            logger.warn(ex.getMessage());
        }
    }

    public void doUpdateForContext(String id, String state) {
        try {
            ContextUpdate contextUpdate = new ContextUpdate();
            contextUpdate.setId(id);
            contextUpdate.setState(state);
            restTemplate.put(environment.getProperty("context_update"), contextUpdate);
            //restTemplate.getForObject(environment.getProperty("update_context") + id + "&state=" + state, ContextModel.class);
        } catch (HttpClientErrorException ex) {
            logger.warn("UpdForContext", ex);
            logger.warn(ex.getMessage());
        }
    }


}
