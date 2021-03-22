# Messages
The messages that will go between the client and server.

## LOGIN
### Request
        Login(Type=LOGIN, String name)

### Response 
        LoginOk(Type=LOGIN_OK, User user)

## CREATE_GAME
### Request 
        CreateGame(Type=CREATE_GAME)

### Response 
        GameCreated(Type=GAME_CREATED, Game game)

## NEW_GUESS
### Request 
        NewGuess(Type=NEW_GUESS, NewGuess guess)

### Response
        GuessResult(Type=GUESS_RESULT, GuessResult result)