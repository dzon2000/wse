package io.pw;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pwykowski
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	private static Map<String, String> USERS = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String name = req.getParameter("name");
		final String brand = USERS.getOrDefault(name, "None");
		resp.getOutputStream().print(String.format("""
				<h1>Hello %s! Your favourite car brand is %s</h1>
				""", name, brand));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String name = req.getParameter("name");
		final String brand = req.getParameter("brand");
		System.out.printf("Name %s, brand %s", name, brand);
		USERS.put(name, brand);
		resp.getOutputStream().print("OK");
	}
}
