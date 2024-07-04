package co.peacom.mm7.services;

import co.peacom.mm7.*;
import co.peacom.mm7.model.MessageRequest;
import co.peacom.mm7.model.MessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class MessageService {
    public MessageResponse sendMessage(MessageRequest messageRequest) throws MM7Error, IOException {
        MessageResponse rs;
        SubmitReq sr = new SubmitReq();
        sr.setVaspId(messageRequest.getVaspId());
        sr.setVasId(messageRequest.getVasId());
        sr.setServiceCode(messageRequest.getServiceCode());
        sr.setSubject(messageRequest.getTitle());
        if (messageRequest.getMessageClass() != null) {
            sr.setMessageClass(messageRequest.getMessageClass());
        } else {
            sr.setMessageClass(MessageClass.INFORMATIONAL);
        }

        sr.addRecipient(new Address(messageRequest.getPhone(), Address.RecipientType.TO));

        BasicContent basicContent = new BasicContent(new ArrayList<>());
        TextContent text;
        if (StringUtils.hasText(messageRequest.getContent())) {
            text = new TextContent(messageRequest.getContent());
            text.setContentId("text");
            basicContent.getParts().add(text);
        }

        if (!messageRequest.getContentType().equals("text/plain") && StringUtils.hasText(messageRequest.getMediaUrl())) {
            try {
                InputStream input = new URL(messageRequest.getMediaUrl()).openStream();
                BinaryContent image = new BinaryContent(messageRequest.getContentType(), input);
                image.setContentId(UUID.randomUUID().toString());
                basicContent.getParts().add(image);
            } catch (IOException e) {
                throw new MM7Error("Open url " + messageRequest.getMediaUrl() + " failed");
            }
        }
        // Add text content
        if (basicContent.getParts().isEmpty()) {
            throw new Error("Invalid message, content or url required");
        }
        sr.setContent(basicContent);

        // Initialize MM7 client to MMSC
        MMSC mmsc = new BasicMMSC(messageRequest.getUrl());
        mmsc.getContext().setMm7Namespace("http://www.3gpp.org/ftp/Specs/archive/23_series/23.140/schema/REL-5-MM7-1-3");
        mmsc.getContext().setMm7Version("5.3.0");
        mmsc.getContext().setUsername(messageRequest.getUsername());
        mmsc.getContext().setPassword(messageRequest.getPassword());

        // Send a message
        SubmitRsp submitRsp = mmsc.submit(sr);
        rs = new MessageResponse();
        rs.setId(submitRsp.getMessageId());
        rs.setStatusCode(submitRsp.getStatusCode());
        rs.setStatusText(submitRsp.getStatusText());
        rs.setDetails(submitRsp.getDetails());
        rs.setTransactionId(submitRsp.getTransactionId());
        return rs;
    }
}
