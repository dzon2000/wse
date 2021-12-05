package io.pw.wse.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping(path = "/")
	@ResponseBody
	public String index() {
		return """
				
				<h1>Hello</h1>
				
				<form action="/greet" method="POST">
					<input type="text" name="login" />
					<input type="password" name="pass" />
					<input type="submit"/>
				</form>
				
				""";
	}

	@RequestMapping(
			path = "/greet",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String greet(@RequestBody MultiValueMap<String, String> data) {
		return String.format("""
				{
					"login": "%s",
					"password": "%s"
				}
				""", data.get("login"), data.get("pass"));
	}

}