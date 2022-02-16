<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="placeholder" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1><c:out value="${ad.title}" /></h1>
    <p>Posted by: <c:out value="${user.username}" /></p>
    <p><c:out value="${ad.description}" /></p>
    <ul class="list-group list-group-horizontal-sm">
        <c:forEach var="category" items="${categories}">
            <li class="list-group-item">
                <c:out value="${category.name}" />
            </li>
        </c:forEach>
    </ul>
</div>
<div class="container">
    <button type="button" class="btn btn-primary">Update listing</button>
    <button type="button" class="btn btn-danger">Delete listing</button>
</div>
<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
