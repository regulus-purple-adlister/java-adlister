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

<div class="container mt-4 mb-4">
    <h1>Edit</h1>
</div>
<form action="/updateprofile" method="post">
    <div class="form-group">
        <h5><label for="firstname">First Name</label></h5>
        <input type="text" id="firstname" class="form-control" name="firstname">
    </div>
    <div class="form-group">
        <h5><label for="lastname">Last Name</label></h5>
        <input type="text" id="lastname" class="form-control" name="lastname">
    </div>
    <div class="form-group">
        <h5><label for="city">City</label></h5>
        <input type="text" id="city" class="form-control" name="city">
    </div>
    <input id="submit-btn" type="submit" class="btn btn-primary btn-block">
</form>
</body>
</html>
