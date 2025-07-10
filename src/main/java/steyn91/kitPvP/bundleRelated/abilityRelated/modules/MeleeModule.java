package steyn91.kitPvP.bundleRelated.abilityRelated.modules;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForModules;
import steyn91.kitPvP.bundleRelated.abilityRelated.wraps.MethodWrap;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.models.PlayerModel;


public class MeleeModule {
    public static void meleeDamageSimple(PlayerModel sourceModel, Location anchorPoint, Vector boxSize, double damageAmount, MethodWrap wrap){
        Player player = sourceModel.getPlayer();
        for (Entity damagedEntity : UtilsForModules.getAllEntitiesInCuboid(
                player.getEyeLocation().getDirection(),
                anchorPoint,
                boxSize)
        ){
            DamageProcessor.dealDamage(player, damagedEntity, damageAmount);
        }

        wrap.execute();
    }

}
