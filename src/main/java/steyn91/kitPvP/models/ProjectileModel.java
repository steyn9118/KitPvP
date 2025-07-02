package steyn91.kitPvP.models;

import org.bukkit.entity.Entity;

public class ProjectileModel {
    private final Entity projectile;
    private final PlayerModel playerModel;
    private PlayerModel projectileSource; // модель игрока как источник урона, для удобства
    public ProjectileModel(Entity projectile, PlayerModel playerModel) {
        this.projectile = projectile;
        this.playerModel = playerModel;
    }

    public Entity getProjectile() {
        return projectile;
    }
    public PlayerModel getPlayerModel() {return playerModel;}
    public PlayerModel getProjectileSource() {return projectileSource;}
    public void setProjectileSource(PlayerModel projectileSource) {this.projectileSource = projectileSource;}


}
