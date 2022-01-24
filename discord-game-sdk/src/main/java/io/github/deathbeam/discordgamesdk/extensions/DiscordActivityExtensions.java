package io.github.deathbeam.discordgamesdk.extensions;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivityAssets;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivityParty;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivitySecrets;
import io.github.deathbeam.discordgamesdk.jna.DiscordActivityTimestamps;
import io.github.deathbeam.discordgamesdk.jna.DiscordPartySize;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.boolToByte;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.byteArrToStr;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.byteToBool;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.strToByteArr;
import java.time.Instant;
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

	public static void setStartTimestamp(final DiscordActivity activity, final Instant timestamp)
	{
		if (activity.timestamps == null)
		{
			activity.timestamps = new DiscordActivityTimestamps();
		}

		activity.timestamps.start = timestamp != null ? timestamp.getEpochSecond() : 0;
	}

	public static Instant getStartTimestamp(final DiscordActivity activity)
	{
		if (activity.timestamps == null)
		{
			return null;
		}

		return activity.timestamps.start > 0 ? Instant.ofEpochSecond(activity.timestamps.start) : null;
	}

	public static void setEndTimestamp(final DiscordActivity activity, final Instant timestamp)
	{
		if (activity.timestamps == null)
		{
			activity.timestamps = new DiscordActivityTimestamps();
		}

		activity.timestamps.end = timestamp != null ? timestamp.getEpochSecond() : 0;
	}

	public static Instant getEndTimestamp(final DiscordActivity activity)
	{
		if (activity.timestamps == null)
		{
			return null;
		}

		return activity.timestamps.end > 0 ? Instant.ofEpochSecond(activity.timestamps.end) : null;
	}

	public static void setLargeImage(final DiscordActivity activity, final String value)
	{
		if (activity.assets == null)
		{
			activity.assets = new DiscordActivityAssets();
		}

		strToByteArr(activity.assets.large_image, value);
	}

	public static String getLargeImage(final DiscordActivity activity)
	{
		if (activity.assets == null)
		{
			return "";
		}

		return byteArrToStr(activity.assets.large_image);
	}

	public static void setLargeText(final DiscordActivity activity, final String value)
	{
		if (activity.assets == null)
		{
			activity.assets = new DiscordActivityAssets();
		}

		strToByteArr(activity.assets.large_text, value);
	}

	public static String getLargeText(final DiscordActivity activity)
	{
		if (activity.assets == null)
		{
			return "";
		}

		return byteArrToStr(activity.assets.large_text);
	}

	public static void setSmallImage(final DiscordActivity activity, final String value)
	{
		if (activity.assets == null)
		{
			activity.assets = new DiscordActivityAssets();
		}

		strToByteArr(activity.assets.small_image, value);
	}

	public static String getSmallImage(final DiscordActivity activity)
	{
		if (activity.assets == null)
		{
			return "";
		}

		return byteArrToStr(activity.assets.small_image);
	}

	public static void setSmallText(final DiscordActivity activity, final String value)
	{
		if (activity.assets == null)
		{
			activity.assets = new DiscordActivityAssets();
		}

		strToByteArr(activity.assets.small_text, value);
	}

	public static String getSmallText(final DiscordActivity activity)
	{
		if (activity.assets == null)
		{
			return "";
		}

		return byteArrToStr(activity.assets.small_text);
	}

	public static void setPartyId(final DiscordActivity activity, final String value)
	{
		if (activity.party == null)
		{
			activity.party = new DiscordActivityParty();
		}

		strToByteArr(activity.party.id, value);
	}

	public static String getPartyId(final DiscordActivity activity)
	{
		if (activity.party == null)
		{
			return "";
		}

		return byteArrToStr(activity.party.id);
	}

	public static void setPartySize(final DiscordActivity activity, final int value)
	{
		if (activity.party == null)
		{
			activity.party = new DiscordActivityParty();
		}

		if (activity.party.size == null)
		{
			activity.party.size = new DiscordPartySize();
		}

		activity.party.size.current_size = value;
	}

	public static int getPartySize(final DiscordActivity activity)
	{
		if (activity.party == null)
		{
			return 0;
		}

		if (activity.party.size == null)
		{
			return 0;
		}

		return activity.party.size.current_size;
	}

	public static void setPartyMax(final DiscordActivity activity, final int value)
	{
		if (activity.party == null)
		{
			activity.party = new DiscordActivityParty();
		}

		if (activity.party.size == null)
		{
			activity.party.size = new DiscordPartySize();
		}

		activity.party.size.max_size = value;
	}

	public static int getPartyMax(final DiscordActivity activity)
	{
		if (activity.party == null)
		{
			return 0;
		}

		if (activity.party.size == null)
		{
			return 0;
		}

		return activity.party.size.max_size;
	}

	public static void setInstance(final DiscordActivity activity, final boolean value)
	{
		activity.instance = boolToByte(value);
	}

	public static boolean getInstance(final DiscordActivity activity)
	{
		return byteToBool(activity.instance);
	}

	public static void setMatchSecret(final DiscordActivity activity, final String value)
	{
		if (activity.secrets == null)
		{
			activity.secrets = new DiscordActivitySecrets();
		}

		strToByteArr(activity.secrets.match, value);
	}

	public static String getMatchSecret(final DiscordActivity activity)
	{
		if (activity.secrets == null)
		{
			return "";
		}

		return byteArrToStr(activity.secrets.match);
	}

	public static void setJoinSecret(final DiscordActivity activity, final String value)
	{
		if (activity.secrets == null)
		{
			activity.secrets = new DiscordActivitySecrets();
		}

		strToByteArr(activity.secrets.join, value);
	}

	public static String getJoinSecret(final DiscordActivity activity)
	{
		if (activity.secrets == null)
		{
			return "";
		}

		return byteArrToStr(activity.secrets.join);
	}

	public static void setSpectateSecret(final DiscordActivity activity, final String value)
	{
		if (activity.secrets == null)
		{
			activity.secrets = new DiscordActivitySecrets();
		}

		strToByteArr(activity.secrets.spectate, value);
	}

	public static String getSpectateSecret(final DiscordActivity activity)
	{
		if (activity.secrets == null)
		{
			return "";
		}

		return byteArrToStr(activity.secrets.spectate);
	}
}
