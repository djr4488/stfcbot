package io.github.djr4488.discord.bot.ping.initiator.listener;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.djr.cdi.logs.Slf4jLogger;
import org.slf4j.Logger;

import net.dv8tion.jda.api.events.DisconnectEvent;
import net.dv8tion.jda.api.events.ExceptionEvent;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ReconnectedEvent;
import net.dv8tion.jda.api.events.ResumedEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.StatusChangeEvent;
import net.dv8tion.jda.api.events.UpdateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryCreateEvent;
import net.dv8tion.jda.api.events.channel.category.CategoryDeleteEvent;
import net.dv8tion.jda.api.events.channel.category.GenericCategoryEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.category.update.CategoryUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.category.update.GenericCategoryUpdateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.priv.PrivateChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.GenericTextChannelEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.text.TextChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.text.update.GenericTextChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNSFWEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateSlowmodeEvent;
import net.dv8tion.jda.api.events.channel.text.update.TextChannelUpdateTopicEvent;
import net.dv8tion.jda.api.events.channel.voice.GenericVoiceChannelEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.voice.update.GenericVoiceChannelUpdateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateBitrateEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateNameEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateParentEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdatePositionEvent;
import net.dv8tion.jda.api.events.channel.voice.update.VoiceChannelUpdateUserLimitEvent;
import net.dv8tion.jda.api.events.emote.EmoteAddedEvent;
import net.dv8tion.jda.api.events.emote.EmoteRemovedEvent;
import net.dv8tion.jda.api.events.emote.GenericEmoteEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateNameEvent;
import net.dv8tion.jda.api.events.emote.update.EmoteUpdateRolesEvent;
import net.dv8tion.jda.api.events.emote.update.GenericEmoteUpdateEvent;
import net.dv8tion.jda.api.events.guild.GenericGuildEvent;
import net.dv8tion.jda.api.events.guild.GuildAvailableEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.guild.UnavailableGuildJoinedEvent;
import net.dv8tion.jda.api.events.guild.member.GenericGuildMemberEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.update.GenericGuildUpdateEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateAfkTimeoutEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateExplicitContentLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateFeaturesEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateIconEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateMFALevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNameEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateNotificationLevelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateOwnerEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateRegionEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSplashEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateSystemChannelEvent;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateVerificationLevelEvent;
import net.dv8tion.jda.api.events.guild.voice.GenericGuildVoiceEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSelfMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceSuppressEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.http.HttpRequestEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageEmbedEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GenericGuildMessageReactionEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.GenericPrivateMessageEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.react.GenericPrivateMessageReactionEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.GenericRoleEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;
import net.dv8tion.jda.api.events.role.update.GenericRoleUpdateEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateColorEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateHoistedEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateMentionableEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdateNameEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePermissionsEvent;
import net.dv8tion.jda.api.events.role.update.RoleUpdatePositionEvent;
import net.dv8tion.jda.api.events.self.GenericSelfUpdateEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateAvatarEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateEmailEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateMFAEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateNameEvent;
import net.dv8tion.jda.api.events.self.SelfUpdateVerifiedEvent;
import net.dv8tion.jda.api.events.user.GenericUserEvent;
import net.dv8tion.jda.api.events.user.UserActivityEndEvent;
import net.dv8tion.jda.api.events.user.UserActivityStartEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.events.user.update.GenericUserPresenceEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateActivityOrderEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateAvatarEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateDiscriminatorEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateNameEvent;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@ApplicationScoped
public class PingListener implements EventListener {
	@Inject
	@Slf4jLogger
	private Logger log;

	public void onGenericEvent(GenericEvent event) {
	}

	public void onGenericUpdate(UpdateEvent<?, ?> event) {
	}

	public void onReady(ReadyEvent event) {
	}

	public void onResume(ResumedEvent event) {
	}

	public void onReconnect(ReconnectedEvent event) {
	}

	public void onDisconnect(DisconnectEvent event) {
	}

	public void onShutdown(ShutdownEvent event) {
	}

	public void onStatusChange(StatusChangeEvent event) {
	}

	public void onException(ExceptionEvent event) {
	}

	public void onUserUpdateName(UserUpdateNameEvent event) {
	}

	public void onUserUpdateDiscriminator(UserUpdateDiscriminatorEvent event) {
	}

	public void onUserUpdateAvatar(UserUpdateAvatarEvent event) {
	}

