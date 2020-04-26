package com.company.test.jackson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.company.test.java.model.SecurityData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Data;

public class JacksonTest {

	private static ObjectMapper mapper = new ObjectMapper();

	private String FILE_NM = "test-data/data.json";

	/**
	 * Setup beans here
	 */
	@BeforeClass
	public static void setup() {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

	}

	private String readJsonFromFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(FILE_NM));
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		return sb.toString();

	}

	@Test
	public void parseJsonToJavaObject() throws IOException {
		/**
		 * Enable pretty print
		 */
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = readJsonFromFile();
		SecurityData data = mapper.readValue(jsonString, SecurityData.class);
		/**
		 * After parsing
		 */
		System.out.println(mapper.writeValueAsString(data));
		data.getRequestHeader().put("securityId", "abc");
		/**
		 * Edit the Json
		 */
		System.out.println(mapper.writeValueAsString(data));

	}

	@Test
	public void parseJsonToJavaObject2() throws IOException {
		/**
		 * Enable pretty print
		 */
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		String jsonString = readJsonFromFile();
		SecurityData2 data = mapper.readValue(jsonString, SecurityData2.class);
		/**
		 * After parsing
		 */
		System.out.println(mapper.writeValueAsString(data));
		data.getRequestHeader().setPropertyKey1("changedValue");
		/**
		 * Edit the Json
		 */
		System.out.println(mapper.writeValueAsString(data));

	}

	@Test
	@SuppressWarnings("unchecked")
	public void parseJsonToMap() throws IOException {

		String jsonString = readJsonFromFile();

		Map<String, Map<String, String>> jsonAsMap = (Map<String, Map<String, String>>) mapper.readValue(jsonString,
				Map.class);
		/**
		 * After parsing
		 */
		System.out.println(mapper.writeValueAsString(jsonAsMap));
		jsonAsMap.get("requestHeader").put("securityId", "abc");
		/**
		 * Edit the Json
		 */
		System.out.println(mapper.writeValueAsString(jsonAsMap));

	}

	@Data
	private static class SecurityData2 {
		/**
		 *  We are defining the nested elements as JSON object.
		 *  This gives more flexibility, see line# 77
		 * 
		 */
		private RequestHeader requestHeader;
		private Map<String, String> securityInfo = new HashMap<String, String>();
	}

	@Data
	private static class RequestHeader {
		private String propertyKey1;
		private String propertyKey2;
		private String propertyKey3;
	}
}
