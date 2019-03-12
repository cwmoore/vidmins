SET FOREIGN_KEY_CHECKS = 0; # https://stackoverflow.com/a/8074510
truncate user;
truncate directory;
truncate video;
truncate note;
truncate auth_token;
SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO user (id, firstName, lastName, userName, enc_pass, dateOfBirth) VALUES (1, 'Joe','Coyne','jcoyne','supersecret1','1964-04-01'),(2, 'Fred','Hensen','fhensen','supersecret2','1988-05-08'),(3,'Barney','Curry','bcurry','supersecret3','1947-11-11'),(4,'Karen','Mack','kmack','supersecret4','1986-07-08'),(5, 'Dianne','Klein','dklein','supersecret5','1991-09-22'),(6, 'Dawn','Tillman','dtillman','supersecret6','1979-08-30');
INSERT INTO directory (id, name, userId) VALUES (1,'default', 1),(2,'new dir', 1),(3,'default', 2),(4,'default', 3);
INSERT INTO video (id,title, duration, directoryId) VALUES (1,'great movie', 1200, 1), (2,'superhero movie', 4000, 2), (3,'comedy', 2400, 3);
INSERT INTO note (id, label, text, start, videoId) VALUES (1,'note one', 'one short note', 0, 1),(2,'one note', 'another short note', 0, 1),(3,'noticia', 'all the words', 0, 2),(4,'nota', 'una communicacion pequena', 0, 3);
INSERT INTO auth_token (id, user_id, user_hash, token, expiration, status) VALUES (1, 1, '1', 'token1', NOW(), 0), (2, 1, '1', 'token2', NOW(), 0), (3, 2, '2', 'token3', NOW(), -1), (4, 2, '2', 'token4', NOW(), 0), (5, 3, '3', 'token5', NOW(), -1)