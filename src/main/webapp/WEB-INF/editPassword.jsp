<%--
  Created by IntelliJ IDEA.
  User: jadeaustin
  Date: 2/16/22
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Your Password" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Edit Your Password <c:out value="${sessionScope.user.username}" />!</h1>
<form action="/editPassword" method="POST">
    <div class="form-group">
        <label for="old_password">Old Password</label>
        <input id="old_password" name="old_password" class="form-control" type="password">
    </div>

    <div class="form-group">
        <label for="new_password">New Password</label>
        <input id="new_password" name="new_password" class="form-control" type="password">
    </div>

    <div class="form-group">
        <label for="confirm_new_password">Confirm Password</label>
        <input id="confirm_new_password" name="confirm_new_password" class="form-control" type="password">
    </div>
    <input type="submit" class="btn btn-primary btn-block">

</form>
</div>


<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
