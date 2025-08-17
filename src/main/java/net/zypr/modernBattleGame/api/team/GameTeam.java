package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface GameTeam {
    @NotNull String getName();
    @NotNull Component getDisplayName();
    @NotNull NamedTextColor getColor();

    @NotNull static SimpleGameTeam of(String name, Component displayName, NamedTextColor color) {
        return new SimpleGameTeam(name, displayName, color);
    }

    @Nullable static ColorGameTeam color(String name) {
        return Arrays.stream(ColorGameTeam.values())
                .parallel()
                .filter(team -> team.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

}
