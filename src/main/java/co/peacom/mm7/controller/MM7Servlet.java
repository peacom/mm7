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

package co.peacom.mm7.controller;

import co.peacom.mm7.*;
import jakarta.servlet.annotation.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serial;

@WebServlet("/mm7")
public class MM7Servlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;


    public VASP getVasp() {
        return vasp;
    }

    public void setVasp(VASP vasp) {
        this.vasp = vasp;
    }

    protected MM7Response dispatch(MM7Request req) throws MM7Error {
        MM7Response resp;
        if (req instanceof DeliverReq) {
            resp = getVasp().deliver((DeliverReq) req);
        } else if (req instanceof DeliveryReportReq) {
            resp = getVasp().deliveryReport((DeliveryReportReq) req);
        } else if (req instanceof ReadReplyReq) {
            resp = getVasp().readReply((ReadReplyReq) req);
        } else {
            log(req.toString());
            throw new MM7Error("method not supported: " + req.getClass());
        }
        return resp;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log("Only HTTP POST supported on this 3GPP MMS MM7 SOAP Endpoint");
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,
                "Only HTTP POST supported on this 3GPP MMS MM7 SOAP Endpoint");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Decode incoming SOAP message
            InputStream in = req.getInputStream();

            MM7Request mm7request;
            try {
                mm7request = (MM7Request) MM7Message.load(in, req.getContentType(), getVasp().getContext());
            } finally {
                in.close();
            }

            // Call a callback on a client
            MM7Response mm7response = dispatch(mm7request);

            // Write out SOAP message
            resp.setContentType(mm7response.getSoapContentType());

            try (OutputStream out = resp.getOutputStream()) {
                MM7Message.save(mm7response, out, getVasp().getContext());
            }
        } catch (MM7Error mm7error) {
            log("MM7 request failed", mm7error);
            // TODO Handle SOAP Faults
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    @Qualifier("SampleSpringVASP")
    public VASP vasp;
}
