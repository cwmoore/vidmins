SET FOREIGN_KEY_CHECKS = 0; # https://stackoverflow.com/a/8074510
truncate user;
truncate directory;
truncate video;
truncate note;
truncate auth_token;
truncate contract;
truncate subscription;
truncate youTubeVideo;
SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO user (id, firstName, lastName, userName, enc_pass, dateOfBirth) VALUES (1, 'Joe','Coyne','jcoyne','supersecret1','1964-04-01'),(2, 'Fred','Hensen','fhensen','supersecret2','1988-05-08'),(3,'Barney','Curry','bcurry','supersecret3','1947-11-11'),(4,'Karen','Mack','kmack','supersecret4','1986-07-08'),(5, 'Dianne','Klein','dklein','supersecret5','1991-09-22'),(6, 'Dawn','Tillman','dtillman','supersecret6','1979-08-30');
INSERT INTO directory (id, name, userId) VALUES (1,'default', 1),(2,'new dir', 1),(3,'default', 2),(4,'default', 3);
INSERT INTO video (id, youTubeVideoId, addDate, title, duration, directoryId) VALUES (2, 1,'2019-01-28 10:26:38','Week1Act5',2400,1),(3,2,'2019-01-28 16:31:59','PHP Week 1',3842,1),(4,3,'2019-01-29 16:17:37','Welcome',-1,2);
INSERT INTO note (id, label, text, start, videoId) VALUES (1,'note one', 'one short note', 0, 2),(2,'one note', 'another short note', 0, 3),(3,'noticia', 'all the words', 0, 2),(4,'nota', 'una communicacion pequena', 0, 4);
INSERT INTO auth_token (id, user_id, user_hash, token, expiration, status) VALUES (1, 1, '1', 'token1', NOW(), 0), (2, 1, '1', 'token2', NOW(), 0), (3, 2, '2', 'token3', NOW(), -1), (4, 2, '2', 'token4', NOW(), 0), (5, 3, '3', 'token5', NOW(), -1);
INSERT INTO contract (id, name, description, priceForTimePeriod, timePeriodHours, authorId) VALUES (1, 'no contract', 'default contract is no contract', 0, 0, 4), (2, 'free contract', 'free contract is free', 0, 0, 4), (3, '$10 contract', '$10 contract is $10/year', 10, 8766, 4);
INSERT INTO subscription (id, name, description, start, end, subscriberId, directoryId, contractId) VALUES (1, 'subscription 1', 'subscription description 1', NOW(), NOW(), 3, 2, 2), (2, 'subscription 2', 'subscription description 2', NOW(), NOW(), 2, 1, 3);
INSERT INTO youTubeVideo (id, youTubeId, title, duration, metadata) VALUES (1, '4HzWKwExaeo', 'Week 1 Act 5', -1, '-'), (2, 'dF0NWtxRXsg', 'PHPWk1', -1, '-'), (3, '4KGGkytxlZM', 'Welcome video', -1, '-');