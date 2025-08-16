package net.zypr.modernBattleGame.api.event;


public interface ModernEvent<T>{
    boolean isValid(T t);
}
