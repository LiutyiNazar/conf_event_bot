package internship.task.tasker.domain.plain.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.interfaces.Button;
import lombok.*;

/**
 * Created by Лютий on 07.05.2018.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostbackButton implements Button{
    @JsonProperty("type")
    private final String type = "postback";
    private String title ;
    private String payload;
}
