package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.GameControllable;
import net.zypr.modernBattleGame.api.game.GameInstance;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScheduler<T extends GameInstance<?>> {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private T gameInstance;
    private boolean isRunning = false;
    private GamePhaseScheduler<T> gamePhaseScheduler;
    private GameControllable gameControllable;


    public GameScheduler(T gameInstance, JavaPlugin plugin, GamePhaseScheduler<T> gamePhaseScheduler) {
        this.plugin = plugin;
        this.gameInstance = gameInstance;
        this.gamePhaseScheduler = gamePhaseScheduler;
    }

    public GameScheduler(T gameInstance, JavaPlugin plugin, GamePhaseScheduler<T> gamePhaseScheduler, GameControllable gameControllable) {
        this.plugin = plugin;
        this.gameInstance = gameInstance;
        this.gamePhaseScheduler = gamePhaseScheduler;
        this.gameControllable = gameControllable;
    }

    public void start() {
        isRunning = true;
        int duration = gameInstance.getGameTick();
        if (gameInstance instanceof Listener listener) {
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
        }
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

        isRunning = false;

        if (gameInstance instanceof Listener listener) {
            HandlerList.unregisterAll(listener);
        }

        gameInstance = null;
        gamePhaseScheduler = null;

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
