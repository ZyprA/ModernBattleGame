package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.function.Consumer;
import java.util.function.Function;

public record SimpleBattlePhase(Consumer<BattleGame> initExecution, Function<BattleGame, Boolean> loopExecution,
                                Timer timer) implements BattlePhase {

    @Override
    public Consumer<BattleGame> getInitialExecution() {
        return initExecution;
    }

    @Override
    public Function<BattleGame, Boolean> getExecution() {
        return loopExecution;
    }
}
