# discord-game-sdk-java

## Updating discord-game-sdk

Download discord game SDK from here: https://discord.com/developers/docs/game-sdk/sdk-starter-guide

Build JNAErator from here: https://github.com/nativelibs4java/JNAerator

Use

```
java -jar jnaerator-0.13-SNAPSHOT-shaded.jar \
  -library discord_game_sdk \
  -mode Directory \
  -runtime JNA \
  discord_game_sdk.h
```
