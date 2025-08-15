package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface GameTeam {
    @NotNull
    static SimpleGameTeam of(String name, Component displayName, ChatColor color) {
        return new SimpleGameTeam(name, displayName, color);
    }

    @Nullable
    static ColorGameTeam color(String name) {
        return Arrays.stream(ColorGameTeam.values())
                .parallel()
                .filter(team -> team.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @NotNull String getName();

    @NotNull Component getDisplayName();

    @NotNull ChatColor getColor();

}
