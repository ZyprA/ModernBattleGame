package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public final class SimpleGameTeam implements GameTeam {
    private final String name;
    private final Component displayName;
    private final NamedTextColor color;

    public SimpleGameTeam(String name, Component displayName, NamedTextColor color) {
        this.name = name;
        this.displayName = displayName;
        this.color = color;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return displayName;
    }


    @Override
    public @NotNull NamedTextColor getColor() {
        return color;
    }
}
