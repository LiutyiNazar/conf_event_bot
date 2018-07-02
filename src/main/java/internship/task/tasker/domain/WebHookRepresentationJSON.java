package internship.task.tasker.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WebHookRepresentationJSON {

    private  String object;
    private List<Entry> entry;


}
