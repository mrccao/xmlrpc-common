/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.xmlrpc.serializer;

import java.io.OutputStream;
import java.io.StringWriter;

import org.apache.ws.commons.serialize.CharSetXMLWriter;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.XmlRpcStreamConfig;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.AttributesImpl;


/** The default implementation of {@link XmlWriterFactory}
 * tests, whether the {@link CharSetXmlWriterFactory}
 * is usable. This is the case, when running in Java 1.4 or later. If so,
 * this factory is used. Otherwise, the
 * {@link BaseXmlWriterFactory} is used as a
 * fallback.
 */
public class DefaultXMLWriterFactory implements XmlWriterFactory {
	private final XmlWriterFactory factory;

	/** Creates a new instance.
	 */
	public DefaultXMLWriterFactory() {
		XmlWriterFactory xwf;
		try {
			CharSetXMLWriter csw = new CharSetXMLWriter();
			StringWriter sw = new StringWriter();
			csw.setWriter(sw);
			csw.startDocument();
			csw.startElement("", "test", "test", new AttributesImpl());
			csw.endElement("", "test", "test");
			csw.endDocument();
			xwf = new CharSetXmlWriterFactory();
		} catch (Throwable t) {
			xwf = new BaseXmlWriterFactory();
		}
		factory = xwf;
	}

	public ContentHandler getXmlWriter(XmlRpcStreamConfig pConfig,
									   OutputStream pStream) throws XmlRpcException {
		return factory.getXmlWriter(pConfig, pStream);
	}
}
