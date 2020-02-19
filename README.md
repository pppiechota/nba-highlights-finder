# nba-highlights-finder

Simple app made as a graduation project for Java: Web Developer course by CodersLab Warsaw. Goals: 
* solving real life issue (however trivial it will be)
* communicating with external APIs
* utilizing knowledge from the course and learning new technologies/classes/interfaces

Application connects to restful API [balldontlie.io](https://www.balldontlie.io/), and retrieves requested schedule. 
Subsequently it connects to YouTube DataAPI, performs a search, applies some filtering and return video IDs for a particular game.   

## Installation

nba-highlights-finder is written with Spring Boot. To run:
1. compile it with maven and execute jar file
2. add your Google ApiKey in youtube.properties
3. add your username and password for database in application.properties

Application uses simple database for NBA teams, it can be built from an attached sql script.

## Technologies

* Java EE
* Spring Boot
* Spring RestTemplate
* YouTube Data API v3
* Jackson
* JSTL and JSP in front
* a bit of jQuery

## Usage

Choose a game from last night or from any other day of a current season to see the highlights. You can also pick your favourite team and choose a game from their schedule. App depends on youtube resources so certain highlights may not be available.

## Contact

Please add suggestions or contact me if you like my project.
