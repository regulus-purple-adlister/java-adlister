<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${ad.title}" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1><c:out value="${ad.title}" /></h1>
    <p>Posted by: <c:out value="${user.username}" /></p>
    <p><c:out value="${ad.description}" /></p>
    <ul class="list-group list-group-horizontal-sm my-2 mx-0">
        <li class="list-group-item list-group-item-light p-2">Tags:</li>
        <c:forEach var="category" items="${categories}">
            <li class="list-group-item p-2">
                <c:out value="${category.name}" />
            </li>
        </c:forEach>
    </ul>
</div>
<%-- this c:if test is commented out for sake of convenience of debugging the ad update/delete --%>
<%-- when features are finished it should be reenabled so only the poster can change their ad --%>
<%--<c:if test="${sessionScope.user.id == ad.userId}">--%>
    <div class="container">
        <form action="update_ad" method="post">
            <button type="button" class="btn btn-primary" value="<c:out value="${ad.id}" />" name="update">Update listing</button>
        </form>
        <form action="delete_ad" method="post">
            <button type="submit" class="btn btn-danger" value="<c:out value="${ad.id}" />" name="delete">Delete listing</button>
        </form>
    </div>
<%--</c:if>--%>
<jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
</body>
</html>
