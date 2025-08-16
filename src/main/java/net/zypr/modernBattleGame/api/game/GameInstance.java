package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.internal.Timer;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface GameInstance<T extends GamePlayer> {

    static GameInstance<GamePlayer> of(Timer timer, List<GamePlayer> gamePlayers, Runnable gameTerminatedExecution, JavaPlugin plugin) {
        return new SimpleGameInstance<>(timer, gamePlayers, gameTerminatedExecution, plugin);
    }


    List<T> getGamePlayers();
    Timer getTimer();
    int getGameTick();
    void onGameEnd();
    JavaPlugin getPlugin();
}
