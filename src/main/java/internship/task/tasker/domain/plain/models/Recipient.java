package internship.task.tasker.domain.plain.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient {
    @JsonProperty("id")
    private String ID;//NOSONAR

}
