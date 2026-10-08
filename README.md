# Flappy Bird Game with MySQL Integration 

A classic Flappy Bird clone built in **Java** utilizing **Swing** for graphics and integrated with a **MySQL database** to handle user authentication (sign up/login) and score tracking.

---

## Features
* **User Authentication**: Secure sign-up and login system connected to a MySQL database.
* **Score Tracking**: Saves high scores dynamically to a MySQL `flappy_bird` database.
* **Interactive Gameplay**: Smooth bird physics, obstacle pipes, and collision detection.
* **Leaderboard**: Displays top player scores retrieved directly from the database.

---

## Technologies Used
* **Programming Language**: Java
* **UI Framework**: Java Swing / AWT
* **Database**: MySQL (MySQL Workbench)
* **IDE**: Eclipse

---

## Setup and Installation

### 1. Database Setup
1. Open **MySQL Workbench** and create a database named `flappy_bird`:
   ```sql
   CREATE DATABASE flappy_bird;
   USE flappy_bird;
