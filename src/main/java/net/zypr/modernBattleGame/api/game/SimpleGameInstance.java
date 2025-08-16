package net.zypr.modernBattleGame.api.game;

import net.zypr.modernBattleGame.api.player.GamePlayer;
import net.zypr.modernBattleGame.internal.Timer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class SimpleGameInstance<T extends GamePlayer> implements GameInstance<T> {


    private final Timer timer;
    private final List<T> gamePlayers;
    private final Runnable gameTerminatedExecution;
    private final JavaPlugin plugin;


    public SimpleGameInstance(Timer timer, List<T> gamePlayers, Runnable gameTerminatedExecution, JavaPlugin plugin) {
        this.timer = timer;
        this.gamePlayers = gamePlayers;
        this.gameTerminatedExecution = gameTerminatedExecution;
        this.plugin = plugin;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }


    @Override
    public int getGameTick() {
        return 5;
    }

    @Override
    public List<T> getGamePlayers() {
        return List.copyOf(gamePlayers);
    }

    @Override
    public void onGameEnd() {
        gameTerminatedExecution.run();
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }
}
