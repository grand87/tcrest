package com.n0dg.tc.rest;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.entity.ContentType;
import org.w3c.dom.Document;

public class ContentParserFactory {
	
	public interface ContentParser {
		Document parse(InputStream stream);
	}

	private static HashMap<String, ContentParser> parsers;
	
	static {
		parsers = new HashMap<String, ContentParser>();
		parsers.put(ContentType.APPLICATION_XML.getMimeType(), new ContentParserXML());
	}

	public static ContentParser getParser(ContentType contentType)
	{
		if (!parsers.containsKey(contentType.getMimeType())) {
			throw new IllegalArgumentException("Invalid content type or parser not initialized : " + contentType.toString());
		}
		return parsers.get(contentType.getMimeType());
	}
}
