package io.github.djr4488.discord.bot.ping.initiator.listener;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import org.djr.cdi.logs.Slf4jLogger;
import org.djr.cdi.properties.Config;
import org.slf4j.Logger;

import io.github.djr4488.discord.bot.BotException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

@Startup
@Singleton
public class PingListenerInitializer {
	@Inject
	@Slf4jLogger
	private Logger log;
	@Inject
	@Config(propertyName = "discord.bot.token", defaultValue = "")
	private String discordToken;
	@Inject
	private PingListener pingListener;

	@PostConstruct
	public void postConstruct() {
		log.trace("postContruct() test");
		try {
			JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
			jdaBuilder.setToken(discordToken);
			jdaBuilder.addEventListeners(pingListener);
			jdaBuilder.build();
		} catch (LoginException lEx) {
			log.error("listen() login error", lEx);
			throw new BotException();
		}
	}


}
