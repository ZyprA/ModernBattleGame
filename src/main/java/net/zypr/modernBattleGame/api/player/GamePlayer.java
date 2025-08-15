package net.zypr.modernBattleGame.api.player;

import org.bukkit.entity.Player;

public interface GamePlayer {
    Player player();

    BattleStatus battleStatus();
}
