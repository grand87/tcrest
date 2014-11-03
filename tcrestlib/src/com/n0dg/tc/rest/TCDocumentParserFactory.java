package com.n0dg.tc.rest;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;

public class TCDocumentParserFactory {

	public interface DocumentParser {
		Map<String, String> parse(Document aDoc);
	}

	private static HashMap<String, DocumentParser> parsers;

	static {
		parsers = new HashMap<String, DocumentParser>();
		parsers.put(TCProjectsPathParser.TC_RESOURCE, new TCProjectsPathParser());
		parsers.put(TCConfigurationsPathParser.TC_RESOURCE, new TCConfigurationsPathParser());
	}

	public static DocumentParser getParser(String resourcePath)
	{
		if (!parsers.containsKey(resourcePath)) {
			throw new IllegalArgumentException("Invalid resource path or parser not initialized : " + resourcePath);
		}
		return parsers.get(resourcePath);
	}
}
