# ArithmeA Telegram Bot

This is the [ArithmeA Telegram-Bot](https://github.com/Tok/ArithmeA-TG) with similar functionality as
[ArithmeA IRC Bot](https://github.com/Tok/ArithmeA-Bot/) based on 
[ArithmeA 2000](https://github.com/Tok/ArithmeA-2000).

## Usage

If you want to run your own instance of the ArithmeA Telegram Bot, setup your Bot with BotFather, 
and add your secret token from into a new file at `/resources/secret-token.txt`. 
Setup your configuration in `/src/arithmea/config.clj`, then run the bot directly or compile and build
the standalone uberjar.

- Add `/resources/secret-token.txt` from with token from BotFather.
- Change setup `/src/arithmea/config.clj`.
- Compile with `lein compile`.
- Run `lein uberjar` to build `/target/arithmea-[version]-SNAPSHOT-standalone.jar`.
- Run the standalone jar directly or with one of the provided scripts `./run-standalone.sh` or `run-standalone.bat`.

## Gematria Method Documentation

https://github.com/Tok/ArithmeA-TG/blob/master/doc/intro.md
