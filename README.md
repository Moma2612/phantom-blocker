# Bot Phantom Blocker (Fabric, Minecraft 26.3)

Serverseitige Fabric-Mod: Setzt jede Minute den Schlaftimer (`time_since_rest`) aller
Carpet-Bots auf 0, damit sie keine Phantome spawnen. Echte Spieler bleiben unberuehrt.

- Keine Abhaengigkeit von Fabric API oder Carpet
- Nur auf dem Server installieren (`mods/`-Ordner)

## Bauen ueber GitHub
1. Neues Repository auf github.com anlegen und den Inhalt dieses Ordners hochladen.
2. Unter **Actions** laeuft automatisch der Workflow `build`.
3. Nach ~2-3 Minuten den Lauf oeffnen, unten unter **Artifacts** herunterladen,
   entpacken und `bot-phantom-blocker-1.0.0.jar` in den `mods`-Ordner legen.

## Lokal bauen (Java 25 noetig)
`./gradlew build` (Windows: `gradlew.bat build`) -> Jar liegt in `build/libs/`.

## Intervall aendern
`INTERVAL_TICKS` in `BotPhantomBlocker.java` (20 Ticks = 1 Sekunde).
