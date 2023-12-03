package io.pw.db.entity;

import io.pw.data.Transformer;
import org.json.JSONObject;

/**
 * Created by pwykowski
 */
public record Product(long id, String name, String desc, String serial, int qty) implements Transformer<JSONObject> {
	@Override
	public JSONObject transform() {
		JSONObject json = new JSONObject();
		json.put("name", this.name);
		json.put("desc", this.desc);
		json.put("serial", this.serial);
		json.put("qty", this.qty);
		return json;
	}
}
