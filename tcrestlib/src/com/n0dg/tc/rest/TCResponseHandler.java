package com.n0dg.tc.rest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;
import org.w3c.dom.Document;

import com.n0dg.tc.rest.ContentParserFactory.ContentParser;

public class TCResponseHandler implements ResponseHandler<Document> {
	public Document handleResponse(final HttpResponse response) throws IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(
			                statusLine.getStatusCode(),
			                statusLine.getReasonPhrase());
		}
		if (entity == null) {
			throw new ClientProtocolException("Response contains no content");
		}

		ContentType contentType = ContentType.getOrDefault(entity);
		
		if (!contentType.getMimeType().equals(ContentType.APPLICATION_XML.getMimeType()) &&
            !contentType.getMimeType().equals(ContentType.TEXT_PLAIN.getMimeType())) {
			throw new ClientProtocolException("Unexpected content type:" + contentType);
		}

		ContentParser parser = ContentParserFactory.getParser(contentType);
		
		if(parser != null) {
			return parser.parse(entity.getContent());
		}

		return null;
	}
}
