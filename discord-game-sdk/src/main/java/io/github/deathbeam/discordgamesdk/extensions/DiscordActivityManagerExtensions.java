package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import java.util.concurrent.CompletableFuture;

public class DiscordActivityManagerExtensions
{
	public static CompletableFuture<Integer> updateActivity(final IDiscordActivityManager activityManager, final DiscordActivity activity)
	{
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		activityManager.update_activity.apply(activityManager, activity, null, (callback_data, result) -> future.complete(result));
		return future;
	}
}
