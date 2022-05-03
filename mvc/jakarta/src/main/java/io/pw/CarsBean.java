package io.pw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.pw.model.Brand;
import io.pw.model.Car;
import io.pw.model.Color;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SessionScoped
@Named
public class CarsBean implements Serializable {
    
    private List<Car> cars = new ArrayList<>(List.of(
        new Car(Brand.BMW, "M3", 3.2f, 2, Color.DARK_GRAY, 650f, "img/bmw.jpg"),
		new Car(Brand.ALFA_ROMEO, "C4", 3.0f, 2, Color.RED, 500f, "img/alfa.jpg"),
		new Car(Brand.AUDI, "R8", 4.8f, 4, Color.DARK_GRAY, 888f, "img/audi.jpg")
    ));

    public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
