function Note() {
    this.videoId = null; // YouTube video ID
    this.label = null; // the label appears on the video timeline
    this.text = null; // free text or other data that can be represented as text
    this.tags = new Set(); // a small number of associated tokens
    this.start = 0; // the timestamp in milliseconds of the start time of the segment
    this.end = 0; // the timestamp in milliseconds of the end time of the segment

    // read the form data into this Note
    this.readForm = function() {
        this.videoId = videoId;
        this.label = document.note_input_form.label.value;
        this.text = document.note_input_form.note_text.value;
        this.addTag(document.note_input_form.tag.value);
        this.start = document.note_input_form.timeStampStart.value;
        this.end = document.note_input_form.timeStampEnd.value;
    }

    // add a tag
    this.addTag = function() {
        this.tags.add(document.note_input_form.tag.value);
        document.note_input_form.tag.value = "";
    }

    // update the tags list in the DOM
    this.updateTagHtml = function() {
        let tagsHtml = "";
        let tagIterator = this.tags.values();

        // build the string
        let currentTag = tagIterator.next().value;
        tagsHtml = currentTag;

        while (currentTag = tagIterator.next().value) {
            tagsHtml += ", " + currentTag;
        }

        // update the DOM
        document.getElementById("tags").innerHTML = tagsHtml;
    }

    // save this note
    this.store = function() {

    }
}

/*function makeUrlParts() {
    // after: https://stackoverflow.com/a/48990315
    String urlid="";
    String url="http://www.youtube.com/watch?v=0zM4nApSvMg#t=0m10s";
    Pattern pattern = Pattern.compile("(?:http|https|)(?::\\/\\/|)(?:www.|)(?:youtu\\.be\\/|youtube\\.com(?:\\/embed\\/|\\/v\\/|\\/watch\\?v=|\\/ytscreeningroom\\?v=|\\/feeds\\/api\\/videos\\/|\\/user\\\\S*[^\\w\\-\\s]|\\S*[^\\w\\-\\s]))([\\w\\-\\_]{11})[a-z0-9;:@#?&%=+\\/\\$_.-]*");
        Matcher result = pattern.matcher(url);
        if (result.find())
        {
             urlid = result.group(1);
        }
        /*
        latest short format: http://youtu.be/NLqAF9hrVbY
        iframe: http://www.youtube.com/embed/NLqAF9hrVbY
        iframe (secure): https://www.youtube.com/embed/NLqAF9hrVbY
        object param: http://www.youtube.com/v/NLqAF9hrVbY?fs=1&hl=en_US
        object embed: http://www.youtube.com/v/NLqAF9hrVbY?fs=1&hl=en_US
        watch: http://www.youtube.com/watch?v=NLqAF9hrVbY
        users: http://www.youtube.com/user/Scobleizer#p/u/1/1p3vcRhsYGo
        ytscreeningroom: http://www.youtube.com/ytscreeningroom?v=NRHVzbJVx8I
        any/thing/goes!:http://www.youtube.com/sandalsResorts#p/c/54B8C800269D7C1B/2/PPS-8DMrAn4
        any/subdomain/too: http://gdata.youtube.com/feeds/api/videos/NLqAF9hrVbY
        more params: http://www.youtube.com/watch?v=spDj54kf-vY&feature=g-vrec
        query may have dot: http://www.youtube.com/watch?v=spDj54kf-vY&feature=youtu.be
        nocookie domain: http://www.youtube-nocookie.com
}

// start new topic as it begins or mark at end and jump back
// signup token
// stored notes search/jump to another video
// study packages (pre-annoted video material)

*/

function replaceActiveFeature(activeFeature, newFeature) {

}

function showPanel(feature) {
    hidePanels();
    deselectButtons();
    document.getElementById(feature + "_button").classList.add("active");
    document.getElementById(feature + "_input").style.display = "block";
}

function hidePanels() {
    let feature;
    const features = ["help", "note", "link", "comment", "ask"];

    for (feature of features) {
        hidePanel(feature);
    }
}

function hidePanel(feature) {
    document.getElementById(feature + "_input").style.display = "none";
}

function deselectButtons() {
    let feature;
    const features = ["help", "note", "link", "comment", "ask"];

    for (feature of features) {
        deselectButton(feature);
    }
}

function deselectButton(feature) {
    const button = document.getElementById(feature + "_button");

    if (button.classList.contains("active")) {
        button.classList.remove("active");
    }
}

function processInput() {
    // get form data
    // assign a unique identifier
    // insert data into file in timestamp order
    // or use database
    // return error or other messages
}

function getTimeParts(totalSeconds) {
    let elSeconds = Math.floor(totalSeconds % 60);
    let elMinutes = Math.floor(totalSeconds / 60);
    let elHours = -1
    return {seconds: elSeconds, minutes: elMinutes, hours: elHours};
}

function showTime() {
    let msg = "";

    msg += "getCurrentTime: " + player.getCurrentTime();
    msg += "\ngetDuration: " + player.getDuration();
    msg += "\nRemaining: "
        + (player.getDuration() - player.getCurrentTime());
    alert(msg);
}

function makeNote() {
    showPanel("note");
    setStartTime(player.getCurrentTime());
    setEndTime(player.getCurrentTime());
    player.stopVideo();
}
function setStartTime(timeStamp) {
    let timeLink = makeUrl(timeStamp);

    document.getElementById("time_stamp_start").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampStart = timeStamp;
}
function setEndTime(timeStamp) {
    let timeLink = makeUrl(timeStamp);

    document.getElementById("time_stamp_end").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampEnd = timeStamp;
}
function addTag() {}

function makeLink() {
    document.getElementById("videoLink").value = makeUrl(player.getCurrentTime());
    document.getElementById("link_time_stamp_prompt").innerText = player.getCurrentTime();
}
function makeUrl(time) {
    // const time_parts = getTimeParts(time);
    // let timeString = "&t=";
    // if (time_parts.hours > 0) {
    //     timeString += time_parts.hours + "h";
    // }
    // if (time_parts.minutes > 0) {
    //     timeString += time_parts.minutes + "m";
    // }
    // if (time_parts.seconds > 0) {
    //     timeString += time_parts.seconds + "s";
    // }
    let timeString = "&t=" + Math.floor(time);
    return "https://www.youtube.com/watch?v=" + youTubeId + timeString + "s";
}

function makeSurveyQuestion() {
    showPanel("survey");
    setPromptTime(player.getCurrentTime());
    player.stopVideo();
}
function setPromptTime(timeStamp) {
    let timeLink = makeUrl(timeStamp);

    document.getElementById("time_stamp_prompt").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampEnd = timeStamp;
}

function makeComment() {
    showPanel("comment");
    setCommentTime(player.getCurrentTime());
    player.stopVideo();
}
function setCommentTime(timeStamp) {
    let timeLink = makeUrl(timeStamp);

    document.getElementById("time_stamp_comment").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampEnd = timeStamp;
}

function makeAskQuestion() {
    showPanel("ask");
    setRelatedTime(player.getCurrentTime());
    player.stopVideo();
}
function setRelatedTime(timeStamp) {
    let timeLink = makeUrl(timeStamp);

    document.getElementById("time_stamp_ask").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampEnd = timeStamp;
}

function updateVideoWidth() {
    // use as much space as possible but no more
}