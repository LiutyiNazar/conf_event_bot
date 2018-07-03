package internship.task.tasker.domain.plain.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WebHookRepresentationJSON {

    private  String object;
    private List<Entry> entry;


}
