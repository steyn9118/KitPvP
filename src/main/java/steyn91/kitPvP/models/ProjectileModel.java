package steyn91.kitPvP.models;

import lombok.Getter;
import org.bukkit.entity.Entity;

public class ProjectileModel {
    @Getter private final Entity projectile;
    private final PlayerModel playerModel;
    @Getter private final Double projectileDamage;
    // модель игрока как источник урона, для удобства
    public ProjectileModel(Entity projectile, PlayerModel playerModel, Double projectileDamage) {
        this.projectile = projectile;
        this.playerModel = playerModel;
        this.projectileDamage = projectileDamage;
    }
    public PlayerModel getPlayerModel() {return playerModel;}

}
