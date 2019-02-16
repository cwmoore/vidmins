<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>{
  user: {
  <c:if test="${user != null}">
    id: ${user.id}
    ,firstName: "${user.firstName}"
    , lastName: "${user.lastName}"
    , username: "${user.userName}"
    , email: "${user.email}"
    , joinDate: "${user.joinDate}"
    , dateOfBirth: "${user.dateOfBirth}"
    , organization: "${user.organization}"
    , introduction: "${user.introduction}"
    , status: "${user.status}"
  </c:if>
  }
  , videos: {
  <c:if test="${videos != null}">
    <c:forEach items="${videos}" var="video">
    {
      id: "${video.id}"
      , addDate: "${video.addDate}"
      , youTubeId: "${video.youTubeId}"
      , title: "${video.title}"
      , duration: "${video.duration}"
    },
    </c:forEach>
  </c:if>
  }
  , notes: {
  <c:if test="${notes != null}">
    <c:forEach items="${notes}" var="note">
    {
      id: "${note.id}"
      , label: "${note.label}"
      , test: "${note.text}"
      , start: "${note.start}"
      , end: "${note.end}"
      , createDatetime: "${note.createDatetime}"
      , userId: "${note.userId}"
      , videoId: "${note.videoId}"
    },
    </c:forEach>
  </c:if>
  }<%--
, tags: {
  <c:if test="${tags != null}">
    <c:forEach items="${tags}" var="tag">
    {
      id: "${tag.id}"
      , tag: "${tag.tag}"
      , objectId: "${tag.objectId}"
      , objectType: "${tag.objectType}"
    },
    </c:forEach>
  </c:if>
  }--%>
}