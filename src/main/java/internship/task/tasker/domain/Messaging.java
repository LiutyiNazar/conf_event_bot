package internship.task.tasker.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Messaging {

    private Recipient recipient;

    private Sender sender;

    private Message message;

    private String timestamp;

    private Postback postback;

}
