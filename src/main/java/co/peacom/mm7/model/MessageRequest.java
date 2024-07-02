package co.peacom.mm7.model;

import co.peacom.mm7.MessageClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class MessageRequest {
    @NotNull
    private String contentType;
    @NotBlank
    private String phone;
    private String content;
    private String mediaUrl;
    private String title;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String vaspId;
    private String vasId;
    @NotBlank
    @URL
    private String url;
    private MessageClass messageClass;
    private String serviceCode;
    private String linkedId;

    public MessageClass getMessageClass() {
        return messageClass;
    }

    public void setMessageClass(MessageClass messageClass) {
        this.messageClass = messageClass;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(String linkedId) {
        this.linkedId = linkedId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVaspId() {
        return vaspId;
    }

    public void setVaspId(String vaspId) {
        this.vaspId = vaspId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVasId() {
        return vasId;
    }

    public void setVasId(String vasId) {
        this.vasId = vasId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
