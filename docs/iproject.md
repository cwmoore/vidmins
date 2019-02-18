#### *work in progress...*

# Project

#### Table of Contents
* [Problem Statement](#problem-statement)
* [User Stories](#user-stories)
* [Screen Design](#screen-design)
* [Technologies](#technologies)

__________________

#### Problem Statement


Vidmins allows the viewer to take notes that annotate the timeline for a video,
creating hooks that can be navigated once the user needs to retrieve
the information. By connecting passive listening with active notetaking,
VidMins also helps the student with the task of keeping focused on the material.

Videos are engaging and convenient media, but struggle at the task of
making information accessible outside of linear playback. Video
information is locked in a format
not easily searched and streaming has come to mean automatically
playing the next video that's served up. Returning to a carefully
crafted explanation from the past entails a challenging search.

When it comes to educational material, advances in video authoring tools
have made it ever easier for more people to generate new content,
but what tools are available to the viewer when
faced with an exploding number of video minutes on all conceivable topics?
Or what if it became the case that a certain student needed
desperately to access certain instructions available in the middle of
one of several hour-long lectures from several months prior?

Enter to the scene: Video Minutes https://vidmins.com

VidMins is intended to bring back into detail something
said many hours of lectures previously without re-watching many hours of
lectures, jumping and scrubbing for every relevant segment. One just
has to watch the videos and take notes on their content. 
___________
Other more multipurpose and full featured notetaking apps such as Evernote or Microsoft OneNote
may include embedded media and users can manually (unless there is a shortcut not known to myself)
edit links to playback moments
(eg. [search in OneNote](https://support.office.com/en-us/article/search-notes-in-onenote-539c3b56-accb-4e16-834d-61a6252ad65b)).
The research-oriented Anvil http://www.anvil-software.org/ is an
advanced tool that can perform scientific analysis on video and annotation data.

The webapp http://videonot.es appears to address the creation of
notes on a timeline, such that each line of notes is linked to a moment in the video playback.
Its notes are stored in the user's own Google Drive. They purport to 
support a number of online learning sites such as
Coursera, Udemy, and Khan Academy, and youtube.com itself.

Vidmins seeks to enter this space with a more coherent user experience
and offering some additional features not previously provided in a single app.
___________
Certain other features may be attempted as well, whether it be asking a question
or making a public comment on the video, to enabling playback to trigger audience-response requests
at set timestamps, to gamifying lecture note-taking by creating
a token-based market for video annotations.
___________
#### User Stories
[top](#project)

* *Sign up*
    + A user can login with a YouTube/Google account with OAuth2.
    + The user can sign up and create an account on the service directly.

* *Login*
    + Once the user has an active account, they can log in.

* *Load a video*
    + A logged-in user can load a video by pasting in a link to a video.
    + A new user can begin as a guest by pasting a link to a video.

* *Load multiple videos*
    + A user can load a list of videos from:
        * a YouTube playlist
        * a YouTube channel
        * a YouTube user (?)
        * a YouTube search (?)
        * a text file of URLs
        * a link to an HTML document containing video links

* *Select video*
    + A user can select and load a video from a list.

* *Continuous playback*
    + A user can opt to have videos play one after the other.

* *Edit video list*
    + A user can add or remove videos and change the order of the list.

* *Make a note*
    + Once a video is loaded, a user can create notes linked to the timeline.

* *Edit a note*
    + A user can select a note to edit, change any values, and store it

* *Delete a note*
    + A user can delete one or more notes.

* *Search through resources*
    + Find notes and video related to a search query.

* *Make link*
    + A user can use vidmins.com to create a labelled, timestamped cueing link to a YT video.  

* *Make a screenshot (?)*
    + A user can have a screenshot associated with a note.

* *Export data*
    + A user can save their notes and other data as a chosen filetype to a cloud or local drive.
    + A user can export an embedded HTML document to host annotated video elswhere (or on vidmins.com)

* *Import data*
    + A user can upload a data export file to load onto the service. 

* *Wildcard: ML*
    + The service can associate wikipedia links with video sections.

* *Enhanced controls*
    + A user can skip a number of seconds forward or back while playing video.
    + A user can change the playback speed.
    + A user can start or stop a loaded video.

* *Endure link rot*
    + or maybe not - otes can be saved without timeline info in lieu of extant video
    + a user can export notes
    + a user can replace a video link id if they can find an identical video elsewhere

* *Account settings*
    + a user can control whether videos load and play automatically
    + a user can control some YouTube account settings
    
___________    
#### Screen Design
[top](#project)

Designed for sole purpose of improving playback and learning experience
of educational videos.

On a laptop or desktop monitor, the video player window is on the right,
data entry on the left. Video window size is maximized to see detail.

___________
#### Technologies
[top](#project)

Java 8

Log4J2

JUnit5

CSS/Bootstrap

Apache Hibernate

YouTube IFrame Player API

YouTube Data API

OAuth 2.0