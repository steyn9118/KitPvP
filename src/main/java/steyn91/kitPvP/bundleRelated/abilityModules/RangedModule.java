package steyn91.kitPvP.bundleRelated.abilityModules;

import org.bukkit.Location;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.ProjectileModel;
import steyn91.kitPvP.models.ProjectileModelController;

public class RangedModule {
    public static void shootProjectile(Class projectile, Double strength, PlayerModel sourceModel, Location sourceLocation, ChainedMethodWrap wrap){
        ProjectileModel projectileModel = ProjectileModelController.addProjectile(projectile, sourceModel, sourceLocation);
        projectileModel.setProjectileSource(sourceModel); // в качестве источника урона помещается PlayerModel
        projectileModel.getProjectile().setVelocity(sourceLocation.getDirection().normalize().multiply(strength)); // запускает projectile по направлению взгляда игрока, умноженный на силу strength
        wrap.chain();
    }
}
