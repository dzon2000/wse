package io.pw.http;

import com.sun.net.httpserver.HttpExchange;
import io.pw.uri.Param;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pwykowski
 */
public class QueryParamParser {
	public static Map<String, Param> getParamsMap(String query) {
		Map<String, Param> paramsMap = new HashMap<>();
		final String[] params = query.split("&");
		for (String param : params) {
			final String[] split = param.split("=");
			paramsMap.put(split[0], new Param(split[0], split[1]));
		}
		return paramsMap;
	}

}
