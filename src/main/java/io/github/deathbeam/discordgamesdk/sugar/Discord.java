package io.github.deathbeam.discordgamesdk.sugar;

import com.sun.jna.Pointer;
import io.github.deathbeam.discordgamesdk.DiscordActivity;
import io.github.deathbeam.discordgamesdk.DiscordCreateParams;
import io.github.deathbeam.discordgamesdk.DiscordEntitlement;
import io.github.deathbeam.discordgamesdk.DiscordGameSDK;
import static io.github.deathbeam.discordgamesdk.DiscordGameSDK.*;
import io.github.deathbeam.discordgamesdk.DiscordRelationship;
import io.github.deathbeam.discordgamesdk.DiscordUser;
import io.github.deathbeam.discordgamesdk.DiscordUserAchievement;
import io.github.deathbeam.discordgamesdk.IDiscordAchievementEvents;
import io.github.deathbeam.discordgamesdk.IDiscordActivityEvents;
import io.github.deathbeam.discordgamesdk.IDiscordCore;
import io.github.deathbeam.discordgamesdk.IDiscordLobbyEvents;
import io.github.deathbeam.discordgamesdk.IDiscordNetworkEvents;
import io.github.deathbeam.discordgamesdk.IDiscordOverlayEvents;
import io.github.deathbeam.discordgamesdk.IDiscordRelationshipEvents;
import io.github.deathbeam.discordgamesdk.IDiscordStoreEvents;
import io.github.deathbeam.discordgamesdk.IDiscordUserEvents;
import io.github.deathbeam.discordgamesdk.IDiscordVoiceEvents;
import static io.github.deathbeam.discordgamesdk.utils.DiscordUtils.toBoolean;
import java.util.concurrent.ExecutorService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Discord
{
	public static IDiscordCore create(final long clientId, final DiscordEvents events, final ExecutorService executorService)
	{
		final DiscordGameSDK discord = INSTANCE;
		DiscordCreateParams params = new DiscordCreateParams();
		params.client_id = clientId;
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

		IDiscordAchievementEvents.ByReference achievementEvents = new IDiscordAchievementEvents.ByReference();
		achievementEvents.on_user_achievement_update = new IDiscordAchievementEvents.on_user_achievement_update_callback()
		{
			@Override
			public void apply(Pointer event_data, DiscordUserAchievement user_achievement)
			{
				events.onUserAchievementUpdate(user_achievement);
			}
		};
		params.achievement_events = achievementEvents;

		IDiscordActivityEvents.ByReference activityEvents = new IDiscordActivityEvents.ByReference();
		activityEvents.on_activity_join = new IDiscordActivityEvents.on_activity_join_callback()
		{
			@Override
			public void apply(Pointer event_data, Pointer secret)
			{
				events.onActivityJoin(secret.getString(0));
			}
		};
		activityEvents.on_activity_spectate = new IDiscordActivityEvents.on_activity_spectate_callback()
		{
			@Override
			public void apply(Pointer event_data, Pointer secret)
			{
				events.onActivitySpectate(secret.getString(0));
			}
		};
		activityEvents.on_activity_join_request = new IDiscordActivityEvents.on_activity_join_request_callback()
		{
			@Override
			public void apply(Pointer event_data, DiscordUser user)
			{
				events.onActivityJoinRequest(user);
			}
		};
		activityEvents.on_activity_invite = new IDiscordActivityEvents.on_activity_invite_callback()
		{
			@Override
			public void apply(Pointer event_data, int type, DiscordUser user, DiscordActivity activity)
			{
				events.onActivityInvite(type, user, activity);
			}
		};
		params.activity_events = activityEvents;

		IDiscordLobbyEvents.ByReference lobbyEvents = new IDiscordLobbyEvents.ByReference();
		lobbyEvents.on_lobby_update = new IDiscordLobbyEvents.on_lobby_update_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id)
			{
				events.onLobbyUpdate(lobby_id);
			}
		};
		lobbyEvents.on_lobby_delete = new IDiscordLobbyEvents.on_lobby_delete_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, int reason)
			{
				events.onLobbyDelete(lobby_id, reason);
			}
		};
		lobbyEvents.on_member_connect = new IDiscordLobbyEvents.on_member_connect_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id)
			{
				events.onLobbyMemberConnect(lobby_id, user_id);
			}
		};
		lobbyEvents.on_member_update = new IDiscordLobbyEvents.on_member_update_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id)
			{
				events.onLobbyMemberUpdate(lobby_id, user_id);
			}
		};
		lobbyEvents.on_member_disconnect = new IDiscordLobbyEvents.on_member_disconnect_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id)
			{
				events.onLobbyMemberDisconnect(lobby_id, user_id);
			}
		};
		lobbyEvents.on_lobby_message = new IDiscordLobbyEvents.on_lobby_message_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id, Pointer data, int data_length)
			{
				events.onLobbyMessage(lobby_id, user_id, data.getString(0));
			}
		};
		lobbyEvents.on_speaking = new IDiscordLobbyEvents.on_speaking_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id, byte speaking)
			{
				events.onLobbySpeaking(lobby_id, user_id, toBoolean(speaking));
			}
		};
		lobbyEvents.on_network_message = new IDiscordLobbyEvents.on_network_message_callback()
		{
			@Override
			public void apply(Pointer event_data, long lobby_id, long user_id, byte channel_id, Pointer data, int data_length)
			{
				events.onLobbyNetworkMessage(lobby_id, user_id, channel_id, data.getString(0));
			}
		};
		params.lobby_events = lobbyEvents;

		IDiscordNetworkEvents.ByReference networkEvents = new IDiscordNetworkEvents.ByReference();
		networkEvents.on_message = new IDiscordNetworkEvents.on_message_callback()
		{
			@Override
			public void apply(Pointer event_data, long peer_id, byte channel_id, Pointer data, int data_length)
			{
				events.onNetworkMessage(peer_id, channel_id, data.getString(0));
			}
		};
		networkEvents.on_route_update = new IDiscordNetworkEvents.on_route_update_callback()
		{
			@Override
			public void apply(Pointer event_data, Pointer route_data)
			{
				events.onNetworkRouteUpdate(route_data.getString(0));
			}
		};
		params.network_events = networkEvents;

		IDiscordOverlayEvents.ByReference overlayEvents = new IDiscordOverlayEvents.ByReference();
		overlayEvents.on_toggle = new IDiscordOverlayEvents.on_toggle_callback()
		{
			@Override
			public void apply(Pointer event_data, byte locked)
			{
				events.onOverlayToggle(toBoolean(locked));
			}
		};
		params.overlay_events = overlayEvents;

		IDiscordRelationshipEvents.ByReference relationshipEvents = new IDiscordRelationshipEvents.ByReference();
		relationshipEvents.on_refresh = new IDiscordRelationshipEvents.on_refresh_callback()
		{
			@Override
			public void apply(Pointer event_data)
			{
				events.onRelationshipRefresh();
			}
		};
		relationshipEvents.on_relationship_update = new IDiscordRelationshipEvents.on_relationship_update_callback()
		{
			@Override
			public void apply(Pointer event_data, DiscordRelationship relationship)
			{
				events.onRelationshipUpdate(relationship);
			}
		};
		params.relationship_events = relationshipEvents;

		IDiscordStoreEvents.ByReference storeEvents = new IDiscordStoreEvents.ByReference();
		storeEvents.on_entitlement_create = new IDiscordStoreEvents.on_entitlement_create_callback()
		{
			@Override
			public void apply(Pointer event_data, DiscordEntitlement entitlement)
			{
				events.onStoreEntitlementCreate(entitlement);
			}
		};
		storeEvents.on_entitlement_delete = new IDiscordStoreEvents.on_entitlement_delete_callback()
		{
			@Override
			public void apply(Pointer event_data, DiscordEntitlement entitlement)
			{
				events.onStoreEntitlementDelete(entitlement);
			}
		};
		params.store_events = storeEvents;

		IDiscordUserEvents.ByReference userEvents = new IDiscordUserEvents.ByReference();
		userEvents.on_current_user_update = new IDiscordUserEvents.on_current_user_update_callback()
		{
			@Override
			public void apply(Pointer event_data)
			{
				events.onCurrentUserUpdate();
			}
		};
		params.user_events = userEvents;

		IDiscordVoiceEvents.ByReference voiceEvents = new IDiscordVoiceEvents.ByReference();
		voiceEvents.on_settings_update = new IDiscordVoiceEvents.on_settings_update_callback()
		{
			@Override
			public void apply(Pointer event_data)
			{
				events.onVoiceSettingsUpdate();
			}
		};
		params.voice_events = voiceEvents;

		IDiscordCore.ByReference coreReference = new IDiscordCore.ByReference();
		IDiscordCore.ByReference[] coreReferences = (IDiscordCore.ByReference[]) coreReference.toArray(1);
		int result = discord.DiscordCreate(DiscordGameSDK.DISCORD_VERSION, params, coreReferences);

		if (result != EDiscordResult.DiscordResult_Ok)
		{
			throw new RuntimeException(String.format("Discord result is not OK(0): %s", result));
		}

		final IDiscordCore core = coreReferences[0];
		core.set_log_hook.apply(core, EDiscordLogLevel.DiscordLogLevel_Debug, null, new IDiscordCore.set_log_hook_callback_hook_callback()
		{
			@Override
			public void apply(Pointer hook_data, int level, Pointer message)
			{
				String messageStr = message.getString(0);
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
			}
		});

		executorService.submit(new Runnable()
		{
			@SneakyThrows
			@Override
			public void run()
			{
				while (true)
				{
					Thread.sleep(200);
					core.run_callbacks.apply(core);
				}
			}
		});

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				core.destroy.apply(core);
			}
		}));

		return core;
	}
}
