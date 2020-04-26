package com.company.test.jackson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.company.test.java.model.SecurityData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonTest {

	private ObjectMapper mapper = new ObjectMapper();

	private String FILE_NM = "test-data/data.json";

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
	public void parseJsonString() throws IOException {
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
}
