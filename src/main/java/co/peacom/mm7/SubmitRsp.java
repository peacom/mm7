/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2007-2014 InstantCom Ltd. All rights reserved.
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

public class SubmitRsp extends MM7Response {

	public String getMessageId() {
		return messageId;
	}

	@Override
	public void load(Element element) {
		super.load(element);

		Element body = element.getChild("Body", MM7Message.ENVELOPE);
		Element rsp = body.getChild("SubmitRsp", namespace);
		setMessageId(rsp.getChildTextTrim("MessageID", namespace));
	}

	@Override
	public Element save(Element parent) {
		Element e = super.save(parent);
		if (messageId != null) {
			e.addContent(new Element("MessageID", e.getNamespace()).setText(messageId));
		}
		return e;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	private String messageId;
}
