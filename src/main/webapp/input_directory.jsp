<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="directory_input" class="aquapanel">

    <form name="directory_form" action="<c:choose><c:when test="${sessionScope.editDirectory != null}">edit</c:when><c:otherwise>new</c:otherwise></c:choose>-directory" method="post">

        <label for="directoryNameField">Name</label><br />
        <input
                name="directoryName"
                id="directoryNameField"
                <c:if test="${sessionScope.editDirectory.name != null}">value="<c:out value="${sessionScope.editDirectory.name}"/>"</c:if>
        />

        <br />

        <label for="directoryDescriptionField">Description</label>
        <textarea name="directoryDescription" id="directoryDescriptionField"><c:if test="${sessionScope.editDirectory.description != null}"><c:out value="${sessionScope.editDirectory.description}"/></c:if></textarea><br />

        <button type="submit" class="btn btn-primary" id="add_directory_button"><c:choose>
            <c:when test="${sessionScope.editDirectory != null}">Save</c:when>
            <c:otherwise>Create</c:otherwise></c:choose> Directory
        </button>

        <%-- TODO show 'delete' if existing directory, 'discard' if new --%>
        <button id="delete_directory_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-directory?directoryId=${sessionScope.editDirectory.id}';">Delete</button>
    </form>
</div>