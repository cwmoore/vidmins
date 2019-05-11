<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${notes != null}">
    <div class="aquapanel">
        <h3>Notes</h3>
        <i>${notes[0].video.title}</i>
        <table id="note_table" class="table table-compact table-striped">
            <tr>
                <th>Label</th>
                <th>Text</th>
                <th>Start</th>
                <th>Created</th>
                <th>Controls</th>
            </tr>
            <c:forEach items="${notes}" var="note">
                <tr class="">
                    <td>${note.label}</td>
                    <td>${note.text}</td>
                    <td>${note.start}</td>
                    <td>${note.createDatetime}</td>
                    <td><a href="edit-note?noteId=${note.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>