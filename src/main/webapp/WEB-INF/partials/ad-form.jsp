<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="${param.action}" method="post">
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
</form>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script>
    $(function () {
        const validateTitle = {
            el: $('#title-help'),
            tgt: $('#title'),
            ok: function () {
                return this.tgt.val().length > 2 && this.tgt.val().length < 255;
            }
        }
        const validateDescription = {
            el: $('#description-help'),
            tgt: $('#description'),
            ok: function () {
                return this.tgt.val().length >= 1;
            }
        }
        const validateCategory = {
            el: $('#category-help'),
            tgt: $('#category'),
            ok: function () {
                return this.tgt.val().length >= 1;
            }
        }
        const submit = {
            el: $('#submit-btn').prop('disabled', true),
            ok: function () {
                if (validateTitle.ok() &&
                    validateDescription.ok() &&
                    validateCategory.ok()) {
                    this.el.prop('disabled', false);
                } else {
                    this.el.prop('disabled', true);
                }
            }
        }
        const inputs = [ validateTitle, validateDescription, validateCategory ]
        inputs.forEach(input => {
            input.el.hide();
            input.tgt.on('input', function () {
                submit.ok();
                if (input.ok()) {
                    input.el.hide();
                    input.tgt.css('border-color', '#ced4da');
                } else {
                    input.el.show();
                    input.tgt.css('border-color', '#ff0000');
                }
            });
        });
    });
</script>
