package io.github.deathbeam.discordgamesdk;

import io.github.deathbeam.discordgamesdk.jna.DiscordActivity;
import io.github.deathbeam.discordgamesdk.jna.DiscordEntitlement;
import io.github.deathbeam.discordgamesdk.jna.DiscordRelationship;
import io.github.deathbeam.discordgamesdk.jna.DiscordUser;
import io.github.deathbeam.discordgamesdk.jna.DiscordUserAchievement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscordEvents
{
	// Achievement events

	public void onUserAchievementUpdate(final DiscordUserAchievement achievement)
	{
		log.trace("Received onUserAchievementUpdate {}", achievement);
	}

	// Activity events

	public void onActivityJoin(final String secret)
	{
		log.trace("Received onActivityJoin {}", secret);
	}

	public void onActivitySpectate(final String secret)
	{
		log.trace("Received onActivitySpectate {}", secret);
	}

	public void onActivityJoinRequest(final DiscordUser user)
	{
		log.trace("Received onActivityJoinRequest {}", user);
	}

	public void onActivityInvite(final int type, final DiscordUser user, final DiscordActivity activity)
	{
		log.trace("Received onActivityInvite {} {} {}", type, user, activity);
	}

	// Lobby events

	public void onLobbyUpdate(final long lobbyId)
	{
		log.trace("Received onLobbyUpdate {}", lobbyId);
	}

	public void onLobbyDelete(final long lobbyId, final int reason)
	{
		log.trace("Received onLobbyDelete {} {}", lobbyId, reason);
	}

	public void onLobbyMemberConnect(final long lobbyId, final long userId)
	{
		log.trace("Received onLobbyMemberConnect {} {}", lobbyId, userId);
	}

	public void onLobbyMemberUpdate(final long lobbyId, final long userId)
	{
		log.trace("Received onLobbyMemberUpdate {} {}", lobbyId, userId);
	}

	public void onLobbyMemberDisconnect(final long lobbyId, final long userId)
	{
		log.trace("Received onLobbyMemberDisconnect {} {}", lobbyId, userId);
	}

	public void onLobbyMessage(final long lobbyId, final long userId, final String message)
	{
		log.trace("Received onLobbyMessage {} {} {}", lobbyId, userId, message);
	}

	public void onLobbySpeaking(final long lobbyId, final long userId, final boolean speaking)
	{
		log.trace("Received onLobbySpeaking {} {} {}", lobbyId, userId, speaking);
	}

	public void onLobbyNetworkMessage(final long lobbyId, final long userId, final byte channelId, final String message)
	{
		log.trace("Received onLobbyNetworkMessage {} {} {} {}", lobbyId, userId, channelId, message);
	}

	// Network events

	public void onNetworkMessage(final long peerId, final byte channelId, final String message)
	{
		log.trace("Received onNetworkMessage {} {} {}", peerId, channelId, message);
	}

	public void onNetworkRouteUpdate(final String routeData)
	{
		log.trace("Received onNetworkRouteUpdate {}", routeData);
	}

	// Overlay events

	public void onOverlayToggle(final boolean locked)
	{
		log.trace("Received onOverlayToggle {}", locked);
	}

	// Relationship events

	public void onRelationshipRefresh()
	{
		log.trace("Received onRelationshipRefresh");
	}

	public void onRelationshipUpdate(final DiscordRelationship relationship)
	{
		log.trace("Received onRelationshipUpdate {}", relationship);
	}

	// Store events

	public void onStoreEntitlementCreate(final DiscordEntitlement entitlement)
	{
		log.trace("Received onStoreEntitlementCreate {}", entitlement);
	}

	public void onStoreEntitlementDelete(final DiscordEntitlement entitlement)
	{
		log.trace("Received onStoreEntitlementDelete {}", entitlement);
	}

	// User events

	public void onCurrentUserUpdate(final DiscordUser user)
	{
		log.trace("Received onCurrentUserUpdate {}", user);
	}

	// Voice events

	public void onVoiceSettingsUpdate()
	{
		log.trace("Received onVoiceSettingsUpdate");
	}
}
