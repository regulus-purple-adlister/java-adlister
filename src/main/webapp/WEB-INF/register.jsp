<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <small id="username-help" class="form-text text-muted">Username must be at least 4 characters long.</small>
                <input id="username" name="username" class="form-control" type="text" value="${username}">
                <p class="text-danger"><c:out value="${userError}" /></p>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <small id="email-help" class="form-text text-muted">Email must be a valid email address.</small>
                <input id="email" name="email" class="form-control" type="text" value="${email}">
                <p class="text-danger"><c:out value="${emailError}" /></p>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <small id="password-help" class="form-text text-muted">Password must be at least 6 characters long.</small>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <small id="confirm-help" class="form-text text-muted">Passwords must match.</small>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
                <p class="text-danger"><c:out value="${passError}" /></p>
            </div>
            <input id="submit-btn" type="submit" class="btn btn-primary btn-block">
        </form>
    </div>
    <jsp:include page="/WEB-INF/partials/bootstrap-scripts.jsp" />
<script>
    $(function () {
        // regex match for strings that is only true when something goes like {stuff}@{stuff}.{stuff} ie email@email.com
        const checkEmail = (email) => {
            return email.match(
                /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            );
        };
        const validateUsername = {
            el: $('#username-help').hide(),
            tgt: $('#username'),
            ok: function () {
                return this.tgt.val().length >= 4;
            }
        }
        const validateEmail =  {
            el: $('#email-help').hide(),
            tgt: $('#email'),
            ok: function () {
                return checkEmail(this.tgt.val());
            }
        }
        const validatePassword = {
            el: $('#password-help').hide(),
            tgt: $('#password'),
            ok: function () {
                return this.tgt.val().length >= 6;
            }
        }
        const validateConfirmPass = {
            el: $('#confirm-help').hide(),
            tgt: $('#confirm_password'),
            ok: function () {
                return this.tgt.val() === validatePassword.tgt.val();
            }
        }
        const submit = {
            el: $('#submit-btn').prop('disabled', true),
            ok: function () {
                if (validateUsername.ok() &&
                    validateEmail.ok() &&
                    validatePassword.ok() &&
                    validateConfirmPass.ok()) {
                    this.el.prop('disabled', false);
                } else {
                    this.el.prop('disabled', true);
                }
            }
        }
        const inputs = [ validateUsername, validateEmail, validatePassword, validateConfirmPass ];
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
</body>
</html>
