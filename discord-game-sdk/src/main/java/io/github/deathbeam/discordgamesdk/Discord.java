package io.github.deathbeam.discordgamesdk;

import io.github.deathbeam.discordgamesdk.extensions.DiscordCoreExtensions;
import io.github.deathbeam.discordgamesdk.extensions.DiscordUserManagerExtensions;
import io.github.deathbeam.discordgamesdk.jna.DiscordCreateParams;
import io.github.deathbeam.discordgamesdk.jna.DiscordGameSDK;
import static io.github.deathbeam.discordgamesdk.jna.DiscordGameSDK.*;
import io.github.deathbeam.discordgamesdk.jna.IDiscordAchievementEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordActivityEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordCore;
import io.github.deathbeam.discordgamesdk.jna.IDiscordLobbyEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordNetworkEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordOverlayEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordRelationshipEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordStoreEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordUserEvents;
import io.github.deathbeam.discordgamesdk.jna.IDiscordVoiceEvents;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.byteToBool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtensionMethod({
	DiscordCoreExtensions.class,
	DiscordUserManagerExtensions.class
})
public class Discord
{
	// Prevent garbage collection
	private final DiscordCreateParams params;
	private final ScheduledExecutorService executorService;
	private DiscordEvents eventHandler = new DiscordEvents();
	private IDiscordCore core;

	public Discord(final long applicationId, final ScheduledExecutorService executorService)
	{
		final DiscordCreateParams params = new DiscordCreateParams();
		params.client_id = applicationId;
		params.application_version = DISCORD_APPLICATION_MANAGER_VERSION;
		params.user_version = DISCORD_USER_MANAGER_VERSION;
		params.image_version = DISCORD_IMAGE_MANAGER_VERSION;
		params.activity_version = DISCORD_ACTIVITY_MANAGER_VERSION;
		params.relationship_version = DISCORD_RELATIONSHIP_MANAGER_VERSION;
		params.lobby_version = DISCORD_LOBBY_MANAGER_VERSION;
		params.network_version = DISCORD_NETWORK_MANAGER_VERSION;
		params.overlay_version = DISCORD_OVERLAY_MANAGER_VERSION;
		params.storage_version = DISCORD_STORAGE_MANAGER_VERSION;
		params.store_version = DISCORD_STORE_MANAGER_VERSION;
		params.voice_version = DISCORD_VOICE_MANAGER_VERSION;
		params.achievement_version = DISCORD_ACHIEVEMENT_MANAGER_VERSION;
		setupEventHandlers(params);

		this.params = params;
		this.executorService = executorService;
	}

