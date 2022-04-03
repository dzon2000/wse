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
		cars.add(new Car(Brand.FIAT, "500", 1.4f, 5, Color.RED, 150f));
		cars.add(new Car(Brand.AUDI, "S8", 4.0f, 5, Color.RED, 950f));
		cars.add(new Car(Brand.OPEL, "Astra", 1.2f, 5, Color.GREEN, 120f));
		return SUCCESS;
	}

	public List<Car> getCars() {
		return cars;
	}
}
