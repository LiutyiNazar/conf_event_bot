package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DefaultAction {

    private  String type;
    private String url;
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions ;
    @JsonProperty("webview_height_ratio")
    private  String webviewHeightRatio ;

}
