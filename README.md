# Lunch Voter Application

## Table of Contents

  - [Features](#features)
  - [Pages](#pages)
    - [Restaurant](#restaurant)
    - [Event](#event)
    - [Vote](#vote)
  - [Project Structure](#project-structure)
    - [Backend](#backend)
    - [Frontend](#frontend)
  - [Installations for Running Application](#installations-for-running-application)
    - [JDK 8](#jdk-8)
    - [Apache Maven](#apache-maven)
    - [Apache Tomcat](#apache-tomcat)
  - [Running Application](#running-application)

## Features

This project goals voting for restaurants belongs to daily lunch events.
Application has three main web page below.

## Pages
Application has 3 main web page.

### Restaurant
The page responsible for creating restaurant with name, website URL, restaurant detail, restaurant address informations or removing restaurants.

### Event
The page responsible for creating event for voting.
User can start to vote restaurant after click event item or vote button.
Admin users can reset all votes belongs to on-going events.
Users can show vote result of any expired event.

### Vote
This page list restaurants for voting. User can vote restaurant and undo given vote.
If users favorite restaurant doesn't exist in restaurant list, user can create a new restaurant.
Furthermore in this page every restaurant item shows users who vote for restaurant and voter numbers.
Every user limited for voting only one restaurant.

## Project Structure
Project consist of two modules lunch-voter-backend and lunch-voter-frontend.

### Backend
This module contains Rest APIs, web configuration and database operations.
Spring Boot, JPA, Java 8, H2 Database and Apache Maven technologies used for implementing this module.
###### Note: If server restart or shutdown, all data will be lost in H2 in memory database.

### Frontend
This module implemented in Vue JS framework that contains web pages and JavaScript logics to manipulate DOM.

## Installations for Running Application

### JDK 8

To install JDK 8 please follow instructions written in URL https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html.

### Apache Maven

To install Apache Maven please follow instructions written in URL https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html.https://maven.apache.org/install.html

### Apache Tomcat

Please download installation file for existing operating system from URL https://tomcat.apache.org/download-80.cgi

## Running Application

- Open terminal and move to TomcatInstallationDirectory/bin. Execute './catalina.sh' command. For extra detail about how to run tomcat on different operating systems,  please visit website https://www.oreilly.com/library/view/tomcat-the-definitive/9780596101060/ch01s02.html

- Clone repository

- Move to repository root directory on file system. Open terminal here and execute 'mvn install' command.

- Open ProjectRootDirectory/lunch-voter-backend on file system  and copy lunch-voter.war into
  TomcatInstallationDirectory/webapps directory.

- Visit http://localhost:8080/lunch-voter URL on browser and logic with user credentials below.

######: Predefined users are below.

  Username:
  Password:
  Status:

  Username:
  Password:
  Status:

  Username:
  Password:
  Status:
