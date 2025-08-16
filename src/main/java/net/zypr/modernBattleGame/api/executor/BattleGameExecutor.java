package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.phase.GamePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.GameScheduler;
import net.zypr.modernBattleGame.internal.GamePhaseScheduler;
import net.zypr.modernBattleGame.internal.EventStreamer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class BattleGameExecutor<P extends GamePlayer, T extends GameInstance<P>> {
    private final GameScheduler<P, T> gameScheduler;
    private final T gameInstance;

    public BattleGameExecutor(T gameInstance, JavaPlugin plugin, List<GamePhase<P, T>> gamePhaseList) {
        EventStreamer.on(plugin);
        this.gameInstance = gameInstance;
        this.gameScheduler = new GameScheduler<>(gameInstance, plugin, new GamePhaseScheduler<>(gameInstance, gamePhaseList));
    }

    public boolean start() {
        if (gameScheduler.isRunningGame()) {
            return false;
        }
        this.gameScheduler.start();
        return true;
    }

    public T getGameInstance() {
        return this.gameInstance;
    }

    public GameScheduler<P, T> getGameScheduler() {
        return this.gameScheduler;
    }

    public GamePhaseScheduler<P, T> getGamePhaseScheduler() {
        return this.gameScheduler.getGamePhaseScheduler();
    }

    public GamePhase<P, T> getPhase() {
        return this.gameScheduler.getGamePhaseScheduler().getPhase();
    }


}
