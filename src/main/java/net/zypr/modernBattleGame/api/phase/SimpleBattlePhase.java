package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.function.Consumer;
import java.util.function.Function;

public final class SimpleBattlePhase<T extends BattleGame<?>> implements BattlePhase<T> {
    private final Consumer<T> initExecution;
    private final Function<T, Boolean> loopExecution;
    private final Timer timer;

    public SimpleBattlePhase(Consumer<T> initExecution, Function<T, Boolean> loopExecution,
                             Timer timer) {
        this.initExecution = initExecution;
        this.loopExecution = loopExecution;
        this.timer = timer;
    }

    @Override
    public Consumer<T> getInitialExecution() {
        return initExecution;
    }

    @Override
    public Function<T, Boolean> getExecution() {
        return loopExecution;
    }

    @Override
    public Timer timer() {
        return timer;
    }

}
