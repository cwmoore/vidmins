<%@include file="head.jsp"%>
<body>
<div class="container-fluid">
    <h2>Search Results: </h2>
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

</body>
</html>
