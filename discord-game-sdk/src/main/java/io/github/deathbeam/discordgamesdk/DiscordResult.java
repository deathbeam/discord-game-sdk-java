package io.github.deathbeam.discordgamesdk;

import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiscordResult
{
	DiscordResult_Ok(0),
	DiscordResult_ServiceUnavailable(1),
	DiscordResult_InvalidVersion(2),
	DiscordResult_LockFailed(3),
	DiscordResult_InternalError(4),
	DiscordResult_InvalidPayload(5),
	DiscordResult_InvalidCommand(6),
	DiscordResult_InvalidPermissions(7),
	DiscordResult_NotFetched(8),
	DiscordResult_NotFound(9),
	DiscordResult_Conflict(10),
	DiscordResult_InvalidSecret(11),
	DiscordResult_InvalidJoinSecret(12),
	DiscordResult_NoEligibleActivity(13),
	DiscordResult_InvalidInvite(14),
	DiscordResult_NotAuthenticated(15),
	DiscordResult_InvalidAccessToken(16),
	DiscordResult_ApplicationMismatch(17),
	DiscordResult_InvalidDataUrl(18),
	DiscordResult_InvalidBase64(19),
	DiscordResult_NotFiltered(20),
	DiscordResult_LobbyFull(21),
	DiscordResult_InvalidLobbySecret(22),
	DiscordResult_InvalidFilename(23),
	DiscordResult_InvalidFileSize(24),
	DiscordResult_InvalidEntitlement(25),
	DiscordResult_NotInstalled(26),
	DiscordResult_NotRunning(27),
	DiscordResult_InsufficientBuffer(28),
	DiscordResult_PurchaseCanceled(29),
	DiscordResult_InvalidGuild(30),
	DiscordResult_InvalidEvent(31),
	DiscordResult_InvalidChannel(32),
	DiscordResult_InvalidOrigin(33),
	DiscordResult_RateLimited(34),
	DiscordResult_OAuth2Error(35),
	DiscordResult_SelectChannelTimeout(36),
	DiscordResult_GetGuildTimeout(37),
	DiscordResult_SelectVoiceForceRequired(38),
	DiscordResult_CaptureShortcutAlreadyListening(39),
	DiscordResult_UnauthorizedForAchievement(40),
	DiscordResult_InvalidGiftCode(41),
	DiscordResult_PurchaseError(42),
	DiscordResult_TransactionAborted(43),

	DiscordResult_Unknown(-1);

	private final int id;

	public boolean isOk()
	{
		return this == DiscordResult_Ok;
	}

	public <T> void completeFuture(final CompletableFuture<T> future, final T result)
	{
		if (isOk())
		{
			future.complete(result);
		}
		else
		{
			future.completeExceptionally(new DiscordException(this));
		}
	}

	public <T> void completeFuture(final CompletableFuture<T> future)
	{
		completeFuture(future, null);
	}

	public static DiscordResult of(int id)
	{
		for (final DiscordResult value : DiscordResult.values())
		{
			if (value.id == id)
			{
				return value;
			}
		}

		return DiscordResult.DiscordResult_Unknown;
	}
}
