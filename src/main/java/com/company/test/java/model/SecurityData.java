package com.company.test.java.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class SecurityData {

	private Map<String, String> requestHeader = new HashMap<String, String>();
	private Map<String, String> securityInfo = new HashMap<String, String>();

}
