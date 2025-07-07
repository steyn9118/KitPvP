package steyn91.kitPvP.bundleRelated.abilityModules;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.ProjectileModel;
import steyn91.kitPvP.models.ProjectileModelController;

public class RangedModule {
    public static void shootProjectile(
            Class projectile,
            Double strength,
            Double projectileDamage,
            PlayerModel sourceModel,
            Location sourceLocation,
            Vector direction,
            MethodWrap wrap
    ){
        ProjectileModel projectileModel = ProjectileModelController.addProjectile(projectile, sourceModel, sourceLocation, projectileDamage);
        projectileModel.setProjectileSource(sourceModel); // в качестве источника урона помещается PlayerModel
        projectileModel.getProjectile().setVelocity(direction.normalize().multiply(strength)); // запускает projectile по направлению взгляда игрока, умноженный на силу strength

        wrap.execute();
    }
}
