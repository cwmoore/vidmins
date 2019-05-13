<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 id="brand-header"><span>Vid</span>eo <span>Min</span>ute<span>s</span></h1>

    <div class="menu">
        <%-- these buttons show or hide the corresponding panels below --%>
        <button id="help_button" name="help" class="btn btn-info" onclick="showPanel('help');">?</button>
        <button id="directory_button" name="new-directory" class="btn btn-info" onclick="showDirectory();">Directory</button>
        <button id="video_button" name="new-video" class="btn btn-info" onclick="showVideo();">Video</button>
        <button id="note_button" name="show-time" class="btn btn-info" onclick="showNote();">Note</button>
        <%-- <button id="survey_button" name="survey" class="btn btn-info" onclick="showPanel('survey');makeSurveyQuestion();">Survey</button>
        <button id="comment_button" name="feedback" class="btn btn-info" onclick="makeComment();">Comment</button>
        <button id="ask_button" name="ask-question" class="btn btn-info" onclick="makeAskQuestion();">Ask</button>
        <button id="player_button" name="player-command" class="btn btn-info" onclick="makePlayerCommand();">Play</button>
        <button id="link_button" name="make-link" class="btn btn-info" onclick="makeLink();">Link</button>
                --%>
    </div>



    <div id="watcher">


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




        <div id="video_input" class="aquapanel">

            <form name="video_form" action="<c:choose><c:when test="${sessionScope.editVideo != null}">edit</c:when><c:otherwise>new</c:otherwise></c:choose>-video" method="post">

                <label for="new_video_title">Your title (optional)</label>
                <input id="new_video_title" name="title"<c:if test="${sessionScope.editVideo.title != null}"><c:out value="${sessionScope.editVideo.title}"/></c:if> placeholder="Your title for this video"/><br />

                <label for="youtube_url">YouTube Video URL</label><br />
                <input type="text" id="youtube_url" name="youtube_url" placeholder="https://youtu.be/R4nd0mCh4r5"<c:if test="${sessionScope.editVideo.youTubeVideo.youTubeId != null}"> value="<c:out value="${sessionScope.editVideo.youTubeVideo.youTubeId}"/>"</c:if>/><br />
                <button type="submit" class="btn btn-primary" id="add_video_button"><c:choose>
                        <c:when test="${sessionScope.editVideo != null}">Create</c:when>
                        <c:otherwise>Save</c:otherwise>
                    </c:choose> Video
                </button>
<%-- TODO show 'delete' if existing video, 'discard' if new --%>
                <button id="delete_video_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-video?videoId=${sessionScope.editVideo.id}';">Delete</button>
            </form>
        </div>




        <div id="note_input" class="aquapanel">

            <form name="note_input_form" accept-charset="utf-8" action="new-note" method="post">
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

                <input type="hidden" name="timeStampStart" value="<c:if test="${sessionScope.editNote.start != null}"><c:out value="${sessionScope.editNote.start}"/></c:if>" />
                <label>Start:</label> <span id="time_stamp_start"><c:if test="${sessionScope.editNote.start != null}"><c:out value="${sessionScope.editNote.start}"/></c:if></span><br />

                <button id="reset_note_time" type="button" onclick="resetTime();">Set Time</button>

                <%--input type="hidden" name="timeStampEnd" value="0" />
                <label>End:</label> <span id="time_stamp_end">0</span><br /--%>

                <c:if test="${sessionScope.editNote != null}">
                <input type="hidden" id="hidden_note_id" name="noteId" value="${sessionScope.editNote.id}" />
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


<%--

        <div id="link_input" class="aquapanel">
            <form name="link_input_form" method="get" action="#">

                <label>Link Text</label><br />
                <input id="linkText" type="text" name="userLinkText" /><br />

                <label>YouTube Video URL:</label><br />
                <input id="videoLink" type="url" name="userVideoUrl" /><br />

                <input type="hidden" name="timeStampPrompt" />
                <label>Prompt time:</label> <span id="link_time_stamp_prompt"></span>s<br />

                <label>Your link</label>
                <div id="finished_link"></div>

                <br />
                <button type="button" class="btn btn-primary" onclick="makeLink()">Link with time</button>
            </form>
        </div>
        --%>


        <%--
                        <div id="survey_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
                            <form name="survey_input_form">

                                <label>Question:</label><br />
                                <textarea name="question"></textarea><br />

                                <input type="hidden" name="timeStampPrompt" />
                                <label>Prompt time:</label> <span id="time_stamp_prompt"></span><br />

                                <br />
                                <input type="submit" value="Update Survey" />
                            </form>
                        </div>

        <div id="comment_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
            <form name="comment_input_form">

                <label>Comment:</label><br />
                <textarea name="comment"></textarea><br />

                <input type="hidden" name="timeStampComment" />
                <label>Comment time:</label> <span id="time_stamp_comment"></span><br />

                <br />
                <input type="submit" class="btn btn-primary" value="Give Feedback" />
            </form>
        </div>


        <div id="ask_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
            <form name="question_input_form">

                <label>Ask Question:</label><br />
                <textarea name="askQuestion"></textarea><br />

                <input type="hidden" name="timeStampAsk" />
                <label>Related time:</label> <span id="time_stamp_ask"></span><br />

                <br />
                <input type="submit" class="btn btn-primary" value="Ask" />
            </form>
        </div>
--%>

    <%--div id="player_input"  class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">

        </div--%>
        <c:import url="help.jsp"/>
    </div>