package steyn91.kitPvP.models;

import lombok.Getter;
import org.bukkit.entity.Entity;

public class LivingEntityModel {
    @Getter
    private final Entity entity;
    private final PlayerModel playerModel;
    private final Double entityDamage;
    private final int maxTimeAlive;

    public LivingEntityModel(Entity entity, PlayerModel playerModel, Double entityDamage, int maxTimeAlive) {
        this.entity = entity;
        this.playerModel = playerModel;
        this.entityDamage = entityDamage;
        this.maxTimeAlive = maxTimeAlive;
    }

}
