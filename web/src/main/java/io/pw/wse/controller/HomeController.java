package io.pw.wse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pwykowski
 */
@Controller
public class HomeController {
	@RequestMapping(path = "/")
	@ResponseBody
	public String index() {
		return "{}";
	}
}