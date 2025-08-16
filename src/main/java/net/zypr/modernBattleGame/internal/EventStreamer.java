package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.event.GamePlayerDamageEvent;
import net.zypr.modernBattleGame.api.event.PlayerDamageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventStreamer implements Listener {

    private static final List<GameScheduler<?>> GAME_SCHEDULERS = new ArrayList<>();

    @EventHandler
    void onPlayerDamageEvent(GamePlayerDamageEvent gamePlayerDamageEvent) {

        GAME_SCHEDULERS.stream().filter(gameScheduler -> gameScheduler.getGamePhaseScheduler().getPhase() instanceof PlayerDamageListener).filter(gameScheduler ->gamePlayerDamageEvent.isValid(gameScheduler.getGameInstance().getGamePlayers())).map(gameScheduler -> gameScheduler.getGamePhaseScheduler().getPhase()).map(PlayerDamageListener.class::cast).forEach(e -> e.receive(gamePlayerDamageEvent));
    }

    public static void register(GameScheduler<?> gameScheduler) {
        GAME_SCHEDULERS.add(gameScheduler);
    }

    public static void unregister(GameScheduler<?> gameScheduler) {
        GAME_SCHEDULERS.remove(gameScheduler);
    }
}
