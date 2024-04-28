package io.pw;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.TimeZone;

/**
 * Created by pwykowski
 */
@WebServlet("/time")
public class TimeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final String tz = req.getParameter("tz");
		Temporal now;
		if (tz == null) {
			now = LocalDateTime.now();
		} else {
			now = ZonedDateTime.now(ZoneId.of(tz));
		}
		final ServletOutputStream outputStream = resp.getOutputStream();
		resp.setHeader("Content-Type", "text/html");
		outputStream.print("""
      		<form action="/jakarta/time" method="post">
  				<select onchange="this.form.submit()" id="tz" name="tz">""");
		final String[] availableIDs = TimeZone.getAvailableIDs();
		for (String availableID : availableIDs) {
			outputStream.print(String.format("<option value=\"%s\">%s</option>", availableID, availableID));
		}
		outputStream.print("""
  				</select>
  			</form>""");
		outputStream.print("<h1>" + DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm z").format(now) + "</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
