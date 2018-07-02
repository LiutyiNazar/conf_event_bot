package internship.task.tasker.domain.Generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import internship.task.tasker.domain.QuickReply;

import java.util.List;

public class GenericMessage {
    private String text;

    private GenericAttachment attachment;
    @JsonProperty("quick_replies")
    private List<QuickReply> quickReplies;

    public String getText() {
        return text;
    }

    public GenericMessage setText(String text) {
        this.text = text;
        return this;
    }

    public GenericAttachment getAttachment() {
        return attachment;
    }

    public GenericMessage setAttachment(GenericAttachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public List<QuickReply> getQuickReplies() {
        return quickReplies;
    }

    public GenericMessage setQuickReplies(List<QuickReply> quickReplies) {
        this.quickReplies = quickReplies;
        return this;
    }
}
