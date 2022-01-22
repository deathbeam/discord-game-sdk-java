package io.github.deathbeam.discordgamesdk.examples;

import com.sun.jna.Pointer;
import io.github.deathbeam.discordgamesdk.DiscordActivity;
import io.github.deathbeam.discordgamesdk.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.IDiscordCore;
import io.github.deathbeam.discordgamesdk.extensions.DiscordActivityExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordCoreExtensions;
import io.github.deathbeam.discordgamesdk.sugar.Discord;
import io.github.deathbeam.discordgamesdk.sugar.DiscordEvents;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtensionMethod({DiscordCoreExtensions.class, DiscordActivityExtensions.class})
public class Example
{
	public static void main(String[] args)
	{
		long CLIENT_ID = Long.parseLong(args[0]);

		final ExecutorService executorService = Executors.newCachedThreadPool();
		final IDiscordCore core = Discord.create(CLIENT_ID, new DiscordEvents(), executorService);

		final IDiscordActivityManager activityManager = core.getActivityManager();
		final DiscordActivity activity = new DiscordActivity();
		activity.setState("In Play Mode");
		activity.setDetails("Playing the trumpet");

		activityManager.update_activity.apply(activityManager, activity, null, new IDiscordActivityManager.update_activity_callback_callback_callback()
		{
			@Override
			public void apply(Pointer callback_data, int result)
			{
				log.debug("update activity callback result is {}", result);
			}
		});

		while (!executorService.isShutdown())
		{
		}
	}
}
