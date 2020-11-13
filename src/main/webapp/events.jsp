<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.11.2020
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/Events">
    Event ID:
    <input type="text" name="eventId" id="eventId" placeholder="Event ID...">
    <br>
    Event title:
    <input type="text" name="eventName" id="eventName" placeholder="Event name...">
    <br>
    Description
    <input type="text" name="eventDescription" id="eventDescription" placeholder="Description...">
    <br>
    <input type="submit" value="Add">
</form>
<a href="<%=request.getContextPath()%>/ShowEvents">Show all events</a></center>
</body>
</html>
