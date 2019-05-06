<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${directories != null}">
    <div class="aquapanel">
        <table id="directory_table" class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Description</th>
            </tr>
        <c:forEach items="${directories}" var="directory">
            <tr>
                <td><c:out value="${directory.name}"/></td>
                <td><c:out value="${directory.description}"/></td>
            </tr>
        </c:forEach>
        </table>
    </div>
</c:if>