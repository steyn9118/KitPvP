package steyn91.kitPvP.models;

import lombok.Getter;
import org.bukkit.entity.Entity;
import steyn91.kitPvP.mechanicsRelated.DamageType;
import steyn91.kitPvP.models.propertiesRelated.Property;

public class LivingEntityModel implements Alive {
    @Getter private final Entity entity;
    private final PlayerModel owner;
    private final Property entityDamage;
    private final int maxTimeAlive;

    public LivingEntityModel(Entity entity, PlayerModel owner, Double entityDamage, int maxTimeAlive) {
        this.entity = entity;
        this.owner = owner;
        this.entityDamage = new Property(entityDamage);
        this.maxTimeAlive = maxTimeAlive;
    }

    @Override
    public boolean takeDamage(double damageAmount, DamageType type) {
        // TODO получение урона сущностью
        return false;
    }

    @Override
    public void heal(double healAmount) {
        // TODO лечение сущности
    }
}
