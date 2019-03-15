package io.github.djr4488.discord.bot.ping.initiator.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.djr.cdi.logs.Slf4jLogger;
import org.slf4j.Logger;

@Path("bot")
@ApplicationScoped
public class PingEndpoint {
	@Inject
	@Slf4jLogger
	private Logger log;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ping")
	public Response ping(String ping) {
		return Response.ok("pong").build();
	}
}
