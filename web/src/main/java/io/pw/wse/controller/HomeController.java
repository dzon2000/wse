package io.pw.wse.controller;

import io.pw.wse.model.Book;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

	private static final List<Book> BOOKS = new ArrayList(List.of(
			new Book("123", "W pustyni i w puszczy", "H. Sienkiewicz"),
			new Book("456", "Lalka", "B. Prus"),
			new Book("678", "Przedwośnie", "S. Żeromski")
	));

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

	@RequestMapping(path = "/books")
	public String indexJSP(Model model) {
		model.addAttribute("books", BOOKS);
		model.addAttribute("book", new Book());
		return "index";
	}

	@RequestMapping(path = "/addBook", method = RequestMethod.POST)
	public String addBook(Book book) {
		BOOKS.add(book);
		return "newBook";
	}

	@RequestMapping(path = "/helloJSP")
	public String helloJSP() {
		return "home";
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