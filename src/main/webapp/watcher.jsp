<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Video Minutes</h1>
    <div class="menu">
        <button id="help_button" name="help" class="btn btn-info" onclick="showPanel('help');">?</button>
        <button id="note_button" name="show-time" class="btn btn-info" onclick="makeNote();">Note</button>
        <button id="link_button" name="make-link" class="btn btn-info" onclick="makeLink();">Link</button>
        <%-- <button id="survey_button" name="survey" class="btn btn-info" onclick="showPanel('survey');makeSurveyQuestion();">Survey</button> --%>
        <button id="comment_button" name="feedback" class="btn btn-info" onclick="makeComment();">Comment</button>
        <button id="ask_button" name="ask-question" class="btn btn-info" onclick="makeAskQuestion();">Ask</button>
    </div>
    <div id="watcher">
        <!-- TODO: use bootstrap navigation -->

        <div id="note_input" class="aquapanel">
            <form id="note_input_form" accept-charset="utf-8" method="post" action="http://localhost:8080/new-note"<%-- onsubmit="processInput(); return false;" --%>>

                <label>Label:</label><br />
                <input type="text" name="label" /><br />

                <label>Text:</label><br />
                <textarea name="note_text"></textarea><br />

                <label>Tags:</label><br />
                <input type="text" name="tag" />

                <button name="add-tag" onclick="addTag()">Add Tag</button><br />
                <span id="tags"></span>

                <input type="hidden" name="timeStampStart" value="0" />
                <label>Start:</label> <span id="time_stamp_start">0</span><br />

                <input type="hidden" name="timeStampEnd" value="0" />
                <label>End:</label> <span id="time_stamp_end">0</span><br />

                <input type="hidden" name="userId" value="${sessionScope.user.id}" />
                <input type="hidden" name="videoId" value="${sessionScope.currentVideo.id}" />

                <br />
                <input id="store_note_btn" type="submit" class="btn btn-primary" value="Store Annotation" />
            </form>
        </div>
        <%-- <div id="notes">
            <ul class="notes"></ul>
        </div>
        <div id="videos">
            <ul class="videos"></ul>
        </div>
--%>

        <div id="link_input" class="aquapanel" method="get" action="#" onsubmit="processInput(); return false;">
            <form name="link_input_form">

                <label>YouTube Video URL:</label><br />
                <input id="videoLink" type="url" name="userVideoUrl" /><br />

                <input type="hidden" name="timeStampPrompt" />
                <label>Prompt time:</label> <span id="link_time_stamp_prompt"></span>s<br />

                <br />
                <input type="submit" class="btn btn-primary" value="Link with time" />
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
        --%>
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
        <div id="help_input" class="aquapanel">
            <h3>App use cases:</h3>
            <h4>One might:</h4>
            <ul>
                <li>pause a video</li>
                <li>obtain a blank card</li>
                <li>write down an idea</li>
                <li>note the timestamp related to the concept</li>
                <li>continue the video</li>
            </ul>
            <h4>Or just as likely one might:</h4>
            <ul>
                <li>encounter a term</li>
                <li>search annotations for that and related terms</li>
                <li>obtain a list of timestamp links to multiple videos</li>
                <li>view the provided video material</li>
            </ul>
            <h4>A presenter might:</h4>
            <ul>
                <li>prepare a video with timely annotations</li>
                <li>pause the video at each point to explain</li>
                <li>include additional linked content</li>
            </ul>
            <h4>An online class might:</h4>
            <ul>
                <li>watch video</li>
                <li>at each point the video pauses</li>
                <li>answer quiz or survey question related to the video</li>
            </ul>
            <h4>Likely target users:</h4>
            <ul>
                <li>A film afficionado</li>
                <li>An online college student</li>
                <li>An online college</li>
                <li>A music lover adding song titles/start times to a full album.</li>
                <li>A video content creator looking for viewer response</li>
                <li>Political commentary on news coverage</li>
                <li>Investigator analyzing event footage</li>
                <li>Content owners defending copyright</li>
                <li>Trend/topic exploration</li>
            </ul>
        </div>

    </div>