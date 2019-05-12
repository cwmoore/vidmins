<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${directories != null}">
    <div class="aquapanel">
        <h3>Directories</h3>
        <table id="directory_table" class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Controls</th>
            </tr>
        <c:forEach items="${directories}" var="directory">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${directory.id == currentDirectory.id}">
                            <c:out value="${directory.name}"/>
                        </c:when>
                        <c:otherwise>
                            <a href="loadClient?directoryId=<c:out value="${directory.id}"/>">
                                <c:out value="${directory.name}"/>
                            </a>
                        </c:otherwise>
                    </c:choose>

                </td>
                <td><c:out value="${directory.description}"/></td>
                <td>
                    <a href="edit-directory?directoryId=${directory.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>
</c:if>