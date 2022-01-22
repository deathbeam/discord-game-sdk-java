package io.github.deathbeam.discordgamesdk.sugar;

import io.github.deathbeam.discordgamesdk.DiscordActivity;
import io.github.deathbeam.discordgamesdk.DiscordEntitlement;
import io.github.deathbeam.discordgamesdk.DiscordRelationship;
import io.github.deathbeam.discordgamesdk.DiscordUser;
import io.github.deathbeam.discordgamesdk.DiscordUserAchievement;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscordEvents
{
	// Achievement events

	public void onUserAchievementUpdate(final DiscordUserAchievement achievement) {
		log.debug("Received onUserAchievementUpdate {}", achievement.achievement_id);
	}

	// Activity events

	public void onActivityJoin(final String secret) {
		log.debug("Received onActivityJoin {}", secret);
	}

	public void onActivitySpectate(final String secret) {
		log.debug("Received onActivitySpectate {}", secret);
	}

	public void onActivityJoinRequest(final DiscordUser user) {
		log.debug("Received onActivityJoinRequest {}", user.id);
	}

	public void onActivityInvite(final int type, final DiscordUser user, final DiscordActivity activity) {
		log.debug("Received onActivityInvite {} {}", type, user.id);
	}

	// Lobby events

	public void onLobbyUpdate(final long lobbyId) {
		log.debug("Received onLobbyUpdate {}", lobbyId);
	}

	public void onLobbyDelete(final long lobbyId, final int reason) {
		log.debug("Received onLobbyDelete {} {}", lobbyId, reason);
	}

	public void onLobbyMemberConnect(final long lobbyId, final long userId) {
		log.debug("Received onLobbyMemberConnect {} {}", lobbyId, userId);
	}

	public void onLobbyMemberUpdate(final long lobbyId, final long userId) {
		log.debug("Received onLobbyMemberUpdate {} {}", lobbyId, userId);
	}

	public void onLobbyMemberDisconnect(final long lobbyId, final long userId) {
		log.debug("Received onLobbyMemberDisconnect {} {}", lobbyId, userId);
	}

	public void onLobbyMessage(final long lobbyId, final long userId, final String message) {
		log.debug("Received onLobbyMessage {} {} {}", lobbyId, userId, message);
	}

	public void onLobbySpeaking(final long lobbyId, final long userId, final boolean speaking) {
		log.debug("Received onLobbySpeaking {} {} {}", lobbyId, userId, speaking);
	}

	public void onLobbyNetworkMessage(final long lobbyId, final long userId, final byte channelId, final String message) {
		log.debug("Received onLobbyNetworkMessage {} {} {} {}", lobbyId, userId, channelId, message);
	}

	// Network events

	public void onNetworkMessage(final long peerId, final byte channelId, final String message) {
		log.debug("Received onNetworkMessage {} {} {}", peerId, channelId, message);
	}

	public void onNetworkRouteUpdate(final String routeData) {
		log.debug("Received onNetworkRouteUpdate {}", routeData);
	}

	// Overlay events

	public void onOverlayToggle(final boolean locked) {
		log.debug("Received onOverlayToggle {}", locked);
	}

	// Relationship events

	public void onRelationshipRefresh() {
		log.debug("Received onRelationshipRefresh");
	}

	public void onRelationshipUpdate(final DiscordRelationship relationship) {
		log.debug("Received onRelationshipUpdate {}", relationship);
	}

	// Store events

	public void onStoreEntitlementCreate(final DiscordEntitlement entitlement) {
		log.debug("Received onStoreEntitlementCreate {}", entitlement.id);
	}

	public void onStoreEntitlementDelete(final DiscordEntitlement entitlement) {
		log.debug("Received onStoreEntitlementDelete {}", entitlement.id);
	}

	// User events

	public void onCurrentUserUpdate() {
		log.debug("Received onCurrentUserUpdate");
	}

	// Voice events

	public void onVoiceSettingsUpdate() {
		log.debug("Received onVoiceSettingsUpdate");
	}
}