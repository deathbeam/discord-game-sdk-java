package io.github.deathbeam.discordgamesdk.examples;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.DiscordCreateParams;
import io.github.deathbeam.discordgamesdk.jna.Discord_game_sdkLibrary;
import static io.github.deathbeam.discordgamesdk.jna.Discord_game_sdkLibrary.*;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordCore;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.strToByteArr;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleJNA
{
	public static void main(String[] args) throws InterruptedException
	{
		final long APPLICATION_ID = Long.parseLong(args[0]);

		final DiscordCreateParams params = new DiscordCreateParams();
		params.client_id = APPLICATION_ID;
		params.application_version = DISCORD_APPLICATION_MANAGER_VERSION;
		params.user_version = DISCORD_USER_MANAGER_VERSION;
		params.image_version = DISCORD_IMAGE_MANAGER_VERSION;
		params.activity_version = DISCORD_ACTIVITY_MANAGER_VERSION;
		params.relationship_version = DISCORD_RELATIONSHIP_MANAGER_VERSION;
		params.lobby_version = DISCORD_LOBBY_MANAGER_VERSION;
		params.network_version = DISCORD_NETWORK_MANAGER_VERSION;
		params.overlay_version = DISCORD_OVERLAY_MANAGER_VERSION;
		params.storage_version = DISCORD_STORAGE_MANAGER_VERSION;
		params.store_version = DISCORD_STORE_MANAGER_VERSION;
		params.voice_version = DISCORD_VOICE_MANAGER_VERSION;
		params.achievement_version = DISCORD_ACHIEVEMENT_MANAGER_VERSION;

		final IDiscordCore.ByReference[] ptr = (IDiscordCore.ByReference[]) new IDiscordCore.ByReference().toArray(1);
		final int result = Discord_game_sdkLibrary.INSTANCE.DiscordCreate(DISCORD_VERSION, params, ptr);
		final IDiscordCore core = ptr[0];
		log.debug("Discord create output is {} and pointer {}", result, core.getPointer());

		final IDiscordActivityManager activityManager = core.get_activity_manager.apply(core);
		final DiscordActivity activity = new DiscordActivity();
		strToByteArr(activity.state, "In Play Mode");
		strToByteArr(activity.details, "Playing the trumpet");

		activityManager.update_activity.apply(activityManager, activity, null, (callback_data, result1) -> log.debug("Update activity callback result is {}", result1));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.debug("Shutting down Discord core {}", core.getPointer());
			core.destroy.apply(core);
		}));

		while (true)
		{
			Thread.sleep(200);
			core.run_callbacks.apply(core);
		}
	}
}