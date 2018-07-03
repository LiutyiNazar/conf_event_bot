package internship.task.tasker.manager;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task.tasker.domain.plain.models.Response;
import internship.task.tasker.interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.interfaces.PlainMessageInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class FacebookResponseMessageSender implements FacebookResponseMessageInterface {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendMessage(PlainMessageInterface plainMessage) {
        logger.info("Start sending message .....");
        doPost(plainMessage);
        logger.info("Message has been sended");
    }

    private void doPost(Object object) {


        try {
            String json;
            json = objectMapper.writeValueAsString(object);
            logger.info(json);

            restTemplate.postForEntity(Objects.requireNonNull(environment.getProperty("sending_url")),
                    object, Response.class);
        } catch (HttpClientErrorException ex) {
            logger.warn(ex.getStatusText());
            logger.warn(ex.getMessage());
            throw new RuntimeException(ex);
        } catch (JsonProcessingException e) {
            logger.warn("Post", e);
            logger.warn(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
