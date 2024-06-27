/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 Portalify Ltd. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://raw.github.com/vnesek/instantcom-mm7/master/LICENSE.txt
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at appropriate location.
 */

package co.peacom.mm7;

import org.jdom2.Element;

import java.util.Date;

public class ReadReplyReq extends MM7Request {
    private String messageID;
    private Address recipient;
    private Address sender;
    private Date timeStamp;
    private String mmStatus;
    private String statusText;

    @Override
    public void load(Element element) {
        super.load(element);

        Element body = element.getChild("Body", MM7Message.ENVELOPE);
        Element req = body.getChild("ReadReplyReq", namespace);

        setMm7Version(req.getChildTextTrim("MM7Version", namespace));
        setMessageID(req.getChildTextTrim("MessageID", namespace));

        setRecipient(extractRecipient(req.getChild("Recipient", namespace)));

        Element sender = req.getChild("Sender", namespace);
        if (sender != null) {
            Address a = new Address();
            a.load((Element) sender.getChildren().get(0));
            setSender(a);
        } else {
            setSender(null);
        }

        setTimeStamp(new RelativeDate(req.getChildTextTrim("TimeStamp", namespace)).toDate());
        setMmStatus(req.getChildTextTrim("MMStatus", namespace));
        setStatusText(req.getChildTextTrim("StatusText", namespace));
    }

    private Address extractRecipient(Element element) {
        if (element != null) {
            Element recipientTo = element.getChild("To", namespace);
            if (recipientTo != null) {
                Address address = new Address();
                address.load(recipientTo.getChildren().get(0));
                return address;
            } else {
                Address address = new Address();
                address.load(element.getChildren().get(0));
                return address;
            }
        }
        return null;
    }

    @Override
    public ReadReplyRsp reply() {
    	ReadReplyRsp response = new ReadReplyRsp();
        response.setMm7Version(getMm7Version());
        response.setNamespace(getNamespace());
        response.setTransactionId(getTransactionId());
        response.setStatusCode(MM7Response.SC_SUCCESS);
        return response;
    }

    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public void setRecipient(Address recipient) {
        this.recipient = recipient;
    }

    public void setSender(Address sender) {
        this.sender = sender;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setMmStatus(String mmStatus) {
        this.mmStatus = mmStatus;
    }

    public String getMessageID() {
        return messageID;
    }

    public Address getRecipient() {
        return recipient;
    }

    public Address getSender() {
        return sender;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMmStatus() {
        return mmStatus;
    }

    public String getStatusText() {
        return statusText;
    }
}
