package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.DiscordResult;
import io.github.deathbeam.discordgamesdk.jna.DiscordUser;
import io.github.deathbeam.discordgamesdk.jna.IDiscordUserManager;
import java.util.concurrent.CompletableFuture;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordUserManagerExtensions
{
	public static DiscordUser getCurrentUser(final IDiscordUserManager userManager)
	{
		final DiscordUser discordUser = new DiscordUser();
		userManager.get_current_user.apply(userManager, discordUser);
		return discordUser;
	}

	public static CompletableFuture<DiscordUser> getUser(final IDiscordUserManager userManager, final long userId)
	{
		final CompletableFuture<DiscordUser> future = new CompletableFuture<>();
		userManager.get_user.apply(userManager, userId, null, (callback_data, result, user) -> DiscordResult.of(result).completeFuture(future, user));
		return future;
	}
}
