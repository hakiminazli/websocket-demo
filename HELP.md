# WebSocket Demo

This project is a simple Spring Boot WebSocket guessing game. A browser client connects to the server with SockJS + STOMP, sends a guess, and receives the result through a shared topic.

## Stack

- Java 17
- Spring Boot 4
- Gradle
- Spring WebSocket
- SockJS and STOMP in the browser
- Lombok

## Project Behavior

The server exposes a WebSocket endpoint at `/ws` and uses `/app` as the application destination prefix.

Current game flow:

- Client sends a message to `/app/guess`
- Server handles it in `GameController`
- Server publishes the response to `/topic/game`
- All subscribed clients receive the message

The current secret number in the controller is `7`.

## Main Files

- `src/main/java/com/example/websocketdemo/config/WebSocketConfig.java`
  Configures the STOMP broker, application prefix, and SockJS endpoint.
- `src/main/java/com/example/websocketdemo/controller/GameController.java`
  Receives guess messages and returns the result text.
- `src/main/java/com/example/websocketdemo/model/GuessMessage.java`
  Payload model with `player` and `number`.
- `src/main/resources/static/index.html`
  Minimal browser client for connecting and sending guesses.

## Run The App

From the project root:

```bash
./gradlew bootRun
```

On Windows PowerShell:

```powershell
.\gradlew.bat bootRun
```

The app starts on `http://localhost:8080`.

## Use The Demo

1. Start the application.
2. Open `http://localhost:8080` in your browser.
3. Enter a player name.
4. Enter a number and click `Guess`.
5. Watch the message list update with the result from the server.

If the guessed number matches the secret, the server responds with:

```text
<player> guessed CORRECT!
```

Otherwise it responds with:

```text
<player> guessed <number>
```

## Build And Test

Compile the project:

```powershell
.\gradlew.bat compileJava
```

Run tests:

```powershell
.\gradlew.bat test
```

## Notes

- `GuessMessage` uses Lombok `@Data`, so Lombok annotation processing must stay enabled in Gradle.
- The frontend currently connects to `http://localhost:8080/ws` directly.
- `setAllowedOriginPatterns("*")` is convenient for local practice, but it is too open for production use.