	public void onUserUpdateOnlineStatus(UserUpdateOnlineStatusEvent event) {
	}

	public void onUserUpdateActivityOrder(UserUpdateActivityOrderEvent event) {
	}

	public void onUserTyping(UserTypingEvent event) {
	}

	public void onUserActivityStart(UserActivityStartEvent event) {
	}

	public void onUserActivityEnd(UserActivityEndEvent event) {
	}

	public void onSelfUpdateAvatar(SelfUpdateAvatarEvent event) {
	}

	public void onSelfUpdateEmail(SelfUpdateEmailEvent event) {
	}

	public void onSelfUpdateMFA(SelfUpdateMFAEvent event) {
	}

	public void onSelfUpdateName(SelfUpdateNameEvent event) {
	}

	public void onSelfUpdateVerified(SelfUpdateVerifiedEvent event) {
	}

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
	}

	public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
	}

	public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
	}

	public void onGuildMessageEmbed(GuildMessageEmbedEvent event) {
	}

	public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
	}

	public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {
	}

	public void onGuildMessageReactionRemoveAll(GuildMessageReactionRemoveAllEvent event) {
	}

	public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
	}

	public void onPrivateMessageUpdate(PrivateMessageUpdateEvent event) {
	}

	public void onPrivateMessageDelete(PrivateMessageDeleteEvent event) {
	}

	public void onPrivateMessageEmbed(PrivateMessageEmbedEvent event) {
	}

	public void onPrivateMessageReactionAdd(PrivateMessageReactionAddEvent event) {
	}

	public void onPrivateMessageReactionRemove(PrivateMessageReactionRemoveEvent event) {
	}

	public void onMessageReceived(MessageReceivedEvent event) {
		log.trace("onMessageReceived() event:{}", event);
		if (!event.getAuthor().isBot() && event.getMessage().getContentRaw().equals("q!ping")) {
			event.getChannel().sendMessage("pong!").queue();
		}
	}

	public void onMessageUpdate(MessageUpdateEvent event) {
	}

	public void onMessageDelete(MessageDeleteEvent event) {
	}

	public void onMessageBulkDelete(MessageBulkDeleteEvent event) {
	}

	public void onMessageEmbed(MessageEmbedEvent event) {
	}

	public void onMessageReactionAdd(MessageReactionAddEvent event) {
	}

	public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
	}

	public void onMessageReactionRemoveAll(MessageReactionRemoveAllEvent event) {
	}

	public void onTextChannelDelete(TextChannelDeleteEvent event) {
	}

	public void onTextChannelUpdateName(TextChannelUpdateNameEvent event) {
	}

	public void onTextChannelUpdateTopic(TextChannelUpdateTopicEvent event) {
	}

	public void onTextChannelUpdatePosition(TextChannelUpdatePositionEvent event) {
	}

	public void onTextChannelUpdatePermissions(TextChannelUpdatePermissionsEvent event) {
	}

	public void onTextChannelUpdateNSFW(TextChannelUpdateNSFWEvent event) {
	}

	public void onTextChannelUpdateParent(TextChannelUpdateParentEvent event) {
	}

	public void onTextChannelUpdateSlowmode(TextChannelUpdateSlowmodeEvent event) {
	}

	public void onTextChannelCreate(TextChannelCreateEvent event) {
	}

	public void onVoiceChannelDelete(VoiceChannelDeleteEvent event) {
	}

	public void onVoiceChannelUpdateName(VoiceChannelUpdateNameEvent event) {
	}

	public void onVoiceChannelUpdatePosition(VoiceChannelUpdatePositionEvent event) {
	}

	public void onVoiceChannelUpdateUserLimit(VoiceChannelUpdateUserLimitEvent event) {
	}

	public void onVoiceChannelUpdateBitrate(VoiceChannelUpdateBitrateEvent event) {
	}

	public void onVoiceChannelUpdatePermissions(VoiceChannelUpdatePermissionsEvent event) {
	}

	public void onVoiceChannelUpdateParent(VoiceChannelUpdateParentEvent event) {
	}

	public void onVoiceChannelCreate(VoiceChannelCreateEvent event) {
	}

	public void onCategoryDelete(CategoryDeleteEvent event) {
	}

	public void onCategoryUpdateName(CategoryUpdateNameEvent event) {
	}

	public void onCategoryUpdatePosition(CategoryUpdatePositionEvent event) {
	}

	public void onCategoryUpdatePermissions(CategoryUpdatePermissionsEvent event) {
	}

	public void onCategoryCreate(CategoryCreateEvent event) {
	}

	public void onPrivateChannelCreate(PrivateChannelCreateEvent event) {
	}

	public void onPrivateChannelDelete(PrivateChannelDeleteEvent event) {
	}

	public void onGuildReady(GuildReadyEvent event) {
	}

	public void onGuildJoin(GuildJoinEvent event) {
	}

	public void onGuildLeave(GuildLeaveEvent event) {
	}

	public void onGuildAvailable(GuildAvailableEvent event) {
	}

	public void onGuildUnavailable(GuildUnavailableEvent event) {
	}

	public void onUnavailableGuildJoined(UnavailableGuildJoinedEvent event) {
	}

	public void onGuildBan(GuildBanEvent event) {
	}

	public void onGuildUnban(GuildUnbanEvent event) {
	}

	public void onGuildUpdateAfkChannel(GuildUpdateAfkChannelEvent event) {
	}

	public void onGuildUpdateSystemChannel(GuildUpdateSystemChannelEvent event) {
	}

	public void onGuildUpdateAfkTimeout(GuildUpdateAfkTimeoutEvent event) {
	}

	public void onGuildUpdateExplicitContentLevel(GuildUpdateExplicitContentLevelEvent event) {
	}

	public void onGuildUpdateIcon(GuildUpdateIconEvent event) {
	}

	public void onGuildUpdateMFALevel(GuildUpdateMFALevelEvent event) {
	}

	public void onGuildUpdateName(GuildUpdateNameEvent event) {
	}

	public void onGuildUpdateNotificationLevel(GuildUpdateNotificationLevelEvent event) {
	}

	public void onGuildUpdateOwner(GuildUpdateOwnerEvent event) {
	}

	public void onGuildUpdateRegion(GuildUpdateRegionEvent event) {
	}

	public void onGuildUpdateSplash(GuildUpdateSplashEvent event) {
	}

	public void onGuildUpdateVerificationLevel(GuildUpdateVerificationLevelEvent event) {
	}

	public void onGuildUpdateFeatures(GuildUpdateFeaturesEvent event) {
	}

	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
	}

	public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
	}

	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
	}

	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
	}

	public void onGuildMemberNickChange(GuildMemberNickChangeEvent event) {
	}

	public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
	}

	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
	}

	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
	}

	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
	}

	public void onGuildVoiceMute(GuildVoiceMuteEvent event) {
	}

	public void onGuildVoiceDeafen(GuildVoiceDeafenEvent event) {
	}

	public void onGuildVoiceGuildMute(GuildVoiceGuildMuteEvent event) {
	}

	public void onGuildVoiceGuildDeafen(GuildVoiceGuildDeafenEvent event) {
	}

	public void onGuildVoiceSelfMute(GuildVoiceSelfMuteEvent event) {
	}

	public void onGuildVoiceSelfDeafen(GuildVoiceSelfDeafenEvent event) {
	}

	public void onGuildVoiceSuppress(GuildVoiceSuppressEvent event) {
	}

	public void onRoleCreate(RoleCreateEvent event) {
	}

	public void onRoleDelete(RoleDeleteEvent event) {
	}

	public void onRoleUpdateColor(RoleUpdateColorEvent event) {
	}

	public void onRoleUpdateHoisted(RoleUpdateHoistedEvent event) {
	}

	public void onRoleUpdateMentionable(RoleUpdateMentionableEvent event) {
	}

	public void onRoleUpdateName(RoleUpdateNameEvent event) {
	}

	public void onRoleUpdatePermissions(RoleUpdatePermissionsEvent event) {
	}

	public void onRoleUpdatePosition(RoleUpdatePositionEvent event) {
	}

	public void onEmoteAdded(EmoteAddedEvent event) {
	}

	public void onEmoteRemoved(EmoteRemovedEvent event) {
	}

	public void onEmoteUpdateName(EmoteUpdateNameEvent event) {
	}

	public void onEmoteUpdateRoles(EmoteUpdateRolesEvent event) {
	}

	public void onHttpRequest(HttpRequestEvent event) {
	}

	public void onGenericMessage(GenericMessageEvent event) {
	}

	public void onGenericMessageReaction(GenericMessageReactionEvent event) {
	}

	public void onGenericGuildMessage(GenericGuildMessageEvent event) {
	}

	public void onGenericGuildMessageReaction(GenericGuildMessageReactionEvent event) {
	}

	public void onGenericPrivateMessage(GenericPrivateMessageEvent event) {
	}

	public void onGenericPrivateMessageReaction(GenericPrivateMessageReactionEvent event) {
	}

	public void onGenericUser(GenericUserEvent event) {
	}

	public void onGenericUserPresence(GenericUserPresenceEvent event) {
	}

	public void onGenericSelfUpdate(GenericSelfUpdateEvent event) {
	}

	public void onGenericTextChannel(GenericTextChannelEvent event) {
	}

	public void onGenericTextChannelUpdate(GenericTextChannelUpdateEvent event) {
	}

	public void onGenericVoiceChannel(GenericVoiceChannelEvent event) {
	}

	public void onGenericVoiceChannelUpdate(GenericVoiceChannelUpdateEvent event) {
	}

	public void onGenericCategory(GenericCategoryEvent event) {
	}

	public void onGenericCategoryUpdate(GenericCategoryUpdateEvent event) {
	}

	public void onGenericGuild(GenericGuildEvent event) {
	}

	public void onGenericGuildUpdate(GenericGuildUpdateEvent event) {
	}

	public void onGenericGuildMember(GenericGuildMemberEvent event) {
	}

	public void onGenericGuildVoice(GenericGuildVoiceEvent event) {
	}

	public void onGenericRole(GenericRoleEvent event) {
	}

	public void onGenericRoleUpdate(GenericRoleUpdateEvent event) {
	}

	public void onGenericEmote(GenericEmoteEvent event) {
	}

	public void onGenericEmoteUpdate(GenericEmoteUpdateEvent event) {
	}

	public void onEvent(GenericEvent event) {
		this.onGenericEvent(event);
		if (event instanceof UpdateEvent) {
			this.onGenericUpdate((UpdateEvent)event);
		}

		if (event instanceof ReadyEvent) {
			this.onReady((ReadyEvent)event);
		} else if (event instanceof ResumedEvent) {
			this.onResume((ResumedEvent)event);
		} else if (event instanceof ReconnectedEvent) {
			this.onReconnect((ReconnectedEvent)event);
		} else if (event instanceof DisconnectEvent) {
			this.onDisconnect((DisconnectEvent)event);
		} else if (event instanceof ShutdownEvent) {
			this.onShutdown((ShutdownEvent)event);
		} else if (event instanceof StatusChangeEvent) {
			this.onStatusChange((StatusChangeEvent)event);
		} else if (event instanceof ExceptionEvent) {
			this.onException((ExceptionEvent)event);
		} else if (event instanceof GuildMessageReceivedEvent) {
			this.onGuildMessageReceived((GuildMessageReceivedEvent)event);
		} else if (event instanceof GuildMessageUpdateEvent) {
			this.onGuildMessageUpdate((GuildMessageUpdateEvent)event);
		} else if (event instanceof GuildMessageDeleteEvent) {
			this.onGuildMessageDelete((GuildMessageDeleteEvent)event);
		} else if (event instanceof GuildMessageEmbedEvent) {
			this.onGuildMessageEmbed((GuildMessageEmbedEvent)event);
		} else if (event instanceof GuildMessageReactionAddEvent) {
			this.onGuildMessageReactionAdd((GuildMessageReactionAddEvent)event);
		} else if (event instanceof GuildMessageReactionRemoveEvent) {
			this.onGuildMessageReactionRemove((GuildMessageReactionRemoveEvent)event);
		} else if (event instanceof GuildMessageReactionRemoveAllEvent) {
			this.onGuildMessageReactionRemoveAll((GuildMessageReactionRemoveAllEvent)event);
		} else if (event instanceof PrivateMessageReceivedEvent) {
			this.onPrivateMessageReceived((PrivateMessageReceivedEvent)event);
		} else if (event instanceof PrivateMessageUpdateEvent) {
			this.onPrivateMessageUpdate((PrivateMessageUpdateEvent)event);
		} else if (event instanceof PrivateMessageDeleteEvent) {
			this.onPrivateMessageDelete((PrivateMessageDeleteEvent)event);
		} else if (event instanceof PrivateMessageEmbedEvent) {
			this.onPrivateMessageEmbed((PrivateMessageEmbedEvent)event);
		} else if (event instanceof PrivateMessageReactionAddEvent) {
			this.onPrivateMessageReactionAdd((PrivateMessageReactionAddEvent)event);
		} else if (event instanceof PrivateMessageReactionRemoveEvent) {
			this.onPrivateMessageReactionRemove((PrivateMessageReactionRemoveEvent)event);
		} else if (event instanceof MessageReceivedEvent) {
			this.onMessageReceived((MessageReceivedEvent)event);
		} else if (event instanceof MessageUpdateEvent) {
			this.onMessageUpdate((MessageUpdateEvent)event);
		} else if (event instanceof MessageDeleteEvent) {
			this.onMessageDelete((MessageDeleteEvent)event);
		} else if (event instanceof MessageBulkDeleteEvent) {
			this.onMessageBulkDelete((MessageBulkDeleteEvent)event);
		} else if (event instanceof MessageEmbedEvent) {
			this.onMessageEmbed((MessageEmbedEvent)event);
		} else if (event instanceof MessageReactionAddEvent) {
			this.onMessageReactionAdd((MessageReactionAddEvent)event);
		} else if (event instanceof MessageReactionRemoveEvent) {
			this.onMessageReactionRemove((MessageReactionRemoveEvent)event);
		} else if (event instanceof MessageReactionRemoveAllEvent) {
			this.onMessageReactionRemoveAll((MessageReactionRemoveAllEvent)event);
		} else if (event instanceof UserUpdateNameEvent) {
			this.onUserUpdateName((UserUpdateNameEvent)event);
		} else if (event instanceof UserUpdateDiscriminatorEvent) {
			this.onUserUpdateDiscriminator((UserUpdateDiscriminatorEvent)event);
		} else if (event instanceof UserUpdateAvatarEvent) {
			this.onUserUpdateAvatar((UserUpdateAvatarEvent)event);
		} else if (event instanceof UserUpdateActivityOrderEvent) {
			this.onUserUpdateActivityOrder((UserUpdateActivityOrderEvent)event);
		} else if (event instanceof UserUpdateOnlineStatusEvent) {
			this.onUserUpdateOnlineStatus((UserUpdateOnlineStatusEvent)event);
		} else if (event instanceof UserTypingEvent) {
			this.onUserTyping((UserTypingEvent)event);
		} else if (event instanceof UserActivityStartEvent) {
			this.onUserActivityStart((UserActivityStartEvent)event);
		} else if (event instanceof UserActivityEndEvent) {
			this.onUserActivityEnd((UserActivityEndEvent)event);
		} else if (event instanceof SelfUpdateAvatarEvent) {
			this.onSelfUpdateAvatar((SelfUpdateAvatarEvent)event);
		} else if (event instanceof SelfUpdateEmailEvent) {
			this.onSelfUpdateEmail((SelfUpdateEmailEvent)event);
		} else if (event instanceof SelfUpdateMFAEvent) {
			this.onSelfUpdateMFA((SelfUpdateMFAEvent)event);
		} else if (event instanceof SelfUpdateNameEvent) {
			this.onSelfUpdateName((SelfUpdateNameEvent)event);
		} else if (event instanceof SelfUpdateVerifiedEvent) {
			this.onSelfUpdateVerified((SelfUpdateVerifiedEvent)event);
		} else if (event instanceof TextChannelCreateEvent) {
			this.onTextChannelCreate((TextChannelCreateEvent)event);
		} else if (event instanceof TextChannelUpdateNameEvent) {
			this.onTextChannelUpdateName((TextChannelUpdateNameEvent)event);
		} else if (event instanceof TextChannelUpdateTopicEvent) {
			this.onTextChannelUpdateTopic((TextChannelUpdateTopicEvent)event);
		} else if (event instanceof TextChannelUpdatePositionEvent) {
			this.onTextChannelUpdatePosition((TextChannelUpdatePositionEvent)event);
		} else if (event instanceof TextChannelUpdatePermissionsEvent) {
			this.onTextChannelUpdatePermissions((TextChannelUpdatePermissionsEvent)event);
		} else if (event instanceof TextChannelUpdateNSFWEvent) {
			this.onTextChannelUpdateNSFW((TextChannelUpdateNSFWEvent)event);
		} else if (event instanceof TextChannelUpdateParentEvent) {
			this.onTextChannelUpdateParent((TextChannelUpdateParentEvent)event);
		} else if (event instanceof TextChannelUpdateSlowmodeEvent) {
			this.onTextChannelUpdateSlowmode((TextChannelUpdateSlowmodeEvent)event);
		} else if (event instanceof TextChannelDeleteEvent) {
			this.onTextChannelDelete((TextChannelDeleteEvent)event);
		} else if (event instanceof VoiceChannelCreateEvent) {
			this.onVoiceChannelCreate((VoiceChannelCreateEvent)event);
		} else if (event instanceof VoiceChannelUpdateNameEvent) {
			this.onVoiceChannelUpdateName((VoiceChannelUpdateNameEvent)event);
		} else if (event instanceof VoiceChannelUpdatePositionEvent) {
			this.onVoiceChannelUpdatePosition((VoiceChannelUpdatePositionEvent)event);
		} else if (event instanceof VoiceChannelUpdateUserLimitEvent) {
			this.onVoiceChannelUpdateUserLimit((VoiceChannelUpdateUserLimitEvent)event);
		} else if (event instanceof VoiceChannelUpdateBitrateEvent) {
			this.onVoiceChannelUpdateBitrate((VoiceChannelUpdateBitrateEvent)event);
		} else if (event instanceof VoiceChannelUpdatePermissionsEvent) {
			this.onVoiceChannelUpdatePermissions((VoiceChannelUpdatePermissionsEvent)event);
		} else if (event instanceof VoiceChannelUpdateParentEvent) {
			this.onVoiceChannelUpdateParent((VoiceChannelUpdateParentEvent)event);
		} else if (event instanceof VoiceChannelDeleteEvent) {
			this.onVoiceChannelDelete((VoiceChannelDeleteEvent)event);
		} else if (event instanceof CategoryCreateEvent) {
			this.onCategoryCreate((CategoryCreateEvent)event);
		} else if (event instanceof CategoryUpdateNameEvent) {
			this.onCategoryUpdateName((CategoryUpdateNameEvent)event);
		} else if (event instanceof CategoryUpdatePositionEvent) {
			this.onCategoryUpdatePosition((CategoryUpdatePositionEvent)event);
		} else if (event instanceof CategoryUpdatePermissionsEvent) {
			this.onCategoryUpdatePermissions((CategoryUpdatePermissionsEvent)event);
		} else if (event instanceof CategoryDeleteEvent) {
			this.onCategoryDelete((CategoryDeleteEvent)event);
		} else if (event instanceof PrivateChannelCreateEvent) {
			this.onPrivateChannelCreate((PrivateChannelCreateEvent)event);
		} else if (event instanceof PrivateChannelDeleteEvent) {
			this.onPrivateChannelDelete((PrivateChannelDeleteEvent)event);
		} else if (event instanceof GuildReadyEvent) {
			this.onGuildReady((GuildReadyEvent)event);
		} else if (event instanceof GuildJoinEvent) {
			this.onGuildJoin((GuildJoinEvent)event);
		} else if (event instanceof GuildLeaveEvent) {
			this.onGuildLeave((GuildLeaveEvent)event);
		} else if (event instanceof GuildAvailableEvent) {
			this.onGuildAvailable((GuildAvailableEvent)event);
		} else if (event instanceof GuildUnavailableEvent) {
			this.onGuildUnavailable((GuildUnavailableEvent)event);
		} else if (event instanceof UnavailableGuildJoinedEvent) {
			this.onUnavailableGuildJoined((UnavailableGuildJoinedEvent)event);
		} else if (event instanceof GuildBanEvent) {
			this.onGuildBan((GuildBanEvent)event);
		} else if (event instanceof GuildUnbanEvent) {
			this.onGuildUnban((GuildUnbanEvent)event);
		} else if (event instanceof GuildUpdateAfkChannelEvent) {
			this.onGuildUpdateAfkChannel((GuildUpdateAfkChannelEvent)event);
		} else if (event instanceof GuildUpdateSystemChannelEvent) {
			this.onGuildUpdateSystemChannel((GuildUpdateSystemChannelEvent)event);
		} else if (event instanceof GuildUpdateAfkTimeoutEvent) {
			this.onGuildUpdateAfkTimeout((GuildUpdateAfkTimeoutEvent)event);
		} else if (event instanceof GuildUpdateExplicitContentLevelEvent) {
			this.onGuildUpdateExplicitContentLevel((GuildUpdateExplicitContentLevelEvent)event);
		} else if (event instanceof GuildUpdateIconEvent) {
			this.onGuildUpdateIcon((GuildUpdateIconEvent)event);
		} else if (event instanceof GuildUpdateMFALevelEvent) {
			this.onGuildUpdateMFALevel((GuildUpdateMFALevelEvent)event);
		} else if (event instanceof GuildUpdateNameEvent) {
			this.onGuildUpdateName((GuildUpdateNameEvent)event);
		} else if (event instanceof GuildUpdateNotificationLevelEvent) {
			this.onGuildUpdateNotificationLevel((GuildUpdateNotificationLevelEvent)event);
		} else if (event instanceof GuildUpdateOwnerEvent) {
			this.onGuildUpdateOwner((GuildUpdateOwnerEvent)event);
		} else if (event instanceof GuildUpdateRegionEvent) {
			this.onGuildUpdateRegion((GuildUpdateRegionEvent)event);
		} else if (event instanceof GuildUpdateSplashEvent) {
			this.onGuildUpdateSplash((GuildUpdateSplashEvent)event);
		} else if (event instanceof GuildUpdateVerificationLevelEvent) {
			this.onGuildUpdateVerificationLevel((GuildUpdateVerificationLevelEvent)event);
		} else if (event instanceof GuildUpdateFeaturesEvent) {
			this.onGuildUpdateFeatures((GuildUpdateFeaturesEvent)event);
		} else if (event instanceof GuildMemberJoinEvent) {
			this.onGuildMemberJoin((GuildMemberJoinEvent)event);
		} else if (event instanceof GuildMemberLeaveEvent) {
			this.onGuildMemberLeave((GuildMemberLeaveEvent)event);
		} else if (event instanceof GuildMemberRoleAddEvent) {
			this.onGuildMemberRoleAdd((GuildMemberRoleAddEvent)event);
		} else if (event instanceof GuildMemberRoleRemoveEvent) {
			this.onGuildMemberRoleRemove((GuildMemberRoleRemoveEvent)event);
		} else if (event instanceof GuildMemberNickChangeEvent) {
			this.onGuildMemberNickChange((GuildMemberNickChangeEvent)event);
		} else if (event instanceof GuildVoiceJoinEvent) {
			this.onGuildVoiceJoin((GuildVoiceJoinEvent)event);
		} else if (event instanceof GuildVoiceMoveEvent) {
			this.onGuildVoiceMove((GuildVoiceMoveEvent)event);
		} else if (event instanceof GuildVoiceLeaveEvent) {
			this.onGuildVoiceLeave((GuildVoiceLeaveEvent)event);
		} else if (event instanceof GuildVoiceMuteEvent) {
			this.onGuildVoiceMute((GuildVoiceMuteEvent)event);
		} else if (event instanceof GuildVoiceDeafenEvent) {
			this.onGuildVoiceDeafen((GuildVoiceDeafenEvent)event);
		} else if (event instanceof GuildVoiceGuildMuteEvent) {
			this.onGuildVoiceGuildMute((GuildVoiceGuildMuteEvent)event);
		} else if (event instanceof GuildVoiceGuildDeafenEvent) {
			this.onGuildVoiceGuildDeafen((GuildVoiceGuildDeafenEvent)event);
		} else if (event instanceof GuildVoiceSelfMuteEvent) {
			this.onGuildVoiceSelfMute((GuildVoiceSelfMuteEvent)event);
		} else if (event instanceof GuildVoiceSelfDeafenEvent) {
			this.onGuildVoiceSelfDeafen((GuildVoiceSelfDeafenEvent)event);
		} else if (event instanceof GuildVoiceSuppressEvent) {
			this.onGuildVoiceSuppress((GuildVoiceSuppressEvent)event);
		} else if (event instanceof RoleCreateEvent) {
			this.onRoleCreate((RoleCreateEvent)event);
		} else if (event instanceof RoleDeleteEvent) {
			this.onRoleDelete((RoleDeleteEvent)event);
		} else if (event instanceof RoleUpdateColorEvent) {
			this.onRoleUpdateColor((RoleUpdateColorEvent)event);
		} else if (event instanceof RoleUpdateHoistedEvent) {
			this.onRoleUpdateHoisted((RoleUpdateHoistedEvent)event);
		} else if (event instanceof RoleUpdateMentionableEvent) {
			this.onRoleUpdateMentionable((RoleUpdateMentionableEvent)event);
		} else if (event instanceof RoleUpdateNameEvent) {
			this.onRoleUpdateName((RoleUpdateNameEvent)event);
		} else if (event instanceof RoleUpdatePermissionsEvent) {
			this.onRoleUpdatePermissions((RoleUpdatePermissionsEvent)event);
		} else if (event instanceof RoleUpdatePositionEvent) {
			this.onRoleUpdatePosition((RoleUpdatePositionEvent)event);
		} else if (event instanceof EmoteAddedEvent) {
			this.onEmoteAdded((EmoteAddedEvent)event);
		} else if (event instanceof EmoteRemovedEvent) {
			this.onEmoteRemoved((EmoteRemovedEvent)event);
		} else if (event instanceof EmoteUpdateNameEvent) {
			this.onEmoteUpdateName((EmoteUpdateNameEvent)event);
		} else if (event instanceof EmoteUpdateRolesEvent) {
			this.onEmoteUpdateRoles((EmoteUpdateRolesEvent)event);
		} else if (event instanceof HttpRequestEvent) {
			this.onHttpRequest((HttpRequestEvent)event);
		}

		if (event instanceof GuildVoiceUpdateEvent) {
			this.onGuildVoiceUpdate((GuildVoiceUpdateEvent)event);
		}

		if (event instanceof GenericMessageReactionEvent) {
			this.onGenericMessageReaction((GenericMessageReactionEvent)event);
		} else if (event instanceof GenericPrivateMessageReactionEvent) {
			this.onGenericPrivateMessageReaction((GenericPrivateMessageReactionEvent)event);
		} else if (event instanceof GenericTextChannelUpdateEvent) {
			this.onGenericTextChannelUpdate((GenericTextChannelUpdateEvent)event);
		} else if (event instanceof GenericCategoryUpdateEvent) {
			this.onGenericCategoryUpdate((GenericCategoryUpdateEvent)event);
		} else if (event instanceof GenericGuildMessageReactionEvent) {
			this.onGenericGuildMessageReaction((GenericGuildMessageReactionEvent)event);
		} else if (event instanceof GenericVoiceChannelUpdateEvent) {
			this.onGenericVoiceChannelUpdate((GenericVoiceChannelUpdateEvent)event);
		} else if (event instanceof GenericGuildUpdateEvent) {
			this.onGenericGuildUpdate((GenericGuildUpdateEvent)event);
		} else if (event instanceof GenericGuildMemberEvent) {
			this.onGenericGuildMember((GenericGuildMemberEvent)event);
		} else if (event instanceof GenericGuildVoiceEvent) {
			this.onGenericGuildVoice((GenericGuildVoiceEvent)event);
		} else if (event instanceof GenericRoleUpdateEvent) {
			this.onGenericRoleUpdate((GenericRoleUpdateEvent)event);
		} else if (event instanceof GenericEmoteUpdateEvent) {
			this.onGenericEmoteUpdate((GenericEmoteUpdateEvent)event);
		} else if (event instanceof GenericUserPresenceEvent) {
			this.onGenericUserPresence((GenericUserPresenceEvent)event);
		}

		if (event instanceof GenericMessageEvent) {
			this.onGenericMessage((GenericMessageEvent)event);
		} else if (event instanceof GenericPrivateMessageEvent) {
			this.onGenericPrivateMessage((GenericPrivateMessageEvent)event);
		} else if (event instanceof GenericGuildMessageEvent) {
			this.onGenericGuildMessage((GenericGuildMessageEvent)event);
		} else if (event instanceof GenericUserEvent) {
			this.onGenericUser((GenericUserEvent)event);
		} else if (event instanceof GenericSelfUpdateEvent) {
			this.onGenericSelfUpdate((GenericSelfUpdateEvent)event);
		} else if (event instanceof GenericTextChannelEvent) {
			this.onGenericTextChannel((GenericTextChannelEvent)event);
		} else if (event instanceof GenericVoiceChannelEvent) {
			this.onGenericVoiceChannel((GenericVoiceChannelEvent)event);
		} else if (event instanceof GenericCategoryEvent) {
			this.onGenericCategory((GenericCategoryEvent)event);
		} else if (event instanceof GenericRoleEvent) {
			this.onGenericRole((GenericRoleEvent)event);
		} else if (event instanceof GenericEmoteEvent) {
			this.onGenericEmote((GenericEmoteEvent)event);
		}

		if (event instanceof GenericGuildEvent) {
			this.onGenericGuild((GenericGuildEvent)event);
		}
	}
}
