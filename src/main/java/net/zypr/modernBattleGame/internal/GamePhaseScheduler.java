package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.phase.GamePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public class GamePhaseScheduler<P extends GamePlayer, T extends GameInstance<P>>{
    private final T battleGame;
    private final List<GamePhase<P, T>> gamePhases;
    private int counter;
    private boolean isInit = true;

    public GamePhaseScheduler(T battleGame, List<GamePhase<P, T>> gamePhases) {
        this.battleGame = battleGame;
        this.gamePhases = gamePhases;
        counter = 0;
    }

    public boolean execute() {
        GamePhase<P, T> gamePhase = gamePhases.get(counter);
        if (isInit) {
            gamePhase.getInitialExecution().accept(battleGame);
            isInit = false;
        }
        if (!isTerminated() && gamePhase.getExecution().apply(battleGame)) {
            if (isTerminated()) {
                return true;
            }
            isInit = true;
            counter++;
        }
        gamePhase.timer().addTick(battleGame.getGameTick());
        gamePhase.timer().updateClock();
        return false;
    }


    public GamePhase<P, T> getPhase() {
        return this.gamePhases.get(counter);
    }

    public boolean isTerminated() {
        return (gamePhases.size() <= counter);
    }

    protected List<P> getGamePlayers() {return battleGame.getGamePlayers();}

}
