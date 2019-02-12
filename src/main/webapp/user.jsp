<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${user != null}">
    <div class="row">
        <h2>User Info: </h2>
        <table class="table table-striped">
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>User Name</th>
                <th>Age</th>
                <th>Password</th>
            </tr>
            <tr class="">
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userName}</td>
                <td>${user.age}</td>
                <td>${user.password}</td>
            </tr>
        </table>
    </div>
</c:if>