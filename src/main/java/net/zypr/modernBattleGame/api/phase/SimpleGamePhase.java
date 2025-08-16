package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.internal.Timer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class SimpleGamePhase<T extends GameInstance<?>> implements GamePhase<T> {
    private final Consumer<T> initExecution;
    private final Function<T, GamePhase<T>> loopExecution;
    private final Timer timer;

    public SimpleGamePhase(Consumer<T> initExecution, Function<T, GamePhase<T>> loopExecution,
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
    public Function<T, GamePhase<T>> getExecution() {
        return loopExecution;
    }

    @Override
    public Timer timer() {
        return timer;
    }

}
