package com.n0dg.tc.rest;

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
