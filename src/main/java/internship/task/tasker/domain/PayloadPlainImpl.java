package internship.task.tasker.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.Interfaces.Button;
import internship.task.tasker.Interfaces.Payload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class PayloadPlainImpl implements Payload {
    @JsonProperty("template_type")
    private String templateType;

    private String text;

    private List<Button> buttons;


}
