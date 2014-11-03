package com.n0dg.tc.rest;

import java.io.IOException;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.w3c.dom.Document;

public class TCServer {

	private RequestsProviderTC8 requestsProvider;
	private Credentials credentials;
	private AuthScope authScope;

	public TCServer(String tcHost, int tcPort) {
		requestsProvider = new RequestsProviderTC8(tcHost, tcPort);
		authScope = new AuthScope(tcHost, tcPort);
	}
	
	public void setCredentials(Credentials aCredentials) {
		credentials = aCredentials;
		requestsProvider.setHasCredentials(credentials != null);
	}
	
	public Map<String, String> getProjects() {
		//generate request
		String url = requestsProvider.getProjects();
		Document tcResult = getDocument(url);

		Map<String, String> result = null;
		if(tcResult != null ) {
			// parser xml doc to return requested info
			result = TCDocumentParserFactory.getParser(TCProjectsPathParser.TC_RESOURCE).parse(tcResult);
		}
		return result;
	}

	public Map<String, String> getConfigurations(String aProjectName) {
		String url = requestsProvider.getConfigurations(aProjectName);

		Map<String, String> result = null;

		if (url.length() > 0) {
			Document tcResult = getDocument(url);

			if (tcResult != null) {
				result = TCDocumentParserFactory.getParser(TCConfigurationsPathParser.TC_RESOURCE).parse(tcResult);
			}
		}
		return result;
	}

	private Document getDocument(String url) {
		Executor reqExec = Executor.newInstance().auth(authScope, credentials);

		// encode url for http

		Request req = Request.Get(url);
		Document result = null;
		try {
			result = reqExec.execute(req).handleResponse(new TCResponseHandler());
		} catch (ClientProtocolException e) {
			new IllegalStateException("HTTP error", e);
		} catch (IOException e) {
			new IllegalStateException("Network IO error", e);
		}
		return result;
	}
}
