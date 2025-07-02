package steyn91.kitPvP.models;

import org.bukkit.entity.Entity;

public class ProjectileModel {
    private final Entity projectile;
    private final PlayerModel playerModel;
    private PlayerModel projectileSource;
    private final Double projectileDamage;
    // модель игрока как источник урона, для удобства
    public ProjectileModel(Entity projectile, PlayerModel playerModel, Double projectileDamage) {
        this.projectile = projectile;
        this.playerModel = playerModel;
        this.projectileDamage = projectileDamage;
    }

    public Entity getProjectile() {
        return projectile;
    }
    public PlayerModel getPlayerModel() {return playerModel;}
    public PlayerModel getProjectileSource() {return projectileSource;}
    public void setProjectileSource(PlayerModel projectileSource) {this.projectileSource = projectileSource;}
    public Double getProjectileDamage() {
        return projectileDamage;
    }


}
