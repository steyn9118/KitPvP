package steyn91.kitPvP.bundleRelated.abilityRelated.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import steyn91.kitPvP.mechanicsRelated.DamageType;
import steyn91.kitPvP.models.LivingEntityModel;
import steyn91.kitPvP.models.PlayerModel;

public class EntityDamagesPlayerEvent extends Event {

    @Getter private final LivingEntityModel source;
    @Getter private final PlayerModel target;
    @Getter private final double damageAmount;
    @Getter private final DamageType type;

    public EntityDamagesPlayerEvent(LivingEntityModel source, PlayerModel target, double damageAmount, DamageType type){
        this.source = source;
        this.target = target;
        this.damageAmount = damageAmount;
        this.type = type;
    }

    @Getter private static final HandlerList handlerList = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

}
