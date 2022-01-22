package io.github.deathbeam.discordgamesdk.examples;

import com.sun.jna.Pointer;
import io.github.deathbeam.discordgamesdk.DiscordActivity;
import io.github.deathbeam.discordgamesdk.DiscordCreateParams;
import io.github.deathbeam.discordgamesdk.DiscordGameSDK;
import static io.github.deathbeam.discordgamesdk.DiscordGameSDK.*;
import io.github.deathbeam.discordgamesdk.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.IDiscordCore;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Example
{
	static void fillStr(byte[] dst, String val) {
		Arrays.fill(dst, (byte)0);
		byte[] valArr = val.getBytes(StandardCharsets.UTF_8);
		System.arraycopy(valArr, 0, dst, 0, valArr.length);
	}

	public static void main(String[] args) throws InterruptedException
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

		final IDiscordCore core = coreReferences[0];
		System.out.println("Discord create output is: " + result + " and pointer: " + core.getPointer());

		core.set_log_hook.apply(core, EDiscordLogLevel.DiscordLogLevel_Debug, null, new IDiscordCore.set_log_hook_callback_hook_callback()
		{
			@Override
			public void apply(Pointer hook_data, int level, Pointer message)
			{
				System.out.printf("LEVEL%s: %s%n", level, message.getString(0));
			}
		});

		IDiscordActivityManager activityManager = core.get_activity_manager.apply(core);
		DiscordActivity activity = new DiscordActivity();
		fillStr(activity.state, "In Play Mode");
		fillStr(activity.details, "Playing the trumpet");

		activityManager.update_activity.apply(activityManager, activity, null, new IDiscordActivityManager.update_activity_callback_callback_callback()
		{
			@Override
			public void apply(Pointer callback_data, int result)
			{
				System.out.println("update activity callback result is " + result);
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				System.out.println("Shutting down Discord core " + core.getPointer());
				core.destroy.apply(core);
			}
		}));

		while (true) {
			Thread.sleep(200);
			core.run_callbacks.apply(core);
		}
	}
}
