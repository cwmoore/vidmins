const Note = () => {
    this.videoId = null; // YouTube video ID
    this.label = null; // the label appears on the video timeline
    this.text = null; // free text or other data that can be represented as text
    this.tags = new Set(); // a small number of associated tokens
    this.start = 0; // the timestamp in milliseconds of the start time of the segment
    this.end = 0; // the timestamp in milliseconds of the end time of the segment

    // read the form data into this Note
    this.readForm = () => {
        this.videoId = videoId;
        this.label = document.note_input_form.label.value;
        this.text = document.note_input_form.note_text.value;
        this.addTag(document.note_input_form.tag.value);
        this.start = document.note_input_form.timeStampStart.value;
        this.end = document.note_input_form.timeStampEnd.value;
    }

    // add a tag
    this.addTag = () => {
        this.tags.add(document.note_input_form.tag.value);
        document.note_input_form.tag.value = "";
    }

    // update the tags list in the DOM
    this.updateTagHtml = () => {
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
    this.store = () => {

    }
}

/*const makeUrlParts = () => {
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

const init = () => {
    let store_note_btn = document.querySelector("#store_note_btn");
    store_note_btn.addEventListener("click", submitForm, false);
}

const submitForm = () => {
    let errors = validateForm();

    if (errors.length > 0) {
        // report errors
    } else {
        // submit the form
    }
}

const validateForm = () => {
    let errors = {};

    if (true) {
        // add error messages
    }

    return errors;
}

const replaceActiveFeature = (activeFeature, newFeature) => {

}

const showPanel = (feature) => {
    hidePanels();
    deselectButtons();
    document.getElementById(feature + "_button").classList.add("active");
    document.getElementById(feature + "_input").style.display = "block";
}

const hidePanels = () => {
    let feature;
    const features = ["help", "note", "link", "comment", "ask"];

    for (feature of features) {
        hidePanel(feature);
    }
}

const hidePanel = (feature) => {
    document.getElementById(feature + "_input").style.display = "none";
}

const deselectButtons = () => {
    let feature;
    const features = ["help", "note", "link", "comment", "ask"];

    for (feature of features) {
        deselectButton(feature);
    }
}

const deselectButton = (feature) => {
    const button = document.getElementById(feature + "_button");

    if (button.classList.contains("active")) {
        button.classList.remove("active");
    }
}

const processInput = () => {
    // get form data
    // assign a unique identifier
    // insert data into file in timestamp order
    // or use database
    // return error or other messages
}

const getTimeParts = (totalSeconds) => {
    let elSeconds = Math.floor(totalSeconds % 60);
    let elMinutes = Math.floor(totalSeconds / 60);
    let elHours = -1
    return {seconds: elSeconds, minutes: elMinutes, hours: elHours};
}

const showTime = () => {
    let msg = "";

    msg += "getCurrentTime: " + Math.floor(player.getCurrentTime());
    msg += "\ngetDuration: " + player.getDuration();
    msg += "\nRemaining: "
        + (player.getDuration() - Math.floor(player.getCurrentTime()));
    alert(msg);
}


const updateVideoWidth = () => {
    // use as much space as possible but no more
}

const resizePlayer = (newWidth, newHeight) => {
    player.setSize(newWidth, newHeight);
}



const makeNote = () => {
    showPanel("note");
    setStartTime(Math.floor(player.getCurrentTime()));
    //setEndTime(Math.floor(player.getCurrentTime()));
    player.pauseVideo();
}

const setStartTime = (timeStamp) => {
    let timeLink = makeYouTubeUrl(timeStamp);

    document.querySelector("#time_stamp_start").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.querySelector("#note_input_form").timeStampStart.value = timeStamp;
}

const setEndTime = (timeStamp) => {
    let timeLink = makeYouTubeUrl(timeStamp);

    document.querySelector("#time_stamp_end").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.querySelector("#note_input_form").timeStampEnd.value = timeStamp;
}

const addTag = () => {}

const makeLink = () => {
    showPanel('link');
    // document.querySelector("#videoLink").value = makeYouTubeUrl(0);

    document.querySelector("#link_time_stamp_prompt").innerText = Math.floor(player.getCurrentTime());

    let url = makeLocalUrl(player.getCurrentTime());
    let link = '<a href="' + url + '">' + document.querySelector("#linkText").value + '</a>';

    // actual link
    let linkNode = document.createElement('div');
    linkNode.innerHTML = link;

    // copyable HTML source for link
    let linkSourceNode = document.createElement('pre');
    linkSourceNode.innerText = link;

    document.querySelector('#finished_link').innerHTML = '';
    document.querySelector('#finished_link').appendChild(linkNode);
    document.querySelector('#finished_link').appendChild(linkSourceNode);

    url = makeYouTubeUrl(player.getCurrentTime());
    link = '<a href="' + url + '">' + document.querySelector("#linkText").value + '</a>';

    // actual link
    linkNode = document.createElement('div');
    linkNode.innerHTML = link;

    // copyable HTML source for link
    linkSourceNode = document.createElement('pre');
    linkSourceNode.innerText = link;

    document.querySelector('#finished_link').appendChild(linkNode);
    document.querySelector('#finished_link').appendChild(linkSourceNode);

    player.pauseVideo();
}

const makeYouTubeUrl = (time) => {
    let timeString = '';
    if (Math.floor(time) > 0) {
        timeString = '&t=' + Math.floor(time) + 's';
    }
    return "https://www.youtube.com/watch?v=" + youTubeId + timeString;
}

const makeLocalUrl = (time) => {
    let timeString = '';
    if (Math.floor(time) > 0) {
        timeString = '&t=' + Math.floor(time) + 's';
    }
    return "https://vidmins.com/watch?v=" + youTubeId + timeString;
}

const makeSurveyQuestion = () => {
    showPanel("survey");
    setPromptTime(Math.floor(player.getCurrentTime()));
    player.pauseVideo();
}
const setPromptTime = (timeStamp) => {
    let timeLink = makeYouTubeUrl(timeStamp);

    document.getElementById("time_stamp_prompt").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.note_input_form.timeStampEnd.value = timeStamp;
}

const makeComment = () => {
    showPanel("comment");
    setCommentTime(Math.floor(player.getCurrentTime()));
    player.pauseVideo();
}
const setCommentTime = (timeStamp) => {
    let timeLink = makeYouTubeUrl(timeStamp);

    document.getElementById("time_stamp_comment").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.comment_input_form.timeStampComment.value = timeStamp;
}

const makeAskQuestion = () => {
    showPanel("ask");
    setRelatedTime(Math.floor(player.getCurrentTime()));
    player.pauseVideo();
}

const setRelatedTime = (timeStamp) => {
    let timeLink = makeYouTubeUrl(timeStamp);

    document.getElementById("time_stamp_ask").innerHTML = '<a href="' + timeLink + '">' + timeStamp + '</a>';
    document.link_input_form.timeStampPrompt.value = timeStamp;
}


/*
* from: https://stackoverflow.com/a/34008994
* use a dummy element to intercept keyboard events and dispatch actions
*
var dummy=document.getElementById("dummyFocus");
dummy.focus();
dummy.addEventListener("keypress",function(event){
  if(event.keyCode== 32){
    if(player.getPlayerState() == 1){
      player.pauseVideo();
    }
    else{
      player.playVideo();
    }
  }
});
*
*
* */

/*
* for sortable tables, didn't work
* https://stackoverflow.com/questions/31888566/sort-table-rows-in-bootstrap
$(document).ready(function() {
    $('#note_table').DataTable();
    $('#video_table').DataTable();
});
*/