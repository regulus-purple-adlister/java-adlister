<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1><c:out value="${sessionScope.user.username}" />!</h1>
</div>
<form action="/updateprofile" method="post">
    <div>
        <label for="firstname">First Name</label>
        <input type="text" id="firstname" name="firstname">
    </div>
    <div>
        <label for="lastname">Last Name</label>
        <input type="text" id="lastname" name="lastname">
    </div>
    <div>
        <label for="city">City</label>
        <input type="text" id="city" name="city">
    </div>
    <input type="submit" value="submit">
</form>
</body>
</html>
