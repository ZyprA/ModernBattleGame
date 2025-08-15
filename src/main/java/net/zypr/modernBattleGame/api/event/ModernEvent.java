package net.zypr.modernBattleGame.api.event;

import org.bukkit.event.Event;

import java.util.function.Function;

public interface ModernEvent<T>{
    boolean isValid(T t);
}
