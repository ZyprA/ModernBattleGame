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

    private static final List<BattlePhaseScheduler<? , ?>> battlePhaseSchedulers = new ArrayList<>();

    @EventHandler
    void onPlayerDamageEvent(GamePlayerDamageEvent gamePlayerDamageEvent) {

        battlePhaseSchedulers.stream().filter(battlePhaseScheduler -> battlePhaseScheduler.getPhase() instanceof PlayerDamageListener).filter(battlePhaseScheduler->gamePlayerDamageEvent.isValid(battlePhaseScheduler.getGamePlayers())).map(BattlePhaseScheduler::getPhase).map(PlayerDamageListener.class::cast).forEach(e -> e.receive(gamePlayerDamageEvent));
    }

    public static void register(BattlePhaseScheduler<?, ?> battlePhaseScheduler) {
        battlePhaseSchedulers.add(battlePhaseScheduler);
    }

    public static void unregister(BattlePhaseScheduler<?, ?> battlePhaseScheduler) {
        battlePhaseSchedulers.remove(battlePhaseScheduler);
    }
}
