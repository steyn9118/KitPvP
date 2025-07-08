package steyn91.kitPvP.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;

public class ProjectileModelController {

    private static final HashMap<UUID, ProjectileModel> projectilesModels = new HashMap<>();



    public static ProjectileModel addProjectileModel(
            Class projectile,
            PlayerModel playerModel,
            Location sourceLocation,
            Double projectileDamage
    ){
        ProjectileModel projectileModel = new ProjectileModel(
                playerModel.getPlayer().getWorld().spawn(sourceLocation, projectile),
                playerModel,
                projectileDamage
        );
        projectilesModels.put(projectileModel.getProjectile().getUniqueId(), projectileModel);
        return projectileModel;
    }

    public static void removeProjectileModel(UUID uuid) {
        projectilesModels.get(uuid).getProjectile().remove();
        projectilesModels.remove(uuid);
    }

    public static ProjectileModel getProjectileModel(UUID uuid) {
        return projectilesModels.get(uuid);
    }
}
