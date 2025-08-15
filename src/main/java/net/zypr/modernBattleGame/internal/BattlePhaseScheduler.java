package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public class BattlePhaseScheduler{
    private final BattleGame battleGame;
    private final List<BattlePhase> battlePhases;
    private int counter;
    private boolean isInit = true;

    public BattlePhaseScheduler(BattleGame battleGame) {
        this.battlePhases = battleGame.getBattlePhaseList();
        this.battleGame = battleGame;
        counter = 0;
    }

    public boolean execute() {
        BattlePhase battlePhase = battlePhases.get(counter);
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

    public BattlePhase getPhase() {
        return this.battlePhases.get(counter);
    }

    public boolean isTerminated() {
        return (battlePhases.size() <= counter);
    }

}
