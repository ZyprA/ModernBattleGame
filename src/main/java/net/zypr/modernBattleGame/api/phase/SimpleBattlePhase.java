package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.Timer;
import java.util.function.Consumer;
import java.util.function.Function;

public final class SimpleBattlePhase<T extends GamePlayer> implements BattlePhase<T> {
    private final Consumer<BattleGame<T>> initExecution;
    private final Function<BattleGame<T>, Boolean> loopExecution;
    private final Timer timer;

    public SimpleBattlePhase(Consumer<BattleGame<T>> initExecution, Function<BattleGame<T>, Boolean> loopExecution,
                             Timer timer) {
        this.initExecution = initExecution;
        this.loopExecution = loopExecution;
        this.timer = timer;
    }

    @Override
    public Consumer<BattleGame<T>> getInitialExecution() {
        return initExecution;
    }

    @Override
    public Function<BattleGame<T>, Boolean> getExecution() {
        return loopExecution;
    }

    public Consumer<BattleGame<T>> initExecution() {
        return initExecution;
    }

    public Function<BattleGame<T>, Boolean> loopExecution() {
        return loopExecution;
    }

    @Override
    public Timer timer() {
        return timer;
    }


}
