package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.internal.BattlePhaseScheduler;
import net.zypr.modernBattleGame.internal.Timer;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public interface BattleGame {

    static BattleGame of(String name, List<BattlePhase> battlePhaseList, Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution) {
        return new SimpleBattleGame(name, battlePhaseList, timer, gamePlayers, gameTerminatedExecution);
    }


    String getName();
    List<GamePlayer> getGamePlayers();
    Timer getTimer();
    List<BattlePhase> getBattlePhaseList();
    BattlePhaseScheduler getBattlePhaseScheduler();
    void onGameEnd();
}
