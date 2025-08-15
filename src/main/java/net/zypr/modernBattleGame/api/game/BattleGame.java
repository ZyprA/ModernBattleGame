package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.internal.BattlePhaseScheduler;
import net.zypr.modernBattleGame.internal.Timer;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public interface BattleGame<T extends GamePlayer> {

    static BattleGame<GamePlayer> of(String name, List<BattlePhase<SimpleBattleGame>> battlePhaseList, Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution) {
        return new SimpleBattleGame(name, battlePhaseList, timer, gamePlayers, gameTerminatedExecution);
    }


    String getName();
    List<T> getGamePlayers();
    Timer getTimer();
    BattlePhaseScheduler<? extends BattleGame<T>> getBattlePhaseScheduler();
    void onGameEnd();
}
