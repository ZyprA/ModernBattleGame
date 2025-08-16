package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public class BattlePhaseScheduler<P extends GamePlayer, T extends BattleGame<P>>{
    private final T battleGame;
    private final List<BattlePhase<P, T>> battlePhases;
    private int counter;
    private boolean isInit = true;

    public BattlePhaseScheduler(T battleGame, List<BattlePhase<P, T>> battlePhases) {
        this.battleGame = battleGame;
        this.battlePhases = battlePhases;
        counter = 0;
    }

    public boolean execute() {
        BattlePhase<P, T> battlePhase = battlePhases.get(counter);
        if (isInit) {
            battlePhase.getInitialExecution().accept(battleGame);
            isInit = false;
        }
        if (!isTerminated() && battlePhase.getExecution().apply(battleGame)) {
            if (isTerminated()) {
                return true;
            }
            isInit = true;
            counter++;
        }
        battlePhase.timer().addTick(battleGame.getGameTick());
        battlePhase.timer().updateClock();
        return false;
    }


    public BattlePhase<P, T> getPhase() {
        return this.battlePhases.get(counter);
    }

    public boolean isTerminated() {
        return (battlePhases.size() <= counter);
    }

    protected List<P> getGamePlayers() {return battleGame.getGamePlayers();}

}
