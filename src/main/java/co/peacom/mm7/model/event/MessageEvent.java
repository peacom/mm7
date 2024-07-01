package co.peacom.mm7.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MessageEvent extends Event {
    private String id;
    private String contentType;
    private String url;
    private String content;
    private String title;
    private long timestamp;
}
