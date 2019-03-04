<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user == null}">
    <div id="access_form_container" class="aquapanel">
        <form method="post" action="" class="signin">
            <label>email</label> <input type="email" name="email"/><br />
            <label>password</label> <input type="password" name="password"/><br />
            <input type="submit" value="Sign in"/>
        </form>

        <form method="post" action="" class="signin">
            <label>email</label> <input type="email" name="new_email1"/><br />
            <label>password</label> <input type="password" name="new_password1"/><br />
            <input type="submit" value="Sign up"/>
        </form>
    </div>
</c:if>