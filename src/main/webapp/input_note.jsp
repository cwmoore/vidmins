<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="note_input" class="aquapanel">

    <form name="note_input_form" accept-charset="utf-8" action="new-note" method="post">
        <%-- TODO provide feedback and instructions --%>
        <%-- TODO properly prepopulate fields sessionScope.editNote is insufficent --%>

        <c:if test="${sessionScope.editNote != null}">
            <button type="button" class="btn btn-info" id="start_new_note_btn">Start New</button><br />
        </c:if>

        <label>Label:</label><br />
        <input type="text" name="label"<c:if test="${sessionScope.editNote.label != null}"> value="${sessionScope.editNote.label}"</c:if>/><br />

        <label>Text:</label><br />
        <textarea name="note_text"><c:if test="${sessionScope.editNote.text != null}"><c:out value="${sessionScope.editNote.text}"/></c:if></textarea><br />

        <%--label>Tags:</label><br />
        <input type="text" name="tag" />

        <button name="add-tag" onclick="addTag()">Add Tag</button><br />
        <span id="tags"></span--%>

            <%-- TODO use player.getCurrentTime if no value is available in editNote --%>
        <input type="hidden" name="timeStampStart" value="<c:if test="${sessionScope.editNote.start != null}"><c:out value="${sessionScope.editNote.start}"/></c:if>" />
        <label>Start:</label> <span id="time_stamp_start"><c:if test="${sessionScope.editNote.start != null}"><c:out value="${sessionScope.editNote.start}"/></c:if></span><br />

        <button id="reset_note_time" type="button" onclick="resetTime();">Set Time</button>

        <%--input type="hidden" name="timeStampEnd" value="0" />
        <label>End:</label> <span id="time_stamp_end">0</span><br /--%>

        <c:if test="${sessionScope.editNote != null}">
            <input type="hidden" id="hidden_note_id" name="noteId" value="${sessionScope.editNote.id}" /><%-- TODO FIXME it is unsafe to expose internal ids to manipulation --%>
        </c:if>

        <input type="hidden" name="videoId"
               value="<c:choose><c:when test="${sessionScope.editNote.video.id != null}">${sessionScope.editNote.video.id}</c:when><c:otherwise>${sessionScope.currentVideo.id}</c:otherwise></c:choose>" />

        <br />

        <button id="store_note_btn" type="submit" class="btn btn-primary"><c:choose>
            <c:when test="${sessionScope.editNote != null}">Save</c:when>
            <c:otherwise>Create</c:otherwise>
        </c:choose> Note</button>

        <%-- TODO show 'delete' if existing note, 'discard' if new --%>
        <button id="delete_note_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-note?noteId=${sessionScope.editNote.id}';">Delete</button>
    </form>

</div>
