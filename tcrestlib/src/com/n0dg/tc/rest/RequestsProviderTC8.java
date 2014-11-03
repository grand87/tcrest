package com.n0dg.tc.rest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

class RequestsProviderTC8 {

	private String host;
	private int port;
	private boolean hasCredentials;

	RequestsProviderTC8(String aHost, int aPort) {
		setHost(aHost);
		setPort(aPort);
	}

	void setHasCredentials(boolean aHasCredentials) {
		hasCredentials = aHasCredentials;
	}

	String getProjects() {
		return "http://" + getHost() + ":" + getPort() + "/" + getAuthType() + "app/rest/projects";
	}

	public String getConfigurations(String aProjectName) {
		/*
		 * List of Build Configurations of a project:
		 * GET http://teamcity:8111/httpAuth/app/rest/projects/<projectLocator>/buildTypes
		 */
		
		try {
			URL url = new URL("http://" + getHost() + ":" + getPort() + "/" + getAuthType() + "app/rest/projects/" +
			                getProjectLocator(aProjectName) + "/buildTypes");

			URI uri = new URI(url.getProtocol(), null, url.getHost(), url.getPort(), url.getPath(), url.getQuery(), null);
			return uri.toString();
		} catch (URISyntaxException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }
	
	private String getProjectLocator(String aName) {
		return "name:" + aName;
	}

	private String getAuthType() {
		return isHasCredentials() ? "httpAuth/" : "";
    }

	private boolean isHasCredentials() {
		return hasCredentials;
    }

	String getHost() {
	    return host;
    }

	void setHost(String host) {
	    this.host = host;
    }

	int getPort() {
	    return port;
    }

	void setPort(int port) {
	    this.port = port;
    }

}
