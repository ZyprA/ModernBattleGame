package net.zypr.modernBattleGame.api.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum ColorGameTeam implements GameTeam {
    RED("red", NamedTextColor.RED, ChatColor.RED),
    BLUE("blue", NamedTextColor.BLUE, ChatColor.BLUE),
    GREEN("green", NamedTextColor.GREEN, ChatColor.GREEN),
    YELLOW("yellow", NamedTextColor.YELLOW, ChatColor.YELLOW),
    AQUA("aqua", NamedTextColor.AQUA, ChatColor.AQUA),
    WHITE("white", NamedTextColor.WHITE, ChatColor.WHITE),
    BLACK("black", NamedTextColor.BLACK, ChatColor.BLACK),
    PURPLE("purple", NamedTextColor.DARK_PURPLE, ChatColor.DARK_PURPLE),
    PINK("pink", NamedTextColor.LIGHT_PURPLE, ChatColor.LIGHT_PURPLE),
    ORANGE("orange", NamedTextColor.GOLD, ChatColor.GOLD),
    GRAY("gray", NamedTextColor.GRAY, ChatColor.GRAY);


    private final String name;
    private final Component displayName;
    private final ChatColor color;

    ColorGameTeam(String name, NamedTextColor displayColor, ChatColor color) {
        this.name = name;
        this.displayName = Component.text(name.toUpperCase(), displayColor);
        this.color = color;
    }

    @Override
    public String getName() { return name; }
    @Override
    public Component getDisplayName() { return displayName; }

    @Override
    public @NotNull ChatColor getColor() {
        return color;
    }

}
