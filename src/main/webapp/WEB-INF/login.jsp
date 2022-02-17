<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-----------------------------------------------------------------------------------------------%>
<%
    String username = (request.getParameter("username") == null) ? "" : request.getParameter("username");
//    String password = (request.getParameter("password") == null) ? "" : request.getParameter("password");
%>
<%-----------------------------------------------------------------------------------------------%>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Please Log In</h1>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" value="<%=username%>">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <p name="error-message" style="display: block; color: red">Error Message Displayed Here</p>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
    </div>
    <jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
