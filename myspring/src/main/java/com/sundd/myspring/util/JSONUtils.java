package com.sundd.myspring.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object obj) {
		String str = null;
		try {
			str = mapper.writeValueAsString(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static <T> T toObject(String jsonString, Class<T> type) {
		T t = null;
		try {
			t =mapper.readValue(jsonString, type);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
}
