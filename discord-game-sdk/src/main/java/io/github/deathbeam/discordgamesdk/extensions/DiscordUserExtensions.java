package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordUser;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.byteArrToStr;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordUserExtensions
{
	public static long getId(final DiscordUser discordUser)
	{
		return discordUser.id;
	}

	public static String getUsername(final DiscordUser discordUser)
	{
		return byteArrToStr(discordUser.username);
	}

	public static String getDiscriminator(final DiscordUser discordUser)
	{
		return byteArrToStr(discordUser.discriminator);
	}

	public static String getDisplayName(final DiscordUser discordUser)
	{
		return getUsername(discordUser) + "#" + getDiscriminator(discordUser);
	}
}
