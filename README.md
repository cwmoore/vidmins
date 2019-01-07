# User Display Exercise

This exercise will provide you with practice with IntelliJ, Tomee+, MySqL and Maven, while providing a refresher
on many topics from Advanced Java including servlets, jsps, properties, jdbc, expression language (el) and beans. 

## Getting Started

To begin using this project:

1. Accept the assignment using the provided link. This will create a repository containing this exercise.
2. Copy the clone url from your repository in GitHub.
3. From the IntelliJ Welcome Screen, check out the project from Git/GitHub using the clone url you copied above.
4. Edit the run configuration.
5. Build and deploy.
6. The application should deploy, but it will not function correctly since there is no database connected at this point.
7. Explore the code to understand what's been given to you.


## Next Steps

1. Create the database. You can use the sql below, or, you may write your own.

        mysql> CREATE DATABASE sample;
       
1. Select the database you just created. 

        mysql> use sample
    
1. Create the user table.
    
        mysql> create table user
               (
                 id int auto_increment
                   primary key,
                 first_name varchar(25) null,
                 last_name varchar(30) null,
                 user_name varchar(15) null,
                 password varchar(30) null,
                 date_of_birth date null,
                 constraint users_user_name_uindex
                 unique (user_name)
               );

1. Populate the user table. 

        insert into user values (1, 'Joe', 'Coyne', 'jcoyne', 'supersecret1', '1964/04/01');
        insert into user values (2, 'Fred', 'Hensen', 'fhensen', 'supersecret2', '1988/05/08');
        insert into user values (3, 'Barney', 'Curry', 'bcurry', 'supersecret3',  '1947/11/11');
        insert into user values (4, 'Karen', 'Mack', 'kmack', 'supersecret4', '1986/07/08');
        insert into user values (5, 'Dianne', 'Klein', 'dklein', 'supersecret5', '1991/01/22');
        insert into user values (6, 'Dawn', 'Tillman', 'dtillman', 'supersecret6', '1979/08/30');
        
1. Find and complete the TODO in "database.properties" to configure the program to use the database you created above. Question: did you know you can easily find all TODOs in intelliJ with a single click?

1. Once the TODOs are completed, build and deploy. The application should open in the browser showing you a list of users. It's not pretty, but you'll fix that in a little bit.

## Next Next Steps

1. Add to the project such that the completed application provides the ability to search for and display users from the database. The user display/search results
    should include userid, first name, last name, and age, and be displayed in a table. At minimum, provide a last name search. Some TODOs have been added to help you get started.
    
## Exercise Submission

To receive credit for this exercise:

1. Take a screenshots of your search screen and search results screen.
2. Create a directory named "screenshots" to your project. 
3. Add the screenshots from step 1 to your screenshots directory.
2. Push your completed code to GitHub. 
3. Verify your latest code and screebshots are visible on GitHub.

