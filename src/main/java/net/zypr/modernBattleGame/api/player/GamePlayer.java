package net.zypr.modernBattleGame.api.player;

import org.bukkit.entity.Player;

public interface GamePlayer {
    Player getPlayer();
    BattleStatus getBattleStatus();
}
