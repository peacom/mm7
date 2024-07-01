package co.peacom.mm7.services;

import co.peacom.mm7.*;
import co.peacom.mm7.model.event.StatusEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component("SampleSpringVASP")
public class SampleSpringVASP implements VASP {
    @Value("${webhook.url}")
    String webHookUrl;
    private final MM7Context context;

    public SampleSpringVASP(MM7Context context) {
        this.context = context;
    }

    public void sendWebHook(StatusEvent event) {
        RestClient restClient = RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .build();
        restClient.post()
                .uri(webHookUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(event)
                .retrieve().toBodilessEntity();
    }

    @Override
    public DeliverRsp deliver(DeliverReq deliverReq) {
        System.out.println("deliver in VASP was called: ");

        return deliverReq.reply();
    }

    @Override
    public MM7Context getContext() {
        return context;
    }

    @Override
    public DeliveryReportRsp deliveryReport(DeliveryReportReq deliveryReportReq) {
        System.out.println("deliveryReport in VASP was called");
        StatusEvent statusEvent = new StatusEvent(deliveryReportReq);
        System.out.println(statusEvent);
        sendWebHook(statusEvent);
        return deliveryReportReq.reply();
    }

    @Override
    public ReadReplyRsp readReply(ReadReplyReq readReplyReq) {
        System.out.println("readReplyReq in VASP was called");
        StatusEvent statusEvent = new StatusEvent(readReplyReq);
        System.out.println(statusEvent);
        sendWebHook(statusEvent);

        return readReplyReq.reply();
    }
}
