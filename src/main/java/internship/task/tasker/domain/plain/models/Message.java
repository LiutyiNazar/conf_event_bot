package internship.task.tasker.domain.plain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String text;

    private Attachment attachment;
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;



}
