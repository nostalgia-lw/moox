<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Hello World!</h2>
<form action="${pageContext.servletContext.contextPath}/login.shtml" method="post">
    username ：<input type="text" name="loginname" value="test01" />  password: <input type="password" name="password" value="123456"><input type="submit" />
</form>
<h5 style="color: #ff6417;text-align: center;">
    <c:if test="${not empty param.kickout}">其他地方登录了此账号，请重新登录！</c:if>${error==null?'请输入帐号和密码!':error}
</h5>
</body>
</html>
