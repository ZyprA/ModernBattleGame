package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.BattleGameScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleGameExecutor {
    private final BattleGameScheduler battleGameScheduler;
    private final BattleGame<?> battleGame;

    public BattleGameExecutor(BattleGame<?> battleGame, JavaPlugin plugin) {
        this.battleGame = battleGame;
        this.battleGameScheduler = new BattleGameScheduler(battleGame, plugin);
    }

    public void start() {
        this.battleGameScheduler.start();
    }

    public BattleGame<?> getGameInstance() {
        return this.battleGame;
    }
}
