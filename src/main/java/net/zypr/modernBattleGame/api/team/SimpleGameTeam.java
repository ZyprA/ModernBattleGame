package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public record SimpleGameTeam(String name, Component displayName, ChatColor color) implements GameTeam {

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return displayName;
    }

    @Override
    public @NotNull ChatColor getColor() {
        return color;
    }
}
