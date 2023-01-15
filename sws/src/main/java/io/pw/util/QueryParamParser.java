package io.pw.util;

import org.json.JSONObject;

/**
 * Created by pwykowski
 */
public class QueryParamParser {


	public static JSONObject parseQueryString(String body) {
		String[] params = body.split("&");
		JSONObject newProduct = new JSONObject();
		for (String param : params) {
			String[] paramAndValue = param.split("=");
			newProduct.put(paramAndValue[0], paramAndValue[1]);
		}
		return newProduct;
	}
}
