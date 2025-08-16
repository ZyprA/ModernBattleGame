package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.event.GamePlayerDamageEvent;
import net.zypr.modernBattleGame.api.event.PlayerDamageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class EventStreamer implements Listener {

    public static void on(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new EventStreamer(), plugin);
    }

    private static final HashSet<BattlePhaseScheduler<?>> phaseSchedulers = new HashSet<>();

    @EventHandler
    void onPlayerDamageEvent(GamePlayerDamageEvent gamePlayerDamageEvent) {

        phaseSchedulers.stream().filter(phaseScheduler -> phaseScheduler.getPhase() instanceof PlayerDamageListener).filter(battlePhaseScheduler->gamePlayerDamageEvent.isValid(battlePhaseScheduler.getGamePlayers())).map(BattlePhaseScheduler::getPhase).map(PlayerDamageListener.class::cast).forEach(e -> e.receive(gamePlayerDamageEvent));
    }

    public static void register(BattlePhaseScheduler<?> battlePhaseScheduler) {
        phaseSchedulers.add(battlePhaseScheduler);
    }

    public static void unregister(BattlePhaseScheduler<?> battlePhaseScheduler) {
        phaseSchedulers.remove(battlePhaseScheduler);
    }
}
