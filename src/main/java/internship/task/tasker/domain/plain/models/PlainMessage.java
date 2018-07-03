package internship.task.tasker.domain.plain.models;

import internship.task.tasker.interfaces.PlainMessageInterface;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlainMessage implements PlainMessageInterface{

    private Recipient recipient;

    private Message message;


}
