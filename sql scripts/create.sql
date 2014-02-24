/* Create script 
GROUP 03
AUTHORS:
Ciprian Ettore  Ettore.Ciprian@stud-inf.unibz.it
Marco Zanellati Marco.Zanellati@stud-inf.unibz.it
Grueber Julian  
Stefano Mich    michste93@gmail.com 
*/

CREATE TABLE cinema
(
  cinemaId integer PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  address VARCHAR(255) NOT NULL,
  city VARCHAR(30) NOT NULL,
  openHr time NOT NULL default '00:00',
  closeHr time NOT NULL default '24:00');

CREATE TABLE movie
 (
  title VARCHAR(255) PRIMARY KEY,
  category VARCHAR(30) NOT NULL,
  directorName VARCHAR(60) NOT NULL);

CREATE TABLE theater
(
  theaterNo SERIAL PRIMARY KEY, /* auto increment theater number id when a new theater is inserted */
  capacity integer NOT NULL,
  cinemaId integer NOT NULL,
  FOREIGN KEY (cinemaId) REFERENCES cinema ON DELETE CASCADE ON UPDATE CASCADE);/* reference to table cinema to keep referential integrity among theater-cinema */

CREATE TABLE projection
(
  projectionId SERIAL PRIMARY KEY , /* auto increment projection num id if one is insert */
  language VARCHAR(30),
  projectionDate date NOT NULL,
  startHr time NOT NULL,
  endHr time NOT NULL,
  theaterNo integer NOT NULL,
  movieTitle VARCHAR(30) NOT NULL,
  FOREIGN KEY (theaterNo) REFERENCES theater ON DELETE CASCADE ON UPDATE CASCADE,/* reference to table theater to keep referential integrity among single projection-theater */
  FOREIGN KEY (movieTitle) REFERENCES movie ON DELETE CASCADE ON UPDATE CASCADE);/* reference to table movie to keep referential integrity among single projection-movie projected */

CREATE TABLE actor
(
  name VARCHAR(30) NOT NULL,
  surname VARCHAR(30) NOT NULL,
  actorId integer PRIMARY KEY  );

CREATE TABLE plays
(
  actorId integer NOT NULL REFERENCES actor,
  movieTitle VARCHAR(30) NOT NULL REFERENCES movie,
/* references to table actor and movie to keep referential integrity among actor-play-movie */
  PRIMARY KEY  (actorId, movieTitle));

CREATE TABLE customer
(
  email VARCHAR(255) NOT NULL PRIMARY KEY ,
  name VARCHAR(30) NOT NULL,
  surname VARCHAR(30) NOT NULL);
/*If the account of the customer is deleted, the customer remains in the database. */
CREATE TABLE account
(
   accountId VARCHAR(30) PRIMARY KEY, 
   email VARCHAR(255) NOT NULL,
   password VARCHAR(60) NOT NULL, 
   creditCardNo VARCHAR(30) NOT NULL CONSTRAINT standard_creditNo CHECK (char_length(creditCardNo)=16),
   FOREIGN KEY (email) REFERENCES customer ON DELETE CASCADE ON UPDATE CASCADE);
/*When a customer is deleted, also his account is deleted */
/*constraint: credit card number must be of 16 chars */

CREATE TABLE ticket
(
  ticketId SERIAL PRIMARY KEY,
  accountId VARCHAR(30) NOT NULL,
  seatNr integer NOT NULL,
  price money NOT NULL,
  projectionId integer NOT NULL,
  FOREIGN KEY (accountId) REFERENCES account ON DELETE CASCADE ON UPDATE CASCADE, /* reference to table account to keep referential integrity among ticket-account. When an account is deleted, the tickets owned by the account are also deleted */
  FOREIGN KEY (projectionId) REFERENCES projection ON DELETE CASCADE ON UPDATE CASCADE); /* reference to table projection to keep referential integrity among ticket-projection. When a rojection is deleted, the tickets of that projection are also deleted */







