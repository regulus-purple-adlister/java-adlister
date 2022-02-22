<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Please Log In</h1>
        <form action="/login" method="POST">
            <input name="redirect" type="hidden" value="${caller}">
            <div class="form-group">
                <label for="username">Username or Email</label>
                <input id="username" name="username" class="form-control" type="text" value="${username}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <p class="text-danger"><c:out value="${loginError}" /></p>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
        <small>Don't have an account? <a href="/register">Register here!</a></small>
    </div>
    <jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
