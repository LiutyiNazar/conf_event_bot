package internship.task.tasker.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Postback {

    private String payload;
    private String title;
}
