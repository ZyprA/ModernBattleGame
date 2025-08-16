package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.BattleGameScheduler;
import net.zypr.modernBattleGame.internal.EventStreamer;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleGameExecutor<T extends BattleGame<?>> {
    private final BattleGameScheduler battleGameScheduler;
    private final T battleGame;

    public BattleGameExecutor(T battleGame, JavaPlugin plugin) {
        EventStreamer.on(plugin);
        this.battleGame = battleGame;
        this.battleGameScheduler = new BattleGameScheduler(battleGame, plugin);
    }

    public void start() {
        this.battleGameScheduler.start();
    }

    public T getGameInstance() {
        return this.battleGame;
    }
}
