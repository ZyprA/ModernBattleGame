package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.event.GamePlayerDamageEvent;
import net.zypr.modernBattleGame.api.event.PlayerDamageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class EventStreamer implements Listener {
    private static boolean check = false;

    public static void on(JavaPlugin plugin) {
        if (check) return;
        check = true;
        plugin.getServer().getPluginManager().registerEvents(new EventStreamer(), plugin);
    }

    private static final List<GamePhaseScheduler<? , ?>> GAME_PHASE_SCHEDULERS = new ArrayList<>();

    @EventHandler
    void onPlayerDamageEvent(GamePlayerDamageEvent gamePlayerDamageEvent) {

        GAME_PHASE_SCHEDULERS.stream().filter(gamePhaseScheduler -> gamePhaseScheduler.getPhase() instanceof PlayerDamageListener).filter(gamePhaseScheduler ->gamePlayerDamageEvent.isValid(gamePhaseScheduler.getGamePlayers())).map(GamePhaseScheduler::getPhase).map(PlayerDamageListener.class::cast).forEach(e -> e.receive(gamePlayerDamageEvent));
    }

    public static void register(GamePhaseScheduler<?, ?> gamePhaseScheduler) {
        GAME_PHASE_SCHEDULERS.add(gamePhaseScheduler);
    }

    public static void unregister(GamePhaseScheduler<?, ?> gamePhaseScheduler) {
        GAME_PHASE_SCHEDULERS.remove(gamePhaseScheduler);
    }
}
