package io.pw.action;

import com.opensymphony.xwork2.ActionSupport;
import io.pw.model.Greet;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 * Created by pwykowski
 */

@Action(value="greet", results = {
		@Result(name = "success", location = "/greet.jsp"),
		@Result(name = "error", location = "/error.jsp")
})
public class GreetAction extends ActionSupport {

	private Greet greet;

	public String execute() {
		greet = new Greet("Hello World!");

		return SUCCESS;
	}

	public Greet getGreet() {
		return greet;
	}
}
