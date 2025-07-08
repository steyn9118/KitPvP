package steyn91.kitPvP.bundleRelated.abilityModules;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityModules.wraps.MethodWrap;
import steyn91.kitPvP.bundleRelated.abilityModules.wraps.MethodWrapWithLocation;
import steyn91.kitPvP.mechanicsRelated.PubSubCore;
import steyn91.kitPvP.models.LivingEntityModel;
import steyn91.kitPvP.models.LivingEntityModelController;
import steyn91.kitPvP.models.PlayerModel;

import java.util.UUID;

public class EntitySummonModule {
    public static void summonEntitySimple(
            PlayerModel sourceModel,
            Location spawnLocation,
            Class entity,
            Double entityDamage,
            int maxTimeAlive,
            MethodWrapWithLocation wrap
    ){
        LivingEntityModel livingEntityModel = LivingEntityModelController.addLivingEntityModel(entity, sourceModel, spawnLocation, entityDamage, maxTimeAlive);
        BukkitRunnable clock = new BukkitRunnable() {
            @Override
            public void run() {
                TNTPrimed tntPrimed = livingEntityModel.getEntity().getWorld().spawn(livingEntityModel.getEntity().getLocation(), TNTPrimed.class);
                tntPrimed.setFuseTicks(0);
                Location allayLocation = livingEntityModel.getEntity().getLocation();
                LivingEntityModelController.removeLivingEntityModel(livingEntityModel.getEntity().getUniqueId());
                wrap.execute(allayLocation);
            }
        }; clock.runTaskLater(KitPvP.getPlugin(), maxTimeAlive);
    }

}
