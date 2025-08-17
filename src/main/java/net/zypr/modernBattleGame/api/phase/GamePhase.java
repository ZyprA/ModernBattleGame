package net.zypr.modernBattleGame.api.phase;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.internal.Timer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public interface GamePhase<T extends GameInstance<?>> {

    Timer timer();

    Consumer<T> getInitialExecution();
    Function<T, @Nullable GamePhase<T>> getExecution();
}
