package io.pw.model;

/**
 * Created by pwykowski
 */
public class Car {

	private Brand brand;
	private String model;
	private float engine;
	private int numOfSeats;
	private Color color;
	private float price;
	private String imgPath;

	public Car(Brand brand, String model, float engine, int numOfSeats, Color color, float price, String imgPath) {
		this.brand = brand;
		this.model = model;
		this.engine = engine;
		this.numOfSeats = numOfSeats;
		this.color = color;
		this.price = price;
		this.imgPath = imgPath;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getEngine() {
		return engine;
	}

	public void setEngine(float engine) {
		this.engine = engine;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
