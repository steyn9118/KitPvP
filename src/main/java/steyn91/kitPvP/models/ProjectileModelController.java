package steyn91.kitPvP.models;

import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;

public class ProjectileModelController {
    public static HashMap<UUID, ProjectileModel> projectilesModels = new HashMap<>();

    public static ProjectileModel addProjectile(Class projectile, PlayerModel playerModel, Location sourceLocation) {
        ProjectileModel projectileModel = new ProjectileModel(playerModel.getPlayer().getWorld().spawn(sourceLocation, projectile), playerModel);
        projectilesModels.put(projectileModel.getProjectile().getUniqueId(), projectileModel);
        return projectileModel;
    }
    public static ProjectileModel getProjectileModel(UUID uuid) {
        return projectilesModels.get(uuid);
    }
}
