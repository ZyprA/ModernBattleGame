package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.BattlePhaseScheduler;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.List;

public class SimpleBattleGame implements BattleGame {


    private final String name;
    private final Timer timer;
    private final BattlePhaseScheduler<SimpleBattleGame> battleGameScheduler;
    private final List<GamePlayer> gamePlayers;
    private final Runnable gameTerminatedExecution;

    public SimpleBattleGame(String name, List<BattlePhase<SimpleBattleGame>> battlePhases, Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution) {
        this.name = name;
        this.battleGameScheduler = new BattlePhaseScheduler<>(this, battlePhases);
        this.timer = timer;
        this.gamePlayers = gamePlayers;
        this.gameTerminatedExecution = gameTerminatedExecution;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }

    @Override
    public BattlePhaseScheduler<SimpleBattleGame> getBattlePhaseScheduler() {
        return battleGameScheduler;
    }

    @Override
    public List<GamePlayer> getGamePlayers() {
        return List.copyOf(gamePlayers);
    }

    @Override
    public void onGameEnd() {
        gameTerminatedExecution.run();
    }
}
