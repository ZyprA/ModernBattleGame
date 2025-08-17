package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.GameControllable;
import net.zypr.modernBattleGame.api.game.GameInstance;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScheduler<T extends GameInstance<?>> {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private T gameInstance;
    private boolean isRunning = false;
    private GamePhaseScheduler<T> gamePhaseScheduler;
    private EventStreamer eventStreamer;
    private GameControllable gameControllable;


    public GameScheduler(T gameInstance, JavaPlugin plugin, GamePhaseScheduler<T> gamePhaseScheduler, EventStreamer eventStreamer) {
        this.plugin = plugin;
        this.gameInstance = gameInstance;
        this.gamePhaseScheduler = gamePhaseScheduler;
        this.eventStreamer = eventStreamer;
    }

    public GameScheduler(T gameInstance, JavaPlugin plugin, GamePhaseScheduler<T> gamePhaseScheduler, EventStreamer eventStreamer, GameControllable gameControllable) {
        this.plugin = plugin;
        this.gameInstance = gameInstance;
        this.gamePhaseScheduler = gamePhaseScheduler;
        this.eventStreamer = eventStreamer;
        this.gameControllable = gameControllable;
    }

    public void start() {
        isRunning = true;
        eventStreamer.register(this);
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
        Bukkit.getScheduler().cancelTask(this.taskId);

        eventStreamer.unregister(this);
        isRunning = false;

        gameInstance = null;
        gamePhaseScheduler = null;
        eventStreamer = null;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (gameControllable != null) gameControllable.onGameEnd();
            }
        }.runTaskLater(plugin, 5);
    }

    public boolean isRunningGame() {
        return isRunning;
    }

    public GamePhaseScheduler<T> getGamePhaseScheduler() {
        return this.gamePhaseScheduler;
    }

    public T getGameInstance() {return this.gameInstance;}
}
