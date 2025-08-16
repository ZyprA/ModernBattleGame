package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.internal.Timer;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public interface GameInstance<T extends GamePlayer> {

    static GameInstance<GamePlayer> of(Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution) {
        return new SimpleGameInstance<>(timer, gamePlayers, gameTerminatedExecution);
    }


    List<T> getGamePlayers();
    Timer getTimer();
    int getGameTick();
}
