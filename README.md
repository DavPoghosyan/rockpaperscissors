# Rock-Paper-Scissors

Welcome to the Rock-Paper-Scissors game! This is a simple implementation of the classic game where you can play against a computer opponent,
view game statistics, and reset the game.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Features

- **Play a Game**: Choose between Rock, Paper, or Scissors to play against the computer.
- **Game Statistics**: View the number of wins, losses, and draws.
- **Reset Game**: Reset the game to start fresh.

## Installation

### Prerequisites

- Java 11 or later
- Gradle 6.8 or later
- Spring Boot

### Clone the Repository

```bash
git clone https://github.com/yourusername/rockpaperscissors.git
cd rockpaperscissors
```

### Build the Application

```bash
./gradlew build
```

## Usage

### Running the Application

To run the application, use the following command:

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`.

## API Endpoints

### Play Game

**POST** `/api/game/play`

**Parameters**:

- `move` (string): The player's move. Must be "ROCK", "PAPER", or "SCISSORS".

**Response**:

- JSON object containing:
    - `userMove`: The player's move.
    - `computerMove`: The computer's move.
    - `result`: The result of the game ("WIN", "LOSE", "DRAW").
    - `remainingRounds`: Number of remaining rounds.

**Example Request**:

```bash
curl -X POST "http://localhost:8080/api/game/play" -d "move=ROCK"
```

**Example Response**:

```json
{
  "userMove": "ROCK",
  "computerMove": "PAPER",
  "result": "LOSE",
  "remainingRounds": 9
}
```

### Get Statistics

**GET** `/api/game/statistics`

**Response**:

- JSON object containing:
    - `wins`: Number of wins.
    - `losses`: Number of losses.
    - `draws`: Number of draws.

**Example Request**:

```bash
curl -X GET "http://localhost:8080/api/game/statistics"
```

**Example Response**:

```json
{
  "wins": 3,
  "losses": 2,
  "draws": 1
}
```

### Reset Game

**DELETE** `/api/game/reset`

**Example Request**:

```bash
curl -X DELETE "http://localhost:8080/api/game/reset"
```

**Example Response**:

```HTTP Status
200, OK
```

## Testing

### Run Unit Tests

To run the unit tests, use the following command:

```bash
./gradlew test
```