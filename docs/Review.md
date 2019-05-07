# Design/Code Review 3

## Project: Brewmeister

### Developer: Luke Busch

#### Reviewer: Curtis Moore

|Category|Criteria|Rating/Comments|
|--------|---------|---------------|
|**Project Overview**|| |
||Which planned functionality has been met? | Login, CRUD with batches |
|| What planned functionality has not been met? | Weather during brewing |
||Describe the GitHub history and what it demonstrates about the project progress during the semester.| Steady progress briefly interrupted by team project. |
||Describe how peer and instructor feedback/recommendations were incorporated into the project.| Feedback was used to debug and improve flow. |
||Other comments/notes?| The layout and design from a UI tool helped to inform the source code. |
|**JSPs**|| |
||Evaluate the JSPs for templating, business logic, data validation, overall look and feel.| A little sparse still at this point, but usable. Good messaging to users.|
||Other comments/notes?| |
|**Java code quality**|Evaluate the code quality for the following and identify specific areas for improvement (class, method or line number) <li>single-purpose methods <li>well-structured project <li>descriptive naming of packages, classes, methods, variables <li>classes appropriately-sized  (no monster classes) <li> CPD (copy paste detection, meaning are the same lines of code repeated?) <li>are there candidates for super/subclass relationships, abstract classes, interfaces? <li>are any values hard-coded that should be in a properties file? <li>proper exception handling <li>proper error reporting to the user <li> code documentation | Handles correct and incorrect inputs. |
||Other comments/notes?|  |
|**Logging**|Evaluate the use of logging, for example:<li>appropriate use of logging statements in the code for error logging and debugging <li>logging levels used - info, debug, error <li> no occurrences of  System.out.printlns or printStackTrace() <li> logging works on AWS deploy| Uses Logger extensively to output to console, some goes to files. |
||Other comments/notes?| |
|**Unit Tests**|Evaluate the unit tests, for example: <li>tests are truly a unit test rather than a high level functional test <li>test data is appropriately cleaned up or handled <li> there is full coverage of methods that perform business logic or utility functions <li>redundant code is eliminated by using set up and tear down methods, i.e., @Before, @After | Unit tests for data access classes/methods. 100% coverage. |
||Other comments/notes?| |
|**Security**|Evaluate authentication/authorization:| Standard Tomcat Realm authentication. |
||Is it implemented correctly and working locally as well as on AWS? | Yes |
||Other comments/notes?| |
|**Web Service/API integration**|Evaluate the service/api integration, for example: <li> Which service/api is implemented? <li>How is  error handling of the service implemented? For example, what happens if the service is not available?| App will incorporate weather data from a service. TBD |
||Other comments/notes?| |
|**Independent research topic**| What is the independent research topic?| Compression |
||Is the independent research topic/technique implemented in the project?| Unknown |
||Other comments/notes?| |
|**Deployment**| Has the application been successfully deployed to AWS?| Yes |
||Is the hosted application fully functioning?| Not at the moment. |
||Other comments/notes?| A little more visual style/images, a couple more CRUD things, and this app is complete. |
  
  
  











