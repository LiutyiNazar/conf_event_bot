package internship.task.tasker.domain.plain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.interfaces.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class URLButtonView implements Button {
    @JsonProperty("type")
    private final String type = "web_url";
    private String url;
    private String title;
    @JsonProperty("webview_height_ratio")
    private String webviewHeightRatio = "tall";
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("fallback_url")
    private final String fallbackUrl= "https://jeeconf.com/";
}
