package io.github.deathbeam.discordgamesdk.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordUtils
{
	public static void fillStr(byte[] dst, String val)
	{
		Arrays.fill(dst, (byte)0);
		final byte[] valArr = val.getBytes(StandardCharsets.UTF_8);
		System.arraycopy(valArr, 0, dst, 0, valArr.length);
	}

	public static boolean toBoolean(byte val)
	{
		return val == 1;
	}
}
