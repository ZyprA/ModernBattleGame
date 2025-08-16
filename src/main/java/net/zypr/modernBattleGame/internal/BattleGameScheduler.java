package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BattleGameScheduler<P extends GamePlayer, T extends BattleGame<P>> {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private final T battleGame;
    private boolean isRunning = false;
    private final BattlePhaseScheduler<P, T> battlePhaseScheduler;


    public BattleGameScheduler(T battleGame, JavaPlugin plugin, BattlePhaseScheduler<P, T> battlePhaseScheduler) {
        this.plugin = plugin;
        this.battleGame = battleGame;
        this.battlePhaseScheduler = battlePhaseScheduler;
    }

    public void start() {
        isRunning = true;
        EventStreamer.register(battlePhaseScheduler);
        int duration = battleGame.getGameTick();
        this.taskId = new BukkitRunnable() {
            @Override
            public void run() {
                battlePhaseScheduler.execute();
                battleGame.getTimer().addTick(duration);
                battleGame.getTimer().updateClock();
                if (battlePhaseScheduler.isTerminated()) stop();
            }
        }.runTaskTimer(plugin, 0L, duration).getTaskId();
    }

    public void stop() {
        if (Bukkit.getScheduler().isCurrentlyRunning(this.taskId)) {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
        EventStreamer.unregister(battlePhaseScheduler);
        this.battleGame.onGameEnd();
        isRunning = false;
    }

    public boolean isRunningGame() {
        return battlePhaseScheduler.isTerminated();
    }

    public BattlePhaseScheduler<P,T> getBattlePhaseScheduler() {
        return this.battlePhaseScheduler;
    }
}
