package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.GameInstance;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScheduler<T extends GameInstance<?>> {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private final T gameInstance;
    private boolean isRunning = false;
    private final GamePhaseScheduler<T> gamePhaseScheduler;


    public GameScheduler(T gameInstance, JavaPlugin plugin, GamePhaseScheduler<T> gamePhaseScheduler) {
        this.plugin = plugin;
        this.gameInstance = gameInstance;
        this.gamePhaseScheduler = gamePhaseScheduler;
    }

    public void start() {
        isRunning = true;
        EventStreamer.register(this);
        int duration = gameInstance.getGameTick();
        this.taskId = new BukkitRunnable() {
            @Override
            public void run() {
                gamePhaseScheduler.execute();
                gameInstance.getTimer().addTick(duration);
                gameInstance.getTimer().updateClock();
                if (gamePhaseScheduler.isTerminated()) stop();
            }
        }.runTaskTimer(plugin, 0L, duration).getTaskId();
    }

    public void stop() {
        if (Bukkit.getScheduler().isCurrentlyRunning(this.taskId)) {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
        EventStreamer.unregister(this);
        this.gameInstance.onGameEnd();
        isRunning = false;
    }

    public boolean isRunningGame() {
        return isRunning;
    }

    public GamePhaseScheduler<T> getGamePhaseScheduler() {
        return this.gamePhaseScheduler;
    }

    public T getGameInstance() {return this.gameInstance;}
}
