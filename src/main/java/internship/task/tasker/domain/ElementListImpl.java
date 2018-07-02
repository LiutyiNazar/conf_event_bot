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
public class ElementListImpl implements Element {

    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageUrl;
    private List<Button> buttons;
}
