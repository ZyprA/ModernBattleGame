package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.BattleGameScheduler;
import net.zypr.modernBattleGame.internal.BattlePhaseScheduler;
import net.zypr.modernBattleGame.internal.EventStreamer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BattleGameExecutor<P extends GamePlayer, T extends BattleGame<P>> {
    private final BattleGameScheduler<P, T> battleGameScheduler;
    private final T battleGame;

    public BattleGameExecutor(T battleGame, JavaPlugin plugin, List<BattlePhase<P, T>> battlePhaseList) {
        EventStreamer.on(plugin);
        this.battleGame = battleGame;
        this.battleGameScheduler = new BattleGameScheduler<>(battleGame, plugin, new BattlePhaseScheduler<>(battleGame, battlePhaseList));
    }

    public void start() {
        if (battleGameScheduler.isRunningGame()) {
            Bukkit.getLogger().info("GAME IS ALREADY RUNNING. WE CANT START YET.");
            return;
        }
        this.battleGameScheduler.start();
    }

    public T getGameInstance() {
        return this.battleGame;
    }
}
