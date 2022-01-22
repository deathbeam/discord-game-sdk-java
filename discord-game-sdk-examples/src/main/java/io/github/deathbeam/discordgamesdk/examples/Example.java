package io.github.deathbeam.discordgamesdk.examples;

import io.github.deathbeam.discordgamesdk.Discord;
import io.github.deathbeam.discordgamesdk.DiscordEvents;
import io.github.deathbeam.discordgamesdk.extensions.DiscordActivityExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordActivityManagerExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordCoreExtensions;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordCore;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtensionMethod({DiscordCoreExtensions.class, DiscordActivityExtensions.class, DiscordActivityManagerExtensions.class})
public class Example
{
	public static void main(String[] args)
	{
		final long CLIENT_ID = Long.parseLong(args[0]);

		final ExecutorService executorService = Executors.newCachedThreadPool();
		final IDiscordCore core = Discord.create(CLIENT_ID, new DiscordEvents(), executorService);

		final IDiscordActivityManager activityManager = core.getActivityManager();
		final DiscordActivity activity = new DiscordActivity();
		activity.setState("In Play Mode");
		activity.setDetails("Playing the trumpet");
		activityManager.updateActivity(activity).thenAccept(result -> log.debug("update activity callback result is {}", result));

		while (!executorService.isShutdown())
		{
		}
	}
}
