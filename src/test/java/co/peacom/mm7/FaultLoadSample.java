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

import java.io.InputStream;

public class FaultLoadSample {

	public static void main(String[] args) throws Exception {
		InputStream is = FaultLoadSample.class.getResourceAsStream("fault.xml");

		try {
			MM7Message.load(is, "text/xml", new MM7Context());
		} catch (MM7Error e) {
			System.out.println(e.getResponse());
			System.out.println("=== Complete SOAP body ===");
			System.out.println(e);
		}
		
		
		InputStream is2 = FaultLoadSample.class.getResourceAsStream("fault_02.xml");

		try {
			MM7Message.load(is2, "text/xml", new MM7Context());
		} catch (MM7Error e) {
			System.out.println(e.getResponse());
			System.out.println("=== Complete SOAP body ===");
			System.out.println(e);
		}
	}
}