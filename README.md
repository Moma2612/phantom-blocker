Bot Phantom Blocker
<img src="src/main/resources/assets/bot-phantom-blocker/icon.png" width="128" alt="Bot Phantom Blocker icon">
Stop Carpet bots from spawning phantoms without touching vanilla behavior for real players.
A tiny server-side Fabric mod for technical Minecraft servers. Carpet fake players (`/player <name> spawn`) never sleep, so after three in-game days phantoms start spawning around them, attacking nearby players and breaking farms. Bot Phantom Blocker quietly resets the "time since rest" statistic of every Carpet bot once per minute, so bots never reach the phantom threshold.
Real players are not affected: if you don't sleep, phantoms still come for you, exactly as in vanilla.
---
Features
🛌 Resets `minecraft:time_since_rest` for Carpet bots only, once every 60 seconds
👤 Real players keep 100% vanilla phantom behavior
🖥️ Server-side. Players joining your server don't need to install anything.
📦 No dependencies besides Fabric Loader: no Fabric API, no Carpet API required
⚡ Negligible performance cost (one check per minute)
🔧 No configuration, no commands, just drop it in
Requirements
Component	Version
Minecraft (Java Edition)	26.3
Fabric Loader	0.19.5 or newer
Java	25 or newer
Carpet	any version (optional, the mod simply does nothing without it)
Installation
Download `bot-phantom-blocker-<version>.jar` from the Releases page.
Put the file into the `mods` folder of your server. For single-player, put it into the `mods` folder of your game instead.
Restart the server.
You should see this line in the server log:
```
Bot Phantom Blocker active: resetting rest timer of Carpet bots every 1200 ticks.
```
How it works
Minecraft spawns phantoms near a player when their `time_since_rest` statistic is above 72,000 ticks (3 in-game days). Sleeping in a bed resets this value to 0.
Bot Phantom Blocker hooks into the server tick. Every 1,200 ticks (60 seconds) it goes through all online players. For each player that is a Carpet fake player (`carpet.patches.EntityPlayerMPFake`), it resets the statistic the same way a bed does. Because the value never gets anywhere near 72,000, phantoms never spawn for bots.
Bots are detected by their class name, so the mod has no compile-time or runtime dependency on Carpet.
Verify it in-game
```
/scoreboard objectives add rest minecraft.custom:minecraft.time_since_rest
/scoreboard objectives setdisplay sidebar rest
```
Your own value keeps counting up. Every bot's value drops back to 0 at least once per minute.
FAQ
Does this change phantom spawning for real players?
No. Only Carpet fake players are affected.
Do I need this on the client?
Not for multiplayer: install it on the server only. For single-player, install it in your own `mods` folder.
Does it work with other fake-player mods?
Only Carpet bots are detected. Other mods' fake players are left untouched.
Can I change the interval?
Not without rebuilding. Change `INTERVAL_TICKS` in `BotPhantomBlocker.java` (20 ticks = 1 second).
Building from source
Requires Java 25.
```bash
./gradlew build
```
The jar ends up in `build/libs/`. Every push to this repository is also built automatically by GitHub Actions. You can download the jar from the Artifacts section of a workflow run.
License
This project is licensed under the MIT License.
