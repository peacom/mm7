package co.peacom.mm7.model.event;

import co.peacom.mm7.DeliveryReportReq;
import co.peacom.mm7.ReadReplyReq;
import co.peacom.mm7.model.MessageStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class StatusEvent extends Event {
    private String messageId;
    private MessageStatus status;
    private String description;
    private long timestamp;

    public StatusEvent(DeliveryReportReq delivered) {
        this.messageId = delivered.getMessageID();
        this.status = MessageStatus.DELIVERED;
        this.timestamp = delivered.getDate().getTime();
        this.description = delivered.getStatusText();
    }

    public StatusEvent(ReadReplyReq delivered) {
        this.messageId = delivered.getMessageID();
        this.status = MessageStatus.DELIVERED;
        this.timestamp = delivered.getTimeStamp().getTime();
        this.description = delivered.getStatusText();
    }
}
