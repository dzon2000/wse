<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <h1>JSP</h1>
            <%
                String prefix = "Hello";
                for (int i = 0; i < 10; i++) { %>
                    <h3><%=prefix + i%></h3>
            <% } %>

    </body>
</html>