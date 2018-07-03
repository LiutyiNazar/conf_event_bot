package internship.task.tasker.domain.plain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Postback {

    private String payload;
    private String title;
}
