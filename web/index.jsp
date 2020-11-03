<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <title>主页</title>
  </head>
  <body>
  <a href="springmvc/testRequestMapping?username=123">Test ParamsAndHeaders1 username</a><br>
  <a href="springmvc/testRequestMapping">Test ParamsAndHeaders2</a><br>
  <a href="springmvc/testRequestMapping?username=123&age=10">Test ParamsAndHeaders3 username</a><br>
  <a href="springmvc/testRequestMapping?username=123&age=11">Test ParamsAndHeaders4 username</a><br>
  <a href="springmvc/testPathVariable/10/d">Test PathVariable</a><br>
  <form action="springmvc/testRest/1" method="post">
    <%--设置隐藏域--%>
    <input type="hidden" name="_method" value="PUT"/>
        <input type="text" name="username"/>
    <input type="submit" value="submit PUT"/>
  </form>
  <form action="springmvc/testRest/1" method="post">
    <%--设置隐藏域--%>
    <input type="hidden" name="_method" value="DELETE"/>
    <%--    <input type="text" name="username"/>--%>
    <input type="submit" value="submit DELETE"/>
  </form>
  <form action="springmvc/testModelAttribute" method="post">
    id:<input type="text" name="id1" value="2"><br>
    username:<input type="text" name="userName" value="lisi"><br>
    password:<input type="text" name="passWord" value="456"><br>
    age:<input type="text" name="age" value="2"><br>
    Address.city:<input type="text" name="address.city"><br>
    Address.province:<input type="text" name="address.province"><br>
    <input type="submit"/>
  </form>

  username:
  <input type="text" name="username1">
  <br>
  password:
  <input type="password" name="password1">
  <br>
  <input type="submit">
  </body>
</html>
