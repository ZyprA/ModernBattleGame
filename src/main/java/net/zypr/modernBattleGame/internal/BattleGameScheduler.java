package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BattleGameScheduler {
    private int taskId = -1;
    private final JavaPlugin plugin;
    private final BattleGame<?> battleGame;
    private boolean isRunning = false;


    public BattleGameScheduler(BattleGame<?> battleGame, JavaPlugin plugin) {
        this.plugin = plugin;
        this.battleGame = battleGame;
    }

    public void start() {
        isRunning = true;
        EventStreamer.register(battleGame.getBattlePhaseScheduler());
        int duration = battleGame.getGameTick();
        this.taskId = new BukkitRunnable() {
            @Override
            public void run() {
                battleGame.getBattlePhaseScheduler().execute();
                battleGame.getTimer().addTick(duration);
                battleGame.getTimer().updateClock();
                if (battleGame.getBattlePhaseScheduler().isTerminated()) stop();
            }
        }.runTaskTimer(plugin, 0L, duration).getTaskId();
    }

    public void stop() {
        if (Bukkit.getScheduler().isCurrentlyRunning(this.taskId)) {
            Bukkit.getScheduler().cancelTask(this.taskId);
        }
        EventStreamer.unregister(battleGame.getBattlePhaseScheduler());
        this.battleGame.onGameEnd();
        isRunning = false;
    }

    public boolean isRunningGame() {
        return battleGame.getBattlePhaseScheduler().isTerminated();
    }
}
