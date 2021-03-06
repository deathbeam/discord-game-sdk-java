package io.github.deathbeam.discordgamesdk.jna;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class IDiscordOverlayManager extends Structure {
	/** C type : is_enabled_callback* */
	public IDiscordOverlayManager.is_enabled_callback is_enabled;
	/** C type : is_locked_callback* */
	public IDiscordOverlayManager.is_locked_callback is_locked;
	/** C type : set_locked_callback* */
	public IDiscordOverlayManager.set_locked_callback set_locked;
	/** C type : open_activity_invite_callback* */
	public IDiscordOverlayManager.open_activity_invite_callback open_activity_invite;
	/** C type : open_guild_invite_callback* */
	public IDiscordOverlayManager.open_guild_invite_callback open_guild_invite;
	/** C type : open_voice_settings_callback* */
	public IDiscordOverlayManager.open_voice_settings_callback open_voice_settings;
	public interface is_enabled_callback extends Callback {
		void apply(IDiscordOverlayManager manager, Pointer enabled);
	};
	public interface is_locked_callback extends Callback {
		void apply(IDiscordOverlayManager manager, Pointer locked);
	};
	public interface set_locked_callback_callback_callback extends Callback {
		void apply(Pointer callback_data, int result);
	};
	public interface set_locked_callback extends Callback {
		void apply(IDiscordOverlayManager manager, byte locked, Pointer callback_data, IDiscordOverlayManager.set_locked_callback_callback_callback callback);
	};
	public interface open_activity_invite_callback_callback_callback extends Callback {
		void apply(Pointer callback_data, int result);
	};
	public interface open_activity_invite_callback extends Callback {
		void apply(IDiscordOverlayManager manager, int type, Pointer callback_data, IDiscordOverlayManager.open_activity_invite_callback_callback_callback callback);
	};
	public interface open_guild_invite_callback_callback_callback extends Callback {
		void apply(Pointer callback_data, int result);
	};
	public interface open_guild_invite_callback extends Callback {
		void apply(IDiscordOverlayManager manager, Pointer code, Pointer callback_data, IDiscordOverlayManager.open_guild_invite_callback_callback_callback callback);
	};
	public interface open_voice_settings_callback_callback_callback extends Callback {
		void apply(Pointer callback_data, int result);
	};
	public interface open_voice_settings_callback extends Callback {
		void apply(IDiscordOverlayManager manager, Pointer callback_data, IDiscordOverlayManager.open_voice_settings_callback_callback_callback callback);
	};
	public IDiscordOverlayManager() {
		super();
	}
	protected List<String > getFieldOrder() {
		return Arrays.asList("is_enabled", "is_locked", "set_locked", "open_activity_invite", "open_guild_invite", "open_voice_settings");
	}
	/**
	 * @param is_enabled C type : is_enabled_callback*<br>
	 * @param is_locked C type : is_locked_callback*<br>
	 * @param set_locked C type : set_locked_callback*<br>
	 * @param open_activity_invite C type : open_activity_invite_callback*<br>
	 * @param open_guild_invite C type : open_guild_invite_callback*<br>
	 * @param open_voice_settings C type : open_voice_settings_callback*
	 */
	public IDiscordOverlayManager(IDiscordOverlayManager.is_enabled_callback is_enabled, IDiscordOverlayManager.is_locked_callback is_locked, IDiscordOverlayManager.set_locked_callback set_locked, IDiscordOverlayManager.open_activity_invite_callback open_activity_invite, IDiscordOverlayManager.open_guild_invite_callback open_guild_invite, IDiscordOverlayManager.open_voice_settings_callback open_voice_settings) {
		super();
		this.is_enabled = is_enabled;
		this.is_locked = is_locked;
		this.set_locked = set_locked;
		this.open_activity_invite = open_activity_invite;
		this.open_guild_invite = open_guild_invite;
		this.open_voice_settings = open_voice_settings;
	}
	public IDiscordOverlayManager(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends IDiscordOverlayManager implements Structure.ByReference {
		
	};
	public static class ByValue extends IDiscordOverlayManager implements Structure.ByValue {
		
	};
}
