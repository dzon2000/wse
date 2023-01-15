package io.pw.db.entity;

import io.pw.data.Transformer;
import org.json.JSONObject;

/**
 * Created by pwykowski
 */
public class Product implements Transformer<JSONObject> {

	private final long id;
	private final String name;
	private final String desc;
	private final String serial;
	private final int qty;

	public Product(long id, String name, String desc, String serial, int qty) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.serial = serial;
		this.qty = qty;
	}

	public Product(String name, String desc, String serial, int qty) {
		this.id = -1;
		this.name = name;
		this.desc = desc;
		this.serial = serial;
		this.qty = qty;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}

	public String getSerial() {
		return serial;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		return "Product{" +
			   "id=" + id +
			   ", name='" + name + '\'' +
			   ", desc='" + desc + '\'' +
			   ", serial='" + serial + '\'' +
			   ", qty=" + qty +
			   '}';
	}

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
