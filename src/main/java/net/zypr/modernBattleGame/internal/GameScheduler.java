package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScheduler<P extends GamePlayer, T extends GameInstance<P>> {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private final T battleGame;
    private boolean isRunning = false;
    private final GamePhaseScheduler<P, T> gamePhaseScheduler;


    public GameScheduler(T battleGame, JavaPlugin plugin, GamePhaseScheduler<P, T> gamePhaseScheduler) {
        this.plugin = plugin;
        this.battleGame = battleGame;
        this.gamePhaseScheduler = gamePhaseScheduler;
    }

    public void start() {
        isRunning = true;
        EventStreamer.register(gamePhaseScheduler);
        int duration = battleGame.getGameTick();
        this.taskId = new BukkitRunnable() {
            @Override
            public void run() {
                gamePhaseScheduler.execute();
                battleGame.getTimer().addTick(duration);
                battleGame.getTimer().updateClock();
                if (gamePhaseScheduler.isTerminated()) stop();
            }
        }.runTaskTimer(plugin, 0L, duration).getTaskId();
    }

    public void stop() {
        if (Bukkit.getScheduler().isCurrentlyRunning(this.taskId)) {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
        EventStreamer.unregister(gamePhaseScheduler);
        this.battleGame.onGameEnd();
        isRunning = false;
    }

    public boolean isRunningGame() {
        return gamePhaseScheduler.isTerminated();
    }

    public GamePhaseScheduler<P,T> getGamePhaseScheduler() {
        return this.gamePhaseScheduler;
    }
}
