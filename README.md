# _Crowd Sourced Music_

#### _Crowd-sourced website for popular songs in different decades, July 13, 2017_

#### By _**Esvethlynna Pangelinan, Alena Golovina**_

## Description

_This is a music website where users can view the top songs from different decaded.  The user is able to enter their own decade or year and their corresponding favorite songs. The user is able to update the song titles and delete them as well._

## Setup/Installation Requirements

* _At terminal, enter postgres_
* _In a different terminal window, enter psql_
* _Create the databases and tables as follows. In psql, enter:_
* _CREATE DATABASE crowd_sourced_music;_
* _\c crowd_sourced_music;_
* _CREATE TABLE songs (id serial PRIMARY KEY, name varchar, decadeId int);_
* _CREATE TABLE decades (id serial PRIMARY KEY, name varchar);_
* _CREATE DATABASE crowd_sourced_music_test WITH TEMPLATE crowd_sourced_music_
* _To run the program, go into the project folder on the terminal and enter gradle run_
* _In the browser, enter localhost:4567_

## To Backup Databases:
* _Clear the tables:  from psql, enter:_
* _DELETE FROM songs;_
* _DELETE FROM decades;_
* _DROP DATABASE crowd_sourced_music_test;_
* _In your "normal" terminal window, not psql, enter: pg_dump crowd_sourced_music > media.sql_
* _Add changes via add . and commit your changes_
* _Upload project to Github._

## To Restore Databases:
* _Clone the database from Github_
* _Connect to psql and run: CREATE DATABASE crowd_sourced_music;_
* _Run the following command in the terminal (not psql): psql crowd_sourced_music < media.sql_
* _Confirm success.  Switch to psql and run:  \c crowd_sourced_music_
* _Then run: \dt_

## Known Bugs

_None that we are aware of_

## Support and contact details

_If you have any suggestions, please contact Lynn or Alena._

## Technologies Used

_Java, Gradle, Spark, JUnit, Velocity, Postgres, HTML, CSS, Bootstrap_

### License

*This software is licensed under the MIT license.*

Copyright (c) 2017 **_Esvethlynna Pangelinan and Alena Golovina_**
