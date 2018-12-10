# ArithmeA Telegram Bot

This is the [ArithmeA Telegram-Bot](https://github.com/Tok/ArithmeA-TG) with similar functionality as
[ArithmeA IRC Bot](https://github.com/Tok/ArithmeA-Bot/) based on 
[ArithmeA 2000](https://github.com/Tok/ArithmeA-2000).

## Usage

If you want to run your own instance of the ArithmeA Telegram Bot, first setup your Bot with Telegrams BotFather, 
then create and run the standalone jar like this:

- Create a text file with the token from Telegrams BotFather to `/resources/secret-token.txt`.
- Consider changing the setup at `/src/arithmea/config.clj` and providing your own wordlists at `/resources/wordlists`.
- Compile the source using your IDE or directly in Leiningen with `lein compile`.
- Run the `uberjar` Leiningen task to build `/target/arithmea-[version]-SNAPSHOT-standalone.jar`.
- Then run the standalone jar directly or with one of the provided scripts `./run-standalone.sh` or `run-standalone.bat`.

## Wordlist

Found at: https://github.com/first20hours/google-10000-english/blob/master/google-10000-english-usa.txt

## Gematria Method Documentation

https://github.com/Tok/ArithmeA-TG/blob/master/doc/intro.md
