package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {

    @JsonProperty("messaging_type")
    private String messagingType;

    private Recipient recipient;

    private Message message;


}
