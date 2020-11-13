<%--
  Created by IntelliJ IDEA.
  User: Tima
  Date: 13.11.2020
  Time: 3:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>News</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/News">
    News ID:
    <input type="text" name="newsId" id="newsId" placeholder="News ID...">
    <br>
News title:
    <input type="text" name="newsTitle" id="newsTitle" placeholder="News title...">
    <br>
    Description
    <input type="text" name="newsDescription" id="newsDescription" placeholder="Description...">
    <br>
    Publisher
    <input type="text" name="newsPublisher" id="newsPublisher" placeholder="Publisher...">
    <br>
    <input type="submit" value="Add">
</form>
<a href="<%=request.getContextPath()%>/ShowNews">Show all news</a></center>
</body>
</html>
