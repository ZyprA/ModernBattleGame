package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BattleGameScheduler {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private final BattleGame<? extends GamePlayer> battleGame;


    public BattleGameScheduler(BattleGame<? extends GamePlayer> battleGame, JavaPlugin plugin) {
        this.plugin = plugin;
        this.battleGame = battleGame;
    }

    public void start() {
        int duration = battleGame.getTimer().getDuration();
        this.taskId = new BukkitRunnable() {
            @Override
            public void run() {
                battleGame.getBattlePhaseScheduler().execute();
                battleGame.getTimer().update();
                if (battleGame.getBattlePhaseScheduler().isTerminated()) stop();
            }
        }.runTaskTimer(plugin, 0L, duration).getTaskId();
    }

    public void stop() {
        if (Bukkit.getScheduler().isCurrentlyRunning(this.taskId)) {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
        this.battleGame.onGameEnd();
    }

    public boolean isRunning() {
        return (Bukkit.getScheduler().isCurrentlyRunning(this.taskId));
    }
}
