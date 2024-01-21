package io.pw.db;

import org.json.JSONObject;

/**
 * Created by pwykowski
 */
public record Product(long id, String name, String desc, String serialNumber, int quantity) {

	public JSONObject toJSON() {
		JSONObject productJSON = new JSONObject();
		productJSON.put("id", this.id());
		productJSON.put("name", this.name());
		productJSON.put("desc", this.desc());
		productJSON.put("serial", this.serialNumber());
		productJSON.put("quantity", this.quantity());
		return productJSON;
	}

}
