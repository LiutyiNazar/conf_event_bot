package internship.task.tasker.domain.plain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.interfaces.Button;
import internship.task.tasker.interfaces.Element;
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
