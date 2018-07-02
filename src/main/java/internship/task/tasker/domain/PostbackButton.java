package internship.task.tasker.domain;


import internship.task.tasker.Interfaces.Button;
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
    private final String type = "postback";
    private String title ;
    private String payload;
}
