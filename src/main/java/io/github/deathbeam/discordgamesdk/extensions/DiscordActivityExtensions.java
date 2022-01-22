package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.DiscordActivity;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.fillStr;

public class DiscordActivityExtensions
{
	public static void setState(final DiscordActivity activity, final String value) {
		fillStr(activity.state, value);
	}

	public static void setDetails(final DiscordActivity activity, final String value) {
		fillStr(activity.details, value);
	}
}
