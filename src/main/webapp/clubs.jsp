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
    <title>Clubs</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/Clubs">
    Club ID:
    <input type="text" name="clubId" id="clubId" placeholder="Club ID...">
    <br>
    Club name:
    <input type="text" name="clubName" id="clubName" placeholder="Club name...">
    <br>
    Description
    <input type="text" name="clubDescription" id="clubDescription" placeholder="Description...">
    <br>
    <input type="submit" value="Add">
</form>
<a href="<%=request.getContextPath()%>/ShowClubs">Show all clubs</a></center>
</body>
</html>
