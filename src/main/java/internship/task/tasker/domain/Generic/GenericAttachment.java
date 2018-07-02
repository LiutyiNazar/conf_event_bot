package internship.task.tasker.domain.Generic;

import internship.task.tasker.Interfaces.Payload;


public class GenericAttachment {
    private static final String type = "template";
    private Payload payload;

    public String getType() {
        return type;
    }

    public Payload getPayload() {
        return payload;
    }

    public GenericAttachment setPayload(Payload payload) {
        this.payload = payload;
        return this;
    }
}
