package internship.task.tasker.domain.plain.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

//DO NOT USE HERE ANNOTATTIONS of LOMBOK, CAUSE IT WON`T WORK WITH THEM!!!
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipient {
    @JsonProperty("id")
    private String ID;

}
