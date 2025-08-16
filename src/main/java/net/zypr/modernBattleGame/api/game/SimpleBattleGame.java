package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.api.phase.BattlePhase;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.BattlePhaseScheduler;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.List;

public class SimpleBattleGame<T extends GamePlayer> implements BattleGame<T> {


    private final String name;
    private final Timer timer;
    private final BattlePhaseScheduler<SimpleBattleGame<T>> battleGameScheduler;
    private final List<T> gamePlayers;
    private final Runnable gameTerminatedExecution;

    public SimpleBattleGame(String name, List<BattlePhase<SimpleBattleGame<T>>> battlePhases, Timer timer, List<T> gamePlayers, Runnable gameTerminatedExecution) {
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
    public BattlePhaseScheduler<? extends BattleGame<T>> getBattlePhaseScheduler() {
        return battleGameScheduler;
    }

    @Override
    public int getGameTick() {
        return 5;
    }

    @Override
    public List<T> getGamePlayers() {
        return List.copyOf(gamePlayers);
    }

    @Override
    public void onGameEnd() {
        gameTerminatedExecution.run();
    }
}
