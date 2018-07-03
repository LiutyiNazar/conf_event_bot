package internship.task.tasker.domain.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.interfaces.Payload;


public class GenericAttachment {
    @JsonProperty("TYPE")
    private static final String TYPE = "template";
    private Payload payload;

    public String getType() {
        return TYPE;
    }

    public Payload getPayload() {
        return payload;
    }

    public GenericAttachment setPayload(Payload payload) {
        this.payload = payload;
        return this;
    }
}
