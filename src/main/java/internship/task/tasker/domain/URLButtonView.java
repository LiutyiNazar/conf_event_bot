package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.Interfaces.Button;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class URLButtonView implements Button {

    private final String type = "web_url";
    private String url;
    private String title;
    @JsonProperty("webview_height_ratio")
    private final String webviewHeightRatio = "tall";
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("fallback_url")
    private final String fallbackUrl = "https://jeeconf.com/";
}
