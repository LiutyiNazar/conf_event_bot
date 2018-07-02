package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.Interfaces.Button;
import internship.task.tasker.Interfaces.Element;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElementPlainImpl implements Element{

    private String title;
    @JsonProperty("image_url")
    private String imageUrl;
    private String subtitle;
    @JsonProperty("default_action")
    private DefaultAction defaultAction;
    private List<Button> buttons;
}
