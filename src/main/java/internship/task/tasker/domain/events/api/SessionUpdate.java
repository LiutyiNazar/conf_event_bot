package internship.task.tasker.domain.events.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionUpdate {
    private Integer id;
    private String updateType;
    private String value;
}
