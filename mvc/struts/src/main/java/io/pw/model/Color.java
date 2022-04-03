package io.pw.model;

/**
 * Created by pwykowski
 */
public enum Color {
	RED("#FF0000"), GREEN("#00FF00"),
	BLUE("#0000FF");

	private String color;

	Color(String color) {
		this.color = color;
	}

	public String color() {
		return this.color;
	}

}
