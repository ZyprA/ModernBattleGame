package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public class BattlePhaseScheduler<T extends BattleGame<?>>{
    private final T battleGame;
    private final List<BattlePhase<T>> battlePhases;
    private int counter;
    private boolean isInit = true;

    public BattlePhaseScheduler(T battleGame, List<BattlePhase<T>> battlePhases) {
        this.battleGame = battleGame;
        this.battlePhases = battlePhases;
        counter = 0;
    }

    public boolean execute() {
        BattlePhase<T> battlePhase = battlePhases.get(counter);
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
        battlePhase.timer().update();
        return false;
    }


    public BattlePhase<T> getPhase() {
        return this.battlePhases.get(counter);
    }

    public boolean isTerminated() {
        return (battlePhases.size() <= counter);
    }

    protected List<GamePlayer> getGamePlayers() {return List.of((GamePlayer) battleGame.getGamePlayers());}

}
