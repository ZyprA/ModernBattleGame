package net.zypr.modernBattleGame.api.event;

import com.google.common.base.Function;
import net.zypr.modernBattleGame.api.player.GamePlayer;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class GamePlayerDamageEvent extends EntityDamageByEntityEvent implements ModernEvent<List<? extends GamePlayer>>{
    public GamePlayerDamageEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull DamageCause cause, @NotNull DamageSource damageSource, @NotNull Map<DamageModifier, Double> modifiers, @NotNull Map<DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions, boolean critical) {
        super(damager, damagee, cause, damageSource, modifiers, modifierFunctions, critical);
    }


    @Override
    public boolean isValid(List<? extends GamePlayer> gamePlayers) {
        return false;
    }
}
