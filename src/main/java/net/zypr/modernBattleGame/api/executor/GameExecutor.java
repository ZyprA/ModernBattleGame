package net.zypr.modernBattleGame.api.executor;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.phase.GamePhase;
import net.zypr.modernBattleGame.internal.GameScheduler;
import net.zypr.modernBattleGame.internal.GamePhaseScheduler;
import net.zypr.modernBattleGame.internal.EventStreamer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GameExecutor<T extends GameInstance<?>> {

    private final GameScheduler<T> gameScheduler;
    private final T gameInstance;

    public GameExecutor(T gameInstance, JavaPlugin plugin, List<GamePhase<T>> gamePhaseList) {
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

    public GameScheduler<T> getGameScheduler() {
        return this.gameScheduler;
    }

    public GamePhaseScheduler<T> getGamePhaseScheduler() {
        return this.gameScheduler.getGamePhaseScheduler();
    }

    public GamePhase<T> getPhase() {
        return this.gameScheduler.getGamePhaseScheduler().getPhase();
    }

    public EventStreamer getEventStreamer() {return new EventStreamer(); }


}
