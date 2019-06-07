<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${currentVideo != null}">
    <div class="aquapanel">
        <h3>Notes</h3>
        <i>${currentVideo.title}</i>
        <table id="note_table" class="table table-compact table-striped">
            <tr>
                <th>Label</th>
                <th>Text</th>
                <th>Start</th>
                <th>Created</th>
                <th>Controls</th>
            </tr>
            <c:forEach items="${currentVideo.notes}" var="note">
                <tr class="">
                    <td>${note.label}</td>
                    <td>${note.text}</td>
                    <td>${note.start}</td>
                    <td>${note.createDatetime}</td>
                    <td><a href="edit-note?noteId=${note.id}">Edit</a></td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(currentVideo.notes) == 0}">
                <p>There are no notes for this video.</p>
            </c:if>
        </table>
    </div>
</c:if>