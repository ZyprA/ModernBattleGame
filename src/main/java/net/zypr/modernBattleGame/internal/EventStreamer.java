package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.event.GamePlayerDamageEvent;
import net.zypr.modernBattleGame.api.event.PlayerDamageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class EventStreamer implements Listener {

    private final Set<GameScheduler<?>> gameSchedulers = Collections.synchronizedSet(new LinkedHashSet<>());

    public EventStreamer() {
    }

    public void register(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamageEvent(GamePlayerDamageEvent event) {
        synchronized (gameSchedulers) {
            for (GameScheduler<?> scheduler : gameSchedulers) {
                if (!(scheduler.getGamePhaseScheduler().getPhase() instanceof PlayerDamageListener)) continue;
                if (!event.isValid(scheduler.getGameInstance().getGamePlayers())) continue;

                PlayerDamageListener listener = (PlayerDamageListener) scheduler.getGamePhaseScheduler().getPhase();
                listener.receive(event);
            }
        }
    }

    public void register(GameScheduler<?> gameScheduler) {
        gameSchedulers.add(gameScheduler);
    }

    public void unregister(GameScheduler<?> gameScheduler) {
        gameSchedulers.remove(gameScheduler);
    }

    public Set<GameScheduler<?>> getGameSchedulers() {
        return Collections.unmodifiableSet(gameSchedulers);
    }
}