<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="form-group">
        <label for="title">Title</label>
        <small id="title-help" class="form-text text-muted">Title must be between 2 and 255 characters.</small>
        <input id="title" name="title" class="form-control" type="text" value="${title}">
        <p class="text-danger"><c:out value="${titleError}" /></p>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <small id="description-help" class="form-text text-muted">Description cannot be empty.</small>
        <textarea id="description" name="description" class="form-control" type="text"><c:out value="${description}" /></textarea>
        <p class="text-danger"><c:out value="${descriptionError}" /></p>

    </div>
    <div class="form-group">
        <label for="category">Category</label>
        <small id="category-help" class="form-text text-muted">Category/categories must be defined.</small>
        <input id="category" name="category" class="form-control" type="text" value="${category}">
        <p class="text-danger"><c:out value="${categoryError}" /></p>
        <small class="form-text text-muted">Multiple categories can be added by separating them with a comma (eg. "pets, clothing, used")</small>
    </div>
    <input id="submit-btn" type="submit" class="btn btn-block btn-primary">