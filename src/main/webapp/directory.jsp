<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${directories != null}">
        <table>
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
    </c:when>
    <c:otherwise>Directories will be shown here.</c:otherwise>
</c:choose>