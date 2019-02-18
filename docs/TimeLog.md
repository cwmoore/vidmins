# Time Log

| Date | Task | Hours | Notes|
|------|------|-------|------|
| 1/18/16| Set up project repository, write problem statement, journal example, technologies used| 2 | |
| 1/24/16 | Added a little more detail to the journal and technologies used in the readme.md   | .5  |   | 
| 1/24/16 | Began researching the google maps api and weather services. Documented my findings in this repo | .5 | |
| 1/29/16 | Wireframes, created project in intellij, recorded video of the process/converted/posted to youtube, journal entry and updates to the project plan, created jsps | 4 | |
| 1/30/16 | Wireframe review and updates | .5 | |
| 2/2/16  | Added log4j, junit, hibernate, sql driver, geronimo, built first daos, updated week 3 details and exercises  | 4 | |
| 2/5/16 | Design and create database tables, journal entry, time entry, plan update | 1.5
| 2/5/16 | Created database diagram, added some missing foreign keys|.5|
| 2/12/16| Added authentication - jsps in the user directory require user role, wrote up week 5 material| 3 |
| 2/13/16| Added bootstrap. Added javascript/jquery to make current page "active" on navbar| 2 | |
| 2/13/16| Added entity for each db table along with a first cut at the  hibernate config for each entity| 1 | |
| 3/2/16| Researched snow depth services.| 1 | |
| 3/3/16| Started user registration - added form and servlet. | 1 | |
| 3/5/16| Added validation to sign up form, now adds user and role on happy path| 1 | |
| 3/6/16| Added daos for trails and trailreport + unit tests, added dump of data to the jsps| 2 | |
| 3/24/16| Bought domain, added jstl jar, and improved display of trail reports using for each and a table | 1 | |
| 3/25/16| Replicated database on openshift, set up domain, set up auth for fbtr only| 3 | |
| 4/5/16| Add Trail servlet and jsp | 1 | |
| 4/6/16| Fixed the content under navbar issue, pathing issues, styling, about page| 2 | |
| 4/20/16|Added remaining fields to Add Trail jsp | .5 | |
| 4/24/16|Added abstract Dao and unit test for abstract dao (difficulty) | 1 | |
| 4/25/16|Added type and difficulty dropdowns. Saving from jsp to trail table.| 2| |
| 5/6/16|Refactoring and updating unit tests | 3  | |
| 10/23/17| (Week of) Added maven, lombok, revamped the db, added in additional unit tests, and added more methods to the generic dao to allow for queries with properties|8||
| 10/30/17| (Week of) Adding additional functionality to the add trail (difficulty, grooming, user). More refactoring on the db. More unit testing. Added  more lombok methods into the rest of the entities |4||
| 11/6/17| (Week of) Added location table, entity, display on trail, create when adding trail. Spent way too much time looking at templates and materialize. Updated readme and timelog|4||
|11/9/17|Searched for a service and finally sent off a request to find out if there is a service currently available for snow depth. The data is here: http://www.nohrsc.noaa.gov/nearest/index.html?city=Hayward%2C+wi. Previous semester student built a scraper for this, but it feels like this should be accessible via an already built service. Received a "no" response, but continued digging and I think this may provide what's needed: https://www.ncdc.noaa.gov/cdo-web/webservices/v2#stations. Requested an api key|2||
|11/16/17|Added google captcha and parameterized values for local versus aws db (user, connection, password) so that these are configured using maven at build time |6|
|10/5/18|Replaced lombok, log4j, junit and hibernate with latest versions, more db restructuring/renaming|3|