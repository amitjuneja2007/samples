package com.company.test.java.model;

import java.util.HashMap;
import java.util.Map;

public class SecurityData {

	private Map<String, String> requestHeader = new HashMap<String, String>();
	private Map<String, String> securityInfo = new HashMap<String, String>();
	
	// Getters and Setters

	public Map<String, String> getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(Map<String, String> requestHeader) {
		this.requestHeader = requestHeader;
	}

	public Map<String, String> getSecurityInfo() {
		return securityInfo;
	}

	public void setSecurityInfo(Map<String, String> securityInfo) {
		this.securityInfo = securityInfo;
	}

}
