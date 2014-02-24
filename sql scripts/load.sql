/* load script 
GROUP 03
AUTHORS:
Ciprian Ettore  Ettore.Ciprian@stud-inf.unibz.it
Marco Zanellati Marco.Zanellati@stud-inf.unibz.it
Grueber Julian  Julian.Goerfer@stud-inf.unibz.it
Stefano Mich michste93@gmail.com 
*/

/*CINEMA inserts*/
INSERT INTO cinema(cinemaId, name, address, city, openHr, closeHr)
VALUES (1, 'Cineplexx', 'via Macello 32', 'Bolzano', '12:00', '24:00');

INSERT INTO cinema(cinemaId, name, address, city, openHr, closeHr)
VALUES (2, 'Eden', 'via Leonardo da Vinci 8', 'Bolzano', '16:00', '24:00');

/*MOVIE inserts*/
INSERT INTO movie(title, category, directorName)
VALUES ('Kill Bill vol.1', 'Action', 'Quentin Tarantino');

INSERT INTO movie(title, category, directorName)
VALUES ('Kill Bill vol.2', 'Action', 'Quentin Tarantino');

INSERT INTO movie(title, category, directorName)
VALUES ('The Hobbit', 'Fantasy', 'Peter Jackson');

INSERT INTO movie(title, category, directorName)
VALUES ('Casablanca', 'Drama', 'Michael Curtiz');

INSERT INTO movie(title, category, directorName)
VALUES ('Oldboy', 'Drama', 'Park Chan-Wook');

INSERT INTO movie(title, category, directorName)
VALUES ('Anchorman', 'Comedy', 'Adam McKay');

INSERT INTO movie(title, category, directorName)
VALUES ('Cabin in the woods', 'Splatter', 'Drew Goddard');


/*THEATER inserts */

INSERT INTO theater(capacity, cinemaId)
VALUES ('30', 1);

INSERT INTO theater(capacity, cinemaId)
VALUES ('120', 1);

INSERT INTO theater(capacity, cinemaId)
VALUES ('120', 1);

INSERT INTO theater(capacity, cinemaId)
VALUES ('60', 2);

INSERT INTO theater(capacity, cinemaId)
VALUES ('80', 2);


/*PROJECTIONS inserts */

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 03, 2014', '22:30', '24:00', 2, 'Kill Bill vol.1');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 04, 2014', '22:30', '24:00', 2, 'Kill Bill vol.2');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 04, 2014', '22:30', '24:00', 3, 'The Hobbit');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 05, 2014', '22:30', '24:00', 1, 'Oldboy');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 05, 2014', '22:30', '24:00', 1, 'Oldboy');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 06, 2014', '21:30', '24:00', 4, 'Casablanca');

INSERT INTO projection(language, projectionDate, startHr, endHr, theaterNo, movieTitle)
VALUES ('English', 'January 06, 2014', '21:30', '24:00', 5, 'Anchorman');

/*ACTOR inserts */

INSERT INTO actor(name, surname, actorId)
VALUES ('Michael', 'Madsen', 1);

INSERT INTO actor(name, surname, actorId)
VALUES ('Martin', 'Freeman', 2);

INSERT INTO actor(name, surname, actorId)
VALUES ('Choi', 'Min-sik', 3);

INSERT INTO actor(name, surname, actorId)
VALUES ('Ian', 'McKellen', 4);

INSERT INTO actor(name, surname, actorId)
VALUES ('Humphrey', 'Bogart', 5);

INSERT INTO actor(name, surname, actorId)
VALUES ('Ingrid', 'Bergman', 6);

INSERT INTO actor(name, surname, actorId)
VALUES ('Uma', 'Thurman', 7);

INSERT INTO actor(name, surname, actorId)
VALUES ('Richard', 'Jenkins', 8);

INSERT INTO actor(name, surname, actorId)
VALUES ('Will', 'Ferrell', 9);

INSERT INTO actor(name, surname, actorId)
VALUES ('David', 'Carradine', 10);


/*PLAYS inserts */

INSERT INTO plays(actorId, movieTitle)
VALUES (1, 'Kill Bill vol.1');

INSERT INTO plays(actorId, movieTitle)
VALUES (2, 'The Hobbit');

INSERT INTO plays(actorId, movieTitle)
VALUES (3, 'Oldboy');

INSERT INTO plays(actorId, movieTitle)
VALUES (4, 'The Hobbit');

INSERT INTO plays(actorId, movieTitle)
VALUES (5, 'Oldboy');

INSERT INTO plays(actorId, movieTitle)
VALUES (1, 'Kill Bill vol.2');

INSERT INTO plays(actorId, movieTitle)
VALUES (10, 'Kill Bill vol.1');

INSERT INTO plays(actorId, movieTitle)
VALUES (10, 'Kill Bill vol.2');

INSERT INTO plays(actorId, movieTitle)
VALUES (7, 'Kill Bill vol.1');

INSERT INTO plays(actorId, movieTitle)
VALUES (7, 'Kill Bill vol.2');

INSERT INTO plays(actorId, movieTitle)
VALUES (5, 'Casablanca');

INSERT INTO plays(actorId, movieTitle)
VALUES (6, 'Casablanca');

INSERT INTO plays(actorId, movieTitle)
VALUES (8, 'Cabin in the woods');

INSERT INTO plays(actorId, movieTitle)
VALUES (9, 'Anchorman');

/*CUSTOMER insert */

INSERT INTO customer(email, name, surname)
VALUES ('Marco.Zanellati@stud-inf.unibz.it', 'Marco', 'Zanellati');

INSERT INTO customer(email, name, surname)
VALUES ('Stefano.Mich@stud-inf.unibz.it', 'Stefano', 'Mich');

INSERT INTO customer(email, name, surname)
VALUES ('Ettore.Ciprian@stud-inf.unibz.it', 'Ettore', 'Ciprian');

INSERT INTO customer(email, name, surname)
VALUES ('Julian.Goerfer@stud-inf.unibz.it', 'Julian', 'Goerfer');


/*ACCOUNT inserts */

INSERT INTO account (accountId, creditCardNo, password, email)
VALUES ('a001', '4082600221948569', 'password', 'Marco.Zanellati@stud-inf.unibz.it');

INSERT INTO account (accountId, creditCardNo, password, email)
VALUES ('a002', '4002600311948432', 'sottone93', 'Ettore.Ciprian@stud-inf.unibz.it');

INSERT INTO account (accountId, creditCardNo, password, email)
VALUES ('a003', '4502600198948234', 'latemar93', 'Stefano.Mich@stud-inf.unibz.it');

INSERT INTO account (accountId, creditCardNo, password, email)
VALUES ('a004', '4082880221929682', 'juligorf91', 'Julian.Goerfer@stud-inf.unibz.it');


/*TICKET inserts */

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a001', 2, 12.50, 1 );

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a002', 25, 12.50, 1 );

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a003', 31, 8.50, 2 );

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a004', 15, 9.50, 3 );

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a002', 46, 7.50, 4 );

INSERT INTO ticket(accountId, seatNr, price, projectionId)
VALUES ('a003', 5, 7.50, 5 );






