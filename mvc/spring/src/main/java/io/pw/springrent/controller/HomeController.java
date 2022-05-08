package io.pw.springrent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import io.pw.springrent.model.Brand;
import io.pw.springrent.model.Car;
import io.pw.springrent.model.Color;

@Controller
public class HomeController {
    
    public List<Car> cars = new ArrayList<>(List.of(
        new Car(Brand.BMW, "M3", 3.2f, 2, Color.DARK_GRAY, 650f, "bmw.jpg"),
        new Car(Brand.ALFA_ROMEO, "C4", 3.0f, 2, Color.RED, 500f, "alfa.jpg"),
        new Car(Brand.AUDI, "R8", 4.8f, 4, Color.DARK_GRAY, 888f, "audi.jpg")
    ));


    @GetMapping(path = "/")
    public String hello(Model model) {
        model.addAttribute("cars", cars);
        return "index";
    }

}
