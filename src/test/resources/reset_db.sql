truncate note;
INSERT INTO note (label, text, videoId) VALUES ('note one', 'one short note', 1),('one note', 'another short note', 1),('noticia', 'all the words', 2),('nota', 'una communicacion pequena', 3);
truncate video;
INSERT INTO video (title, duration, directoryId) VALUES ('great movie', 1200, 1), ('superhero movie', 4000, 2), ('comedy', 2400, 3);
truncate directory;
INSERT INTO directory (name, userId) VALUES ('default', 1),('new dir', 1),('default', 2),('default', 3);
truncate user;
INSERT INTO user (firstName, lastName, userName, enc_pass, dateOfBirth) VALUES ('Joe','Coyne','jcoyne','supersecret1','1964-04-01'),('Fred','Hensen','fhensen','supersecret2','1988-05-08'),(,'Barney','Curry','bcurry','supersecret3','1947-11-11'),('Karen','Mack','kmack','supersecret4','1986-07-08'),('Dianne','Klein','dklein','supersecret5','1991-09-22'),('Dawn','Tillman','dtillman','supersecret6','1979-08-30');
