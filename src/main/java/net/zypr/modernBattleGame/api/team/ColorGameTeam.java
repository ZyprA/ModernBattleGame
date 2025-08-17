package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;


public enum ColorGameTeam implements GameTeam {
    RED("red", NamedTextColor.RED, NamedTextColor.RED),
    BLUE("blue", NamedTextColor.BLUE, NamedTextColor.BLUE),
    GREEN("green", NamedTextColor.GREEN, NamedTextColor.GREEN),
    YELLOW("yellow", NamedTextColor.YELLOW, NamedTextColor.YELLOW),
    AQUA("aqua", NamedTextColor.AQUA, NamedTextColor.AQUA),
    WHITE("white", NamedTextColor.WHITE, NamedTextColor.WHITE),
    BLACK("black", NamedTextColor.BLACK, NamedTextColor.BLACK),
    PURPLE("purple", NamedTextColor.DARK_PURPLE, NamedTextColor.DARK_PURPLE),
    PINK("pink", NamedTextColor.LIGHT_PURPLE, NamedTextColor.LIGHT_PURPLE),
    ORANGE("orange", NamedTextColor.GOLD, NamedTextColor.GOLD),
    GRAY("gray", NamedTextColor.GRAY, NamedTextColor.GRAY);


    private final String name;
    private final Component displayName;
    private final NamedTextColor color;

    ColorGameTeam(String name, NamedTextColor displayColor, NamedTextColor color) {
        this.name = name;
        this.displayName = Component.text(name.toUpperCase(), displayColor);
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
