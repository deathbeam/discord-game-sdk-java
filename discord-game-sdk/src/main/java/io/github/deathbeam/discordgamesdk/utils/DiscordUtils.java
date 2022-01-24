package io.github.deathbeam.discordgamesdk.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordUtils
{
	public static void strToByteArr(byte[] dst, String val)
	{
		Arrays.fill(dst, (byte) 0);
		final byte[] valArr = val.getBytes(StandardCharsets.UTF_8);
		System.arraycopy(valArr, 0, dst, 0, valArr.length);
	}

	public static String byteArrToStr(byte[] src)
	{
		final String str = new String(src, StandardCharsets.UTF_8);
		final String[] split = str.split("\\u0000");
		return split.length > 0 ? split[0] : str;
	}

	public static boolean byteToBool(byte val)
	{
		return val != 0;
	}

	public static byte boolToByte(boolean val)
	{
		return val ? (byte) 1 : (byte) 0;
	}
}
