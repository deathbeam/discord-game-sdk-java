package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.byteArrToStr;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.strToByteArr;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordActivityExtensions
{
	public static void setState(final DiscordActivity activity, final String value)
	{
		strToByteArr(activity.state, value);
	}

	public static String getState(final DiscordActivity activity)
	{
		return byteArrToStr(activity.state);
	}

	public static void setDetails(final DiscordActivity activity, final String value)
	{
		strToByteArr(activity.details, value);
	}

	public static String getDetails(final DiscordActivity activity)
	{
		return byteArrToStr(activity.details);
	}
}
