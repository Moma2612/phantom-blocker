package de.martin.botphantomblocker;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotPhantomBlocker implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bot-phantom-blocker");

	/** Alle 1200 Ticks = 60 Sekunden (bei 20 TPS). */
	public static final int INTERVAL_TICKS = 1200;

	/** Klassenname des Carpet-Bots. */
	private static final String CARPET_FAKE_PLAYER = "carpet.patches.EntityPlayerMPFake";

	@Override
	public void onInitialize() {
		LOGGER.info("Bot Phantom Blocker aktiv: Schlaftimer von Carpet-Bots wird alle {} Ticks zurueckgesetzt.", INTERVAL_TICKS);
	}

	/** Wird jeden Server-Tick aufgerufen (siehe MinecraftServerMixin). */
	public static void onServerTick(MinecraftServer server) {
		if (server.getTickCount() % INTERVAL_TICKS != 0) {
			return;
		}
		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			if (isCarpetBot(player)) {
				player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
			}
		}
	}

	/** true, wenn der Spieler ein Carpet-Bot ist. */
	private static boolean isCarpetBot(ServerPlayer player) {
		for (Class<?> c = player.getClass(); c != null && c != ServerPlayer.class; c = c.getSuperclass()) {
			if (CARPET_FAKE_PLAYER.equals(c.getName())) {
				return true;
			}
		}
		return false;
	}
}
