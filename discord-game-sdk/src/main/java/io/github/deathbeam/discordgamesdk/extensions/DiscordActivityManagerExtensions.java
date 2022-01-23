package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import java.util.concurrent.CompletableFuture;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordActivityManagerExtensions
{
	public static CompletableFuture<Integer> updateActivity(final IDiscordActivityManager activityManager, final DiscordActivity activity)
	{
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		activityManager.update_activity.apply(activityManager, activity, null, (callback_data, result) -> future.complete(result));
		return future;
	}

	public static CompletableFuture<Integer> clearActivity(final IDiscordActivityManager activityManager)
	{
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		activityManager.clear_activity.apply(activityManager, null, (callback_data, result) -> future.complete(result));
		return future;
	}
}
