package internship.task.tasker.domain.plain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.interfaces.Button;
import internship.task.tasker.interfaces.Element;
import internship.task.tasker.interfaces.Payload;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PayloadListImpl implements Payload {
    @JsonProperty("template_type")
    private static final String TEMPLATE_TYPE = "list";
    @JsonProperty("top_element_style")
    private String topElementStyle = "compact";

    private List<Element> elements;

    private List<Button> buttons;
}
