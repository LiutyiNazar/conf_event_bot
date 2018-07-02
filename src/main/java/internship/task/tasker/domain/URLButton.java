package internship.task.tasker.domain;

import internship.task.tasker.Interfaces.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class URLButton implements Button{

    private final String type = "web_url";
    private String url;
    private String title;

}
