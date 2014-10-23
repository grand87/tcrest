package com.n0dg.tc.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.n0dg.tc.rest.ContentParserFactory.ContentParser;

public class ContentParserXML implements ContentParser {

	@Override
	public Document parse(InputStream stream) {
		Document result = null;
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = dbfac.newDocumentBuilder();

			InputStreamReader reader = new InputStreamReader(stream);
			InputSource source = new InputSource(reader);
			source.setEncoding("UTF-8");
			result = docBuilder.parse(source);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Invalid Parser configuration", e);
		} catch (SAXException e) {
			throw new IllegalStateException("Malformed XML document", e);
		} catch (IOException e) {
			throw new IllegalStateException("Parse exception", e);
        }
		return result;
	}

}
