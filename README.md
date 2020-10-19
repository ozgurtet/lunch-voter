# Lunch Voter Application

## Table of Contents

  - [Features](#features)
  - [Pages](#pages)
    - [Restaurant](#restaurant)
    - [Event](#event)
    - [Vote](#vote)
  - [Technologies](#technologies)
    - [Backend](#backend)
    - [Frontend](#frontend)
  - [Installation](#technologies)

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

## Technologies
Project consist of two modules lunch-voter-backend and lunch-voter-frontend.

### Backend
This module contains Rest APIs, web configuration and Database operations.
Spring Boot, JPA, Java 8, H2 Database and Maven technologies used for implementing this module.
###### Note: If server restart or shutdown, all data will lost in H2 in memory database.

### Frontend
This module implemented in Vue JS framework that contains web pages and JavaScript code block to manipulate DOM.

## Installation
