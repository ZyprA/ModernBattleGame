package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.phase.GamePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public class GamePhaseScheduler<T extends GameInstance<?>>{
    private final T battleGame;
    private GamePhase<T> gamePhase;
    private boolean isInit = true;

    public GamePhaseScheduler(T battleGame, GamePhase<T> firstPhase) {
        this.battleGame = battleGame;
        this.gamePhase = firstPhase;
    }

    public boolean execute() {
        if (isInit) {
            gamePhase.getInitialExecution().accept(battleGame);
            isInit = false;
        }
        GamePhase<T> nextGamePhase = gamePhase.getExecution().apply(battleGame);
        if (!nextGamePhase.equals(gamePhase)) {
            gamePhase = nextGamePhase;
            isInit = true;
        }
        gamePhase.timer().addTick(battleGame.getGameTick());
        gamePhase.timer().updateClock();
        return false;
    }


    public GamePhase<T> getPhase() {
        return this.gamePhase;
    }

    public boolean isTerminated() {
        return (gamePhase == null);
    }

}
