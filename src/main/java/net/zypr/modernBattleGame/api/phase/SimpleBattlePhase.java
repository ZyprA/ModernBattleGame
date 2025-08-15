package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.BattleGame;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.Timer;

import java.util.function.Consumer;
import java.util.function.Function;

public record SimpleBattlePhase<T extends GamePlayer>(Consumer<BattleGame<T>> initExecution,
                                                      Function<BattleGame<T>, Boolean> loopExecution,
                                                      Timer timer) implements BattlePhase<T> {

    @Override
    public Consumer<BattleGame<T>> getInitialExecution() {
        return initExecution;
    }

    @Override
    public Function<BattleGame<T>, Boolean> getExecution() {
        return loopExecution;
    }


}
