package internship.task.tasker.manager;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import internship.task.tasker.domain.plain.models.Response;
import internship.task.tasker.interfaces.FacebookResponseMessageInterface;
import internship.task.tasker.interfaces.PlainMessageInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@Slf4j
public class FacebookResponseMessageSender implements FacebookResponseMessageInterface {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment environment;


    @Override
    public void sendMessage(PlainMessageInterface plainMessage) {
        LOGGER.info("Start sending message .....");
        doPost(plainMessage);
        LOGGER.info("Message has been sended");
    }

    private void doPost(Object object) {


        try {
            String json;
            json = objectMapper.writeValueAsString(object);
            LOGGER.info(json);

            restTemplate.postForEntity(Objects.requireNonNull(environment.getProperty("sending_url")),
                    object, Response.class);
        } catch (HttpClientErrorException ex) {
            LOGGER.warn(ex.getStatusText());
            LOGGER.warn(ex.getMessage());
        } catch (JsonProcessingException e) {
            LOGGER.warn("Post", e);
            LOGGER.warn(e.getMessage());
        }

    }

}
