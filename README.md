# discord-game-sdk-java

Java JNA wrapper for Discord Game SDK

## How to use

[Example.java](discord-game-sdk-examples/src/main/java/io/github/deathbeam/discordgamesdk/examples/Example.java)

## Updating discord-game-sdk-jna

Download discord game SDK from here: https://discord.com/developers/docs/game-sdk/sdk-starter-guide

Build JNAErator from here: https://github.com/deathbeam/JNAerator

Use

```
java -jar <path_to_jnaerator_shaded.jar> \
  -library discord_game_sdk \
  -package io.github.deathbeam.discordgamesdk.jna \
  -mode Directory \
  -runtime JNA \
  -limitComments \
  -f \
  <path_to_discord_game_sdk.h>
```
