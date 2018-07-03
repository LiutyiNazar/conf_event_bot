package internship.task.tasker.domain.plain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Attachment {

    private String type;
    private PayloadPlainImpl payload;

}
