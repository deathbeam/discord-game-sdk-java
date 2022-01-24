package io.github.deathbeam.discordgamesdk.examples;

import io.github.deathbeam.discordgamesdk.Discord;
import io.github.deathbeam.discordgamesdk.DiscordEvents;
import io.github.deathbeam.discordgamesdk.extensions.DiscordActivityExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordActivityManagerExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordCoreExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordUserExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordUserManagerExtensions;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.DiscordUser;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordCore;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtensionMethod({
	DiscordCoreExtensions.class,
	DiscordActivityExtensions.class,
	DiscordActivityManagerExtensions.class,
	DiscordUserExtensions.class,
	DiscordUserManagerExtensions.class
})
public class Example
{
	public static void main(String[] args)
	{
		final long APPLICATION_ID = Long.parseLong(args[0]);

		final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		final Discord discord = new Discord(APPLICATION_ID, executorService);

		discord.setEventHandler(new DiscordEvents()
		{
			@Override
			public void onCurrentUserUpdate(DiscordUser user)
			{
				super.onCurrentUserUpdate(user);
				log.info("Current user is {}", user.getDisplayName());
			}
		});

		final IDiscordCore core = discord.init();
		final IDiscordActivityManager activityManager = core.getActivityManager();

		final DiscordActivity activity = new DiscordActivity();
		activity.setState("In Play Mode");
		activity.setDetails("Playing the trumpet");
		activity.setStartTimestamp(Instant.now());
		activityManager.updateActivity(activity).thenAccept(result -> log.debug("update activity callback result is {}", result));

		while (!executorService.isShutdown())
		{
		}
	}
}
