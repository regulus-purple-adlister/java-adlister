<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ads">
    <c:forEach var="ad" items="${ads}">
        <div>
            <h2>
                <a href="<c:url value="/ads/${ad.id}" />">
                <c:out value="${ad.title}" />
                </a>
            </h2>
            <p><c:out value="${ad.description}" /></p>
        </div>
    </c:forEach>
</div>