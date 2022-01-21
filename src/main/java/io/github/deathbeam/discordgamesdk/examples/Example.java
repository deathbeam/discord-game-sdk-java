package io.github.deathbeam.discordgamesdk.examples;

import com.sun.jna.Pointer;
import io.github.deathbeam.discordgamesdk.DiscordActivity;
import io.github.deathbeam.discordgamesdk.DiscordCreateParams;
import io.github.deathbeam.discordgamesdk.DiscordGameSDK;
import static io.github.deathbeam.discordgamesdk.DiscordGameSDK.*;
import io.github.deathbeam.discordgamesdk.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.IDiscordCore;
import java.nio.charset.StandardCharsets;

public class Example
{
	public static void main(String[] args)
	{
		// Put your client ID here
		long CLIENT_ID = 0L;

		DiscordGameSDK discord = DiscordGameSDK.INSTANCE;

		DiscordCreateParams params = new DiscordCreateParams();
		params.client_id = CLIENT_ID;
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

		IDiscordCore.ByReference coreReference = new IDiscordCore.ByReference();
		IDiscordCore.ByReference[] coreReferences = (IDiscordCore.ByReference[]) coreReference.toArray(1);

		int result = discord.DiscordCreate(DiscordGameSDK.DISCORD_VERSION, params, coreReferences);

		IDiscordCore core = coreReferences[0];
		System.out.println("Discord create output is: " + result + " and pointer: " + core.getPointer());

		IDiscordActivityManager activityManager = core.get_activity_manager.apply(core);
		DiscordActivity activity = new DiscordActivity();
		activity.state = "In Play Mode".getBytes(StandardCharsets.UTF_8);
		activity.details = "Playing the trumpet".getBytes(StandardCharsets.UTF_8);

		activityManager.update_activity.apply(activityManager, activity, null, new IDiscordActivityManager.update_activity_callback_callback_callback()
		{
			@Override
			public void apply(Pointer callback_data, int result)
			{
				System.out.println("update activity callback result is " + result);
			}
		});

		while (true) {
			core.run_callbacks.apply(core);
		}

	}
}
