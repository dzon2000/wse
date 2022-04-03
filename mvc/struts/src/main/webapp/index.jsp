<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Car Rental</title>
  </head>
  <body>
    <h2>Hello, this is Car Rental</h2>

    <s:iterator value="cars">
          <s:property value="brand" />, <s:property value="model" />
        </s:iterator>
  </body>
</html>