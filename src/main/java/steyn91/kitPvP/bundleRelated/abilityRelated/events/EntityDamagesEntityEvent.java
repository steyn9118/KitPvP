package steyn91.kitPvP.bundleRelated.abilityRelated.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import steyn91.kitPvP.mechanicsRelated.DamageType;
import steyn91.kitPvP.models.LivingEntityModel;

public class EntityDamagesEntityEvent extends Event {

    @Getter private final LivingEntityModel source;
    @Getter private final LivingEntityModel target;
    @Getter private final double damageAmount;
    @Getter private final DamageType type;

    public EntityDamagesEntityEvent(LivingEntityModel source, LivingEntityModel target, double damageAmount, DamageType type){
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
