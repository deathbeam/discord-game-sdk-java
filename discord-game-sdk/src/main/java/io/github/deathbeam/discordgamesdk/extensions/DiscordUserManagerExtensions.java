package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordUser;
import io.github.deathbeam.discordgamesdk.jna.IDiscordUserManager;
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
}
