package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.DiscordResult;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import java.util.concurrent.CompletableFuture;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordActivityManagerExtensions
{
	public static CompletableFuture<Void> updateActivity(final IDiscordActivityManager activityManager, final DiscordActivity activity)
	{
		final CompletableFuture<Void> future = new CompletableFuture<>();
		activityManager.update_activity.apply(activityManager, activity, null, (callback_data, result) -> DiscordResult.of(result).completeFuture(future));
		return future;
	}

	public static CompletableFuture<Void> clearActivity(final IDiscordActivityManager activityManager)
	{
		final CompletableFuture<Void> future = new CompletableFuture<>();
		activityManager.clear_activity.apply(activityManager, null, (callback_data, result) -> DiscordResult.of(result).completeFuture(future));
		return future;
	}

	public static CompletableFuture<Void> sendRequestReply(final IDiscordActivityManager activityManager, final long userId, final int reply)
	{
		final CompletableFuture<Void> future = new CompletableFuture<>();
		activityManager.send_request_reply.apply(activityManager, userId, reply, null, (callback_data, result) -> DiscordResult.of(result).completeFuture(future));
		return future;
	}
}
