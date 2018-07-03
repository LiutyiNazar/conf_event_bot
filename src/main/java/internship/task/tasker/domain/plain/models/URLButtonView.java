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
    private static final String TYPE = "web_url";
    private String url;
    private String title;
    @JsonProperty("webview_height_ratio")
    private static final String WEBVIEW_HEIGHT_RATIO = "tall";
    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("fallback_url")
    private static final String FALLBACK_URL = "https://jeeconf.com/";
}
