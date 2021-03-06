<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>YouTube Link to Time</title>
    <style>
        .title-breakdown { font-size: 1.5em; color: #777; }
        .url-input { width: 20em; }
        .time-input { width: 2.5em; text-align: right; }
        a { text-decoration: none; }
    </style>
</head>
<body>

    <h1>'Video minutes' - vidmins.com</h1>
    <h2><code class="title-breakdown">v-id-min-s</code></h2>
    <h4>linking tool</h4>
    <h5><i>Create a link, cued-up to a specific point in the playback timeline, for almost any video on YouTube</i></h5>

    <div>

        <form name="generate_link">

            <br />Cue start time<br />
            <input type="text" class="time-input" name="minutes" placeholder="0"/>minutes
            <input type="text" class="time-input" name="seconds" placeholder="0"/>seconds

            <br />

            YouTube video URL or ID<br />
            <input type="text" class="url-input" name="base_url" placeholder="https://youtu.be/abc-123_XYZ"/>

            <br />

            <button id="gen_link_btn" type="button" name="user_click">Generate</button>
        </form>

    </div>


    <span id="output_link"></span>


</body>
</html>

<script>




const makeTimeParam = () => {
    let uMinutes = document.generate_link.minutes.value;
    let uSeconds = document.generate_link.seconds.value;
    let onlyDigits = /^\d*$/g;
    let timeParam = '';

    if (uMinutes.match(onlyDigits) && uSeconds.match(onlyDigits)) {
        let minutes = (uMinutes.length > 0) ? parseInt(uMinutes) : 0;
        let seconds = (uSeconds.length > 0) ? parseInt(uSeconds) : 0;
        // this didn't work with shorturls: timeParam = `&t=${minutes}m${seconds}s`;
        timeParam = '&t=' + ((minutes * 60) + seconds);
    } else {
        console.log(`Time param not set. Problem with user strings: ${uMinutes} ${uSeconds}`);
    }

    return timeParam;
}

const processInputLink = () => {

    let userUrl = document.generate_link.base_url.value;

    // improved from: https://www.regextester.com/94360
    let yt_regex = /^(http(s)?:\/\/)?((w){3}.)?youtu(be|.be)?(\.com)?\/(embed\/)?(watch\?.*v=([^&\s]+))?([^\s]+)?/g;
    let yt_id_regex = /^[a-z|A-Z|0-9|-|_]+$/g;

    // let wwwyt_regex = /[https?:\/\/]?[www.]?youtube\.com\/watch\?v=([^&]+)/g;
    // let ytbe_regex = /.*youtu\.be\/([^&]+)/g;

    let youTubeId = false;

    console.log(userUrl);

    if (userUrl.match(yt_regex)) {
        let vidRegex = RegExp(yt_regex);
        console.log("mathed");
        matches = vidRegex.exec(userUrl);

        // number of match groups in yt_regex
        if (matches.length === 11) {
            youTubeId = (matches[9] === undefined) ? matches[10] : matches[9];
        }

        console.log("found youTubeId -> %s", youTubeId);

    } else if (userUrl.match(yt_id_regex)) {
        console.log("Maybe user entered the YouTube video ID instead of a full url");
        youTubeId = userUrl;
    } else {
        console.log("regex match failed for URL: %s", userUrl);
    }

    return youTubeId;
}

// check youtube for video available
const checkYouTubeAPIForVID = (youTubeId) => {
    let videoExists = false;

    // connect to YouTube Data API
    // check response for "match exists"
    // get the title
    // get the duration

    // buildApiRequest('GET',
    //     '/youtube/v3/videos',
    //     {'id': 'Ks-_Mh1QhMc',
    //         'part': 'snippet,contentDetails,statistics'});
    return videoExists;
}


const makeOutputUrl = (youTubeId) => {

    let videoLink = `https://www.youtube.com/watch?v=${youTubeId}`

    let timeParam = makeTimeParam();

    return videoLink + timeParam;
}

const outputLink = (videoYTUrl) => {
    let output_el = document.querySelector('#output_link');
    let ahref = document.createElement('a');
    ahref.setAttribute('href', videoYTUrl);
    ahref.innerText = videoYTUrl;

    output_el.insertBefore(ahref, output_el.firstChild);
}


const makeAddVideoUrl = (youTubeId) => {

    let addVideoLink = `https://vidmins.com/add?vid=${youTubeId}`;

    return addVideoLink;
}

const addVideoLink = (videoLocalUrl) => {

    let output_el = document.querySelector('#output_link');
    let ahref = document.createElement('a');

    ahref.setAttribute('href', videoLocalUrl);
    ahref.innerText = " [add to videos] <- ";

    output_el.insertBefore(ahref, output_el.firstChild);
}


const userClick = () => {
    let idOrFalse = processInputLink();
    if (idOrFalse !== false) {

        let youTubeId = idOrFalse; // not false

        let output_el = document.querySelector('#output_link');
        output_el.insertBefore(document.createElement('br'), output_el.firstChild);
        output_el.insertBefore(document.createElement('br'), output_el.firstChild);

        outputLink( makeOutputUrl(youTubeId) );
        addVideoLink( makeAddVideoUrl(youTubeId) );

    } else {
        console.log("problems recognizing the provided url");
    }

}


const init = () => {
    document.querySelector('#gen_link_btn').addEventListener('click', userClick);
}

init();

</script>