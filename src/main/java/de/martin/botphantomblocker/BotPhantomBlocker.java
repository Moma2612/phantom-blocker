package de.martin.botphantomblocker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotPhantomBlocker implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bot-phantom-blocker");

	/** Reset interval: 1200 ticks = 60 seconds at 20 TPS. */
	public static final int INTERVAL_TICKS = 1200;

	/** Class name of Carpet's fake player. Checked by name so Carpet is not needed at compile time. */
	private static final String CARPET_FAKE_PLAYER = "carpet.patches.EntityPlayerMPFake";

	@Override
	public void onInitialize() {
		LOGGER.info("Bot Phantom Blocker active: resetting rest timer of Carpet bots every {} ticks.", INTERVAL_TICKS);
	}

	/** Called at the end of every server tick (see MinecraftServerMixin). */
	public static void onServerTick(MinecraftServer server) {
		if (server.getTickCount() % INTERVAL_TICKS != 0) {
			return;
		}
		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			if (isCarpetBot(player)) {
				// Same call vanilla uses when a player sleeps: time_since_rest -> 0
				player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			}
		}
	}

	/** Returns true if the player is a Carpet bot (or a subclass of it). */
	private static boolean isCarpetBot(ServerPlayer player) {
		for (Class<?> c = player.getClass(); c != null && c != ServerPlayer.class; c = c.getSuperclass()) {
			if (CARPET_FAKE_PLAYER.equals(c.getName())) {
				return true;
			}
		}
		return false;
	}
}
