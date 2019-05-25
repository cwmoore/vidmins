<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="video_input" class="aquapanel">

    <form name="video_form" action="<c:choose><c:when test="${sessionScope.editVideo != null}">edit</c:when><c:otherwise>new</c:otherwise></c:choose>-video" method="post">

        <label for="new_video_title">Your title (optional)</label>
        <input id="new_video_title" name="title"<c:if test="${sessionScope.editVideo.title != null}"><c:out value="${sessionScope.editVideo.title}"/></c:if> placeholder="Your title for this video"/><br />

        <label for="youtube_url">YouTube Video URL</label><br />
        <input type="text" id="youtube_url" name="youtube_url" placeholder="https://youtu.be/R4nd0mCh4r5"<c:if test="${sessionScope.editVideo.youTubeVideo.youTubeId != null}"> value="<c:out value="${sessionScope.editVideo.youTubeVideo.youTubeId}"/>"</c:if>/><br />


        <c:if test="${sessionScope.editVideo != null}">
            <input type="hidden" id="hidden_video_id" name="videoId" value="${sessionScope.editVideo.id}" /><%-- TODO FIXME it is unsafe to expose internal ids to manipulation --%>
        </c:if>

        <button type="submit" class="btn btn-primary" id="add_video_button"><c:choose>
            <c:when test="${sessionScope.editVideo != null}">Create</c:when>
            <c:otherwise>Save</c:otherwise>
        </c:choose> Video
        </button>
        <%-- TODO show 'delete' if existing video, 'discard' if new --%>
        <button id="delete_video_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-video?videoId=${sessionScope.editVideo.id}';">Delete</button>
    </form>
</div>