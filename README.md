# discord-game-sdk-java

## How to use

[Example.java](src/main/java/io/github/deathbeam/discordgamesdk/examples/Example.java)

## Updating discord-game-sdk

Download discord game SDK from here: https://discord.com/developers/docs/game-sdk/sdk-starter-guide

Build JNAErator from here: https://github.com/deathbeam/JNAerator

Use

```
java -jar jnaerator/target/jnaerator-0.13-SNAPSHOT-shaded.jar \
  -library discord_game_sdk \
  -mode Directory \
  -runtime JNA \
  <path_to_discord_game_sdk.h>
```
