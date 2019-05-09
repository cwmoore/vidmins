<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 id="brand-header"><span>Vid</span>eo <span>Min</span>ute<span>s</span></h1>

    <div class="menu">
        <%-- these buttons show or hide the corresponding panels below --%>
        <button id="help_button" name="help" class="btn btn-info" onclick="showPanel('help');">?</button>
        <button id="directory_button" name="new-directory" class="btn btn-info" onclick="makeNewDirectory();">Directory</button>
        <button id="video_button" name="new-video" class="btn btn-info" onclick="makeNewVideo();">Video</button>
        <button id="link_button" name="make-link" class="btn btn-info" onclick="makeLink();">Link</button>
        <button id="note_button" name="show-time" class="btn btn-info" onclick="makeNote();">Note</button>
        <%-- <button id="survey_button" name="survey" class="btn btn-info" onclick="showPanel('survey');makeSurveyQuestion();">Survey</button>
        <button id="comment_button" name="feedback" class="btn btn-info" onclick="makeComment();">Comment</button>
        <button id="ask_button" name="ask-question" class="btn btn-info" onclick="makeAskQuestion();">Ask</button>
        <button id="player_button" name="player-command" class="btn btn-info" onclick="makePlayerCommand();">Play</button>
                --%>
    </div>



    <div id="watcher">



        <div id="directory_input" class="aquapanel">
            <form name="directory_form" action="new-directory" method="post">
                <label for="directoryNameField">Name</label><br />
                <input type="text" name="directoryName" id="directoryNameField"<c:if test="${currentDirectory != null}"> value="<c:out value="${currentDirectory.name}"/>"</c:if> /><br />

                <label for="directoryDescriptionField">Description</label>
                <textarea name="directoryDescription" id="directoryDescriptionField"><c:if test="${currentDirectory != null}"><c:out value="${currentDirectory.description}"/></c:if></textarea><br />

                <button type="submit" class="btn btn-primary" id="add_directory_button">Create New</button>

                <button id="delete_directory_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-directory?directoryId=${currentDirectory.id}';">Delete</button>
            </form>
        </div>




        <div id="video_input" class="aquapanel">
            <form id="add_video_form" action="new-video" method="post">
                <label for="youtube_url">YouTube Video URL</label><br />
                <input type="text" id="youtube_url" name="youtube_url" placeholder="https://youtu.be/R4nd0mCh4r5"<c:if test="${currentVideo != null}"> value="<c:out value="${currentVideo.youTubeVideo.youTubeUrl}"/>"</c:if>/><br />
                <button type="submit" class="btn btn-primary" id="add_video_button">Add Video</button>

                <button id="delete_video_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-video?videoId=${currentVideo.id}';">Delete</button>
            </form>
        </div>




        <div id="note_input" class="aquapanel">
            <form id="note_input_form" accept-charset="utf-8" method="post" action="new-note">

                <label>Label:</label><br />
                <input type="text" name="label"<c:if test="${currentNote != null}"> value="${currentNote.label}"</c:if>/><br />

                <label>Text:</label><br />
                <textarea name="note_text"><c:if test="${currentNote != null}"><c:out value="${currentNote.text}"/></c:if></textarea><br />

                <%--label>Tags:</label><br />
                <input type="text" name="tag" />

                <button name="add-tag" onclick="addTag()">Add Tag</button><br />
                <span id="tags"></span--%>

                <input type="hidden" name="timeStampStart" value="0" />
                <label>Start:</label> <span id="time_stamp_start">0</span><br />

                <%--input type="hidden" name="timeStampEnd" value="0" />
                <label>End:</label> <span id="time_stamp_end">0</span><br /--%>

                <c:if test="${currentNote != null}">
                <input type="hidden" id="hidden_note_id" name="noteId" value="${currentNote.id}" />
                </c:if>

                <input type="hidden" name="videoId"
                       value="<c:choose><c:when test="${currentNote != null}">${currentNote.video.id}</c:when><c:otherwise>${sessionScope.currentVideo.id}</c:otherwise></c:choose>" />

                <br />
                <input id="store_note_btn" type="submit" class="btn btn-primary" value="Store" />

                <button id="delete_note_btn" type="button" class="btn btn-danger" onclick="window.location.href = 'delete-note?noteId=${currentNote.id}';">Delete</button>
            </form>

        </div>




        <div id="link_input" class="aquapanel">
            <form name="link_input_form" method="get" action="#">

                <label>Link Text</label><br />
                <input id="linkText" type="text" name="userLinkText" /><br />
<%--
                <label>YouTube Video URL:</label><br />
                <input id="videoLink" type="url" name="userVideoUrl" /><br />
--%>
                <input type="hidden" name="timeStampPrompt" />
                <label>Prompt time:</label> <span id="link_time_stamp_prompt"></span>s<br />

                <label>Your link</label>
                <div id="finished_link"></div>

                <br />
                <button type="button" class="btn btn-primary" onclick="makeLink()">Link with time</button>
            </form>
        </div>
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