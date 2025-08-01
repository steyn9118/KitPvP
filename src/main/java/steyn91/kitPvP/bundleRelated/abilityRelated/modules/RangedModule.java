package steyn91.kitPvP.bundleRelated.abilityRelated.modules;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.ProjectileModel;
import steyn91.kitPvP.models.ProjectileModelController;

import java.util.UUID;

public class RangedModule {
    public static Location shootProjectile(
            Class projectile,
            Double strength,
            Double projectileDamage,
            PlayerModel sourceModel,
            Location sourceLocation,
            Vector direction // нельзя делать нулевым
    ){
        ProjectileModel projectileModel = ProjectileModelController.addProjectileModel(projectile, sourceModel, sourceLocation, projectileDamage);
        projectileModel.getProjectile().setVelocity(direction.normalize().multiply(strength)); // запускает projectile по направлению взгляда игрока, умноженный на силу strength
        UUID uuid = projectileModel.getProjectile().getUniqueId();
        return null;
        //return PubSubManager.subLocation(uuid, (hitLocation) -> wrap.execute(hitLocation));
    }
}
