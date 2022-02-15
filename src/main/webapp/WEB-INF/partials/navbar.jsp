<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/ads">Adlister</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navToggle" aria-controls="navToggle" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navToggle">
        <form class="form-inline my-2 my-lg-auto ml-lg-auto mr-2" method="get" action="/search">
            <input class="form-control mr-sm-2" type="search" placeholder="Search ads" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <ul class="navbar-nav ml-2 mr-2 mt-2 mt-lg-0">
            <c:if test="${user == null}">
                <li class="mr-2 mb-1"><a href="/login">Login</a></li>
                <li class="mr-2 mb-1"><a href="/register">Register</a></li>
            </c:if>
            <c:if test="${user != null}">
                <li class="mr-2 mb-1"><a href="/profile">Profile</a></li>
                <li class="mr-2 mb-1"><a href="/logout">Logout</a></li>
            </c:if>
        </ul>
    </div>
</nav>