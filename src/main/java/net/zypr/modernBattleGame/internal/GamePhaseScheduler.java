package net.zypr.modernBattleGame.internal;

import net.zypr.modernBattleGame.api.game.GameInstance;
import net.zypr.modernBattleGame.api.phase.GamePhase;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GamePhaseScheduler<T extends GameInstance<?>>{
    private final T battleGame;
    private GamePhase<T> gamePhase;
    private boolean isInit = true;
    private boolean isTerminated = false;
    private JavaPlugin plugin;
    private List<Listener> listeners = new ArrayList<>();

    public GamePhaseScheduler(T battleGame, GamePhase<T> firstPhase, JavaPlugin plugin) {
        this.battleGame = battleGame;
        this.gamePhase = firstPhase;
        this.plugin = plugin;
    }

    public void execute() {
        if (isInit) {
            if (gamePhase.getListeners() != null) {
                listeners = gamePhase.getListeners().apply(battleGame);
                listeners.forEach(listener -> plugin.getServer().getPluginManager().registerEvents(listener, plugin));
            }
            if (gamePhase.getInitialExecution() != null) {
                gamePhase.getInitialExecution().accept(battleGame);
            }
            isInit = false;
        }
        if (gamePhase.getExecution() == null) {
            listeners.forEach(HandlerList::unregisterAll);
            isTerminated = true;
            return;
        }
        GamePhase<T> nextGamePhase = gamePhase.getExecution().apply(battleGame);
        if (nextGamePhase == null) {
            listeners.forEach(HandlerList::unregisterAll);
            isTerminated = true;
            return;
        }
        if (!nextGamePhase.equals(gamePhase)) {
            listeners.forEach(HandlerList::unregisterAll);
            gamePhase = nextGamePhase;
            isInit = true;
        }
        gamePhase.timer().addTick(battleGame.getGameTick());
        gamePhase.timer().updateClock();
    }


    public GamePhase<T> getPhase() {
        return this.gamePhase;
    }

    public boolean isTerminated() {
        return isTerminated;
    }

}
