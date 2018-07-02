package internship.task.tasker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class QuickReply {
    @JsonProperty("content_type")
    private String contentType;
    private String title;
    private String payload;
    @JsonProperty("image_url")
    private  String imageUrl;




}
