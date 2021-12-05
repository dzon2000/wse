package io.pw.wse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by pwykowski
 */
@RestController
public class RestAppController {

	private static final Map<Integer, String> USERS = Map.of(
			1, "john",
			2, "greg"
	);

	@GetMapping(path = "/api/users")
	public String users() {
		return """
				{
					"users": [
						{
							"login": "john"
						},
						{
							"login": "greg"
						}
					]	
				}		
				
				""";
	}

	@GetMapping(path = "/api/users/{id}")
	public String userByID(@PathVariable Integer id) {
		return String.format("""
				{
					"login": "%s"
				}		
				""", USERS.get(id));
	}

}
