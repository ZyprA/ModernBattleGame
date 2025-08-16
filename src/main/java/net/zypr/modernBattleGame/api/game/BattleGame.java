package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.internal.Timer;
import net.zypr.modernBattleGame.api.player.GamePlayer;

import java.util.List;

public interface BattleGame<T extends GamePlayer> {

    static BattleGame<GamePlayer> of(Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution) {
        return new SimpleBattleGame<>(timer, gamePlayers, gameTerminatedExecution);
    }


    List<T> getGamePlayers();
    Timer getTimer();
    int getGameTick();
    void onGameEnd();
}
