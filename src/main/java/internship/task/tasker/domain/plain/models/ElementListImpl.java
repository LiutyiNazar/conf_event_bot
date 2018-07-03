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
public class ElementListImpl implements Element {

    private String title;
    private String subtitle;
    @JsonProperty("image_url")
    private String imageUrl;
    private List<Button> buttons;
}
