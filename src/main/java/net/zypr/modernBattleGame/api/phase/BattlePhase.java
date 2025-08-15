package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.function.Consumer;
import java.util.function.Function;

public interface BattlePhase {

    public abstract Timer timer();

    Consumer<BattleGame> getInitialExecution();
    Function<BattleGame, Boolean> getExecution();
}
