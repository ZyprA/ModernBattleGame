package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.BattleGameScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public class ModernBattleGameExecutor {
    private final BattleGameScheduler battleGameScheduler;

    public ModernBattleGameExecutor(BattleGame<?> battleGame, JavaPlugin plugin) {
        this.battleGameScheduler = new BattleGameScheduler(battleGame, plugin);
    }

    public void start() {
        this.battleGameScheduler.start();
    }
}
