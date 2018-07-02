package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.Interfaces.Button;
import internship.task.tasker.Interfaces.Element;
import internship.task.tasker.Interfaces.Payload;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PayloadListImpl implements Payload {
    @JsonProperty("template_type")
    private final String templateType = "list";
    @JsonProperty("top_element_style")
    private String topElementStyle = "compact";

    private List<Element> elements;

    private List<Button> buttons;
}
