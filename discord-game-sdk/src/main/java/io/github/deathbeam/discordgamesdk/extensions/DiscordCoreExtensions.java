package io.github.deathbeam.discordgamesdk.extensions;


import io.github.deathbeam.discordgamesdk.jna.IDiscordAchievementManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordApplicationManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordCore;
import io.github.deathbeam.discordgamesdk.jna.IDiscordImageManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordLobbyManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordNetworkManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordOverlayManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordRelationshipManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordStorageManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordStoreManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordUserManager;
import io.github.deathbeam.discordgamesdk.jna.IDiscordVoiceManager;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DiscordCoreExtensions
{
	public static IDiscordApplicationManager getApplicationManager(final IDiscordCore core)
	{
		return core.get_application_manager.apply(core);
	}

	public static IDiscordUserManager getUserManager(final IDiscordCore core)
	{
		return core.get_user_manager.apply(core);
	}

	public static IDiscordImageManager getImageManager(final IDiscordCore core)
	{
		return core.get_image_manager.apply(core);
	}

	public static IDiscordActivityManager getActivityManager(final IDiscordCore core)
	{
		return core.get_activity_manager.apply(core);
	}

	public static IDiscordRelationshipManager getRelationshipManager(final IDiscordCore core)
	{
		return core.get_relationship_manager.apply(core);
	}

	public static IDiscordLobbyManager getLobbyManager(final IDiscordCore core)
	{
		return core.get_lobby_manager.apply(core);
	}

	public static IDiscordNetworkManager getNetworkManager(final IDiscordCore core)
	{
		return core.get_network_manager.apply(core);
	}

	public static IDiscordOverlayManager getOverlayManager(final IDiscordCore core)
	{
		return core.get_overlay_manager.apply(core);
	}

	public static IDiscordStorageManager getStorageManager(final IDiscordCore core)
	{
		return core.get_storage_manager.apply(core);
	}

	public static IDiscordStoreManager getStoreManager(final IDiscordCore core)
	{
		return core.get_store_manager.apply(core);
	}

	public static IDiscordVoiceManager getVoiceManager(final IDiscordCore core)
	{
		return core.get_voice_manager.apply(core);
	}

	public static IDiscordAchievementManager getAchievementManager(final IDiscordCore core)
	{
		return core.get_achievement_manager.apply(core);
	}
}
