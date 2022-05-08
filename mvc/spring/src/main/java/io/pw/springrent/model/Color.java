package io.pw.springrent.model;

public enum Color {
	RED("#FF0000"), DARK_GRAY("#404040"),
	WHITE("#000000");

	private String color;

	Color(String color) {
		this.color = color;
	}

	public String color() {
		return this.color;
	}

}
