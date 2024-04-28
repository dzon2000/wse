<%@ page language="java" contentType="text/html; charset=UTF-8"
      pageEncoding="UTF-8"%>
   <%@ page import="java.time.LocalDateTime" %>
   <%@ page import="java.time.ZoneId" %>
   <%@ page import="java.time.ZonedDateTime" %>
   <%@ page import="java.time.format.DateTimeFormatter" %>
   <%@ page import="java.time.temporal.Temporal" %>
   <%@ page import="java.util.TimeZone" %>

  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>MVC - Jakarta</title>
  </head>
  <body>
    <form action="/jakarta/time" method="post">
    	<select onchange="this.form.submit()" id="tz" name="tz">""");
        <%
            final String tz = request.getParameter("tz");
            Temporal now;
            if (tz == null) {
                now = LocalDateTime.now();
            } else {
                now = ZonedDateTime.now(ZoneId.of(tz));
            }
            final String[] availableIDs = TimeZone.getAvailableIDs();
            for (String availableID : availableIDs) {
        %>
                <option value="<%=availableID%>"><%=availableID%></option>
        <%
            }
        %>
        </select>
    </form>
    <h1>
        <%=DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm z").format(now)%>
    </h1>
  </body>
  </html>

