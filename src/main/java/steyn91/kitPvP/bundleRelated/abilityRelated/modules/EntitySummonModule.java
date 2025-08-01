package steyn91.kitPvP.bundleRelated.abilityRelated.modules;

import org.bukkit.Location;
import org.bukkit.entity.TNTPrimed;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForModules;
import steyn91.kitPvP.models.LivingEntityModel;
import steyn91.kitPvP.models.LivingEntityModelController;
import steyn91.kitPvP.models.PlayerModel;

public class EntitySummonModule {
    public static void summonEntitySimple(
            PlayerModel sourceModel,
            Location spawnLocation,
            Class entity,
            Double entityDamage,
            int maxTimeAlive
    ){
        LivingEntityModel livingEntityModel = LivingEntityModelController.addLivingEntityModel(entity, sourceModel, spawnLocation, entityDamage, maxTimeAlive);
        UtilsForModules.startTaskLater(100, () -> {
            TNTPrimed tntPrimed = livingEntityModel.getEntity().getWorld().spawn(livingEntityModel.getEntity().getLocation(), TNTPrimed.class);
            tntPrimed.setFuseTicks(0);
            Location allayLocation = livingEntityModel.getEntity().getLocation();
            LivingEntityModelController.removeLivingEntityModel(livingEntityModel.getEntity().getUniqueId());
        });
    }
}
