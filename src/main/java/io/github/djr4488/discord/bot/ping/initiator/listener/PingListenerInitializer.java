package io.github.djr4488.discord.bot.ping.initiator.listener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import org.djr.cdi.logs.Slf4jLogger;
import org.djr.cdi.properties.Config;
import org.slf4j.Logger;

import io.github.djr4488.discord.bot.BotException;

@Startup
@Singleton
public class PingListenerInitializer {
	@Inject
	@Slf4jLogger
	private Logger log;
	@Inject
	@Config(propertyName = "discord.bot.token", defaultValue = "<<your discord bot key here>>")
	private String discordToken;
	@Inject
	private PingListener pingListener;
	@Resource(name = "scheduledExecutor")
	private ManagedScheduledExecutorService managedScheduledExecutorService;
	@Resource(name = "executorManaged")
	private ManagedExecutorService managedExecutorService;

	@PostConstruct
	public void postConstruct() {
		log.trace("postContruct() test");
		try {
			JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT);
			jdaBuilder.setToken(discordToken);
			jdaBuilder.addEventListener(pingListener);
			jdaBuilder.setRateLimitPool(managedScheduledExecutorService);
			jdaBuilder.setGatewayPool(managedScheduledExecutorService);
			jdaBuilder.setCallbackPool(managedExecutorService);
			jdaBuilder.build();
		} catch (LoginException lEx) {
			log.error("listen() login error", lEx);
			throw new BotException();
		}
	}


}
