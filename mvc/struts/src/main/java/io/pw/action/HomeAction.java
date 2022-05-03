package io.pw.action;

import com.opensymphony.xwork2.ActionSupport;
import io.pw.model.Brand;
import io.pw.model.Car;
import io.pw.model.Color;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pwykowski
 */
@Result(location = "/index.jsp")
@Action("")
public class HomeAction extends ActionSupport {

	public List<Car> cars = new ArrayList<>();
	public String execute() {
		cars.add(new Car(Brand.BMW, "M3", 3.2f, 2, Color.DARK_GRAY, 650f, "bmw.jpg"));
		cars.add(new Car(Brand.ALFA_ROMEO, "C4", 3.0f, 2, Color.RED, 500f, "alfa.jpg"));
		cars.add(new Car(Brand.AUDI, "R8", 4.8f, 4, Color.DARK_GRAY, 888f, "audi.jpg"));
		return SUCCESS;
	}

	public List<Car> getCars() {
		return cars;
	}
}