	private void setupEventHandlers(final DiscordCreateParams params)
	{
		final IDiscordAchievementEvents.ByReference achievementEvents = new IDiscordAchievementEvents.ByReference();
		achievementEvents.on_user_achievement_update = (event_data, user_achievement) -> eventHandler.onUserAchievementUpdate(user_achievement);
		params.achievement_events = achievementEvents;

		final IDiscordActivityEvents.ByReference activityEvents = new IDiscordActivityEvents.ByReference();
		activityEvents.on_activity_join = (event_data, secret) -> eventHandler.onActivityJoin(secret.getString(0));
		activityEvents.on_activity_spectate = (event_data, secret) -> eventHandler.onActivitySpectate(secret.getString(0));
		activityEvents.on_activity_join_request = (event_data, user) -> eventHandler.onActivityJoinRequest(user);
		activityEvents.on_activity_invite = (event_data, type, user, activity) -> eventHandler.onActivityInvite(type, user, activity);
		params.activity_events = activityEvents;

		final IDiscordLobbyEvents.ByReference lobbyEvents = new IDiscordLobbyEvents.ByReference();
		lobbyEvents.on_lobby_update = (event_data, lobby_id) -> eventHandler.onLobbyUpdate(lobby_id);
		lobbyEvents.on_lobby_delete = (event_data, lobby_id, reason) -> eventHandler.onLobbyDelete(lobby_id, reason);
		lobbyEvents.on_member_connect = (event_data, lobby_id, user_id) -> eventHandler.onLobbyMemberConnect(lobby_id, user_id);
		lobbyEvents.on_member_update = (event_data, lobby_id, user_id) -> eventHandler.onLobbyMemberUpdate(lobby_id, user_id);
		lobbyEvents.on_member_disconnect = (event_data, lobby_id, user_id) -> eventHandler.onLobbyMemberDisconnect(lobby_id, user_id);
		lobbyEvents.on_lobby_message = (event_data, lobby_id, user_id, data, data_length) -> eventHandler.onLobbyMessage(lobby_id, user_id, data.getString(0));
		lobbyEvents.on_speaking = (event_data, lobby_id, user_id, speaking) -> eventHandler.onLobbySpeaking(lobby_id, user_id, byteToBool(speaking));
		lobbyEvents.on_network_message = (event_data, lobby_id, user_id, channel_id, data, data_length) -> eventHandler.onLobbyNetworkMessage(lobby_id, user_id, channel_id, data.getString(0));
		params.lobby_events = lobbyEvents;

		final IDiscordNetworkEvents.ByReference networkEvents = new IDiscordNetworkEvents.ByReference();
		networkEvents.on_message = (event_data, peer_id, channel_id, data, data_length) -> eventHandler.onNetworkMessage(peer_id, channel_id, data.getString(0));
		networkEvents.on_route_update = (event_data, route_data) -> eventHandler.onNetworkRouteUpdate(route_data.getString(0));
		params.network_events = networkEvents;

		final IDiscordOverlayEvents.ByReference overlayEvents = new IDiscordOverlayEvents.ByReference();
		overlayEvents.on_toggle = (event_data, locked) -> eventHandler.onOverlayToggle(byteToBool(locked));
		params.overlay_events = overlayEvents;

		final IDiscordRelationshipEvents.ByReference relationshipEvents = new IDiscordRelationshipEvents.ByReference();
		relationshipEvents.on_refresh = event_data -> eventHandler.onRelationshipRefresh();
		relationshipEvents.on_relationship_update = (event_data, relationship) -> eventHandler.onRelationshipUpdate(relationship);
		params.relationship_events = relationshipEvents;

		final IDiscordStoreEvents.ByReference storeEvents = new IDiscordStoreEvents.ByReference();
		storeEvents.on_entitlement_create = (event_data, entitlement) -> eventHandler.onStoreEntitlementCreate(entitlement);
		storeEvents.on_entitlement_delete = (event_data, entitlement) -> eventHandler.onStoreEntitlementDelete(entitlement);
		params.store_events = storeEvents;

		final IDiscordUserEvents.ByReference userEvents = new IDiscordUserEvents.ByReference();
		userEvents.on_current_user_update = event_data -> eventHandler.onCurrentUserUpdate(core.getUserManager().getCurrentUser());
		params.user_events = userEvents;

		final IDiscordVoiceEvents.ByReference voiceEvents = new IDiscordVoiceEvents.ByReference();
		voiceEvents.on_settings_update = event_data -> eventHandler.onVoiceSettingsUpdate();
		params.voice_events = voiceEvents;
	}

	public IDiscordCore init()
	{
		if (core != null)
		{
			throw new RuntimeException("init() was already called");
		}

		final DiscordGameSDK discordGameSDK;

		try
		{
			discordGameSDK = INSTANCE;
		}
		catch (Error e)
		{
			log.warn("Failed to load Discord library, Discord support will be disabled.");
			return null;
		}

		final IDiscordCore.ByReference[] ptr = (IDiscordCore.ByReference[]) new IDiscordCore.ByReference().toArray(1);
		final int result = discordGameSDK.DiscordCreate(DISCORD_VERSION, params, ptr);

		if (result != EDiscordResult.DiscordResult_Ok)
		{
			throw new RuntimeException(String.format("Discord result is not OK(0): %s", result));
		}

		final IDiscordCore core = ptr[0];
		log.debug("Created Discord core instance {}", core.getPointer());
		this.core = core;

		// Setup logging
		core.set_log_hook.apply(core, EDiscordLogLevel.DiscordLogLevel_Debug, null, (hook_data, level, message) -> {
			final String messageStr = message.getString(0);
			switch (level)
			{
				case EDiscordLogLevel.DiscordLogLevel_Debug:
					log.debug(messageStr);
					break;
				case EDiscordLogLevel.DiscordLogLevel_Info:
					log.info(messageStr);
					break;
				case EDiscordLogLevel.DiscordLogLevel_Warn:
					log.warn(messageStr);
					break;
				case EDiscordLogLevel.DiscordLogLevel_Error:
					log.error(messageStr);
					break;
			}
		});

		// Initialize managers
		core.get_activity_manager.apply(core);
		core.get_achievement_manager.apply(core);
		core.get_application_manager.apply(core);
		core.get_image_manager.apply(core);
		core.get_lobby_manager.apply(core);
		core.get_network_manager.apply(core);
		core.get_overlay_manager.apply(core);
		core.get_storage_manager.apply(core);
		core.get_store_manager.apply(core);
		core.get_user_manager.apply(core);
		core.get_voice_manager.apply(core);
		core.get_relationship_manager.apply(core);

		executorService.scheduleAtFixedRate(() -> core.run_callbacks.apply(core), 200, 200, TimeUnit.MILLISECONDS);

		// Add shutdown hook to properly destroy the service
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			log.debug("Closing Discord core instance {}", core.getPointer());
			core.destroy.apply(core);
		}));

		return core;
	}

	public void setEventHandler(final DiscordEvents eventHandler)
	{
		this.eventHandler = eventHandler;
	}
}
