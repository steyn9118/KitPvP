package steyn91.kitPvP.bundleRelated.abilityRelated.modules;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForModules;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.models.PlayerModel;

import java.util.List;


public class MeleeModule {
    public static List<Entity> meleeDamageSimple(PlayerModel sourceModel, Location anchorPoint, Vector boxSize, double damageAmount){
        Player player = sourceModel.getPlayer();
        List<Entity> entities = UtilsForModules.getAllEntitiesInCuboid(
                player.getEyeLocation().getDirection(),
                anchorPoint,
                boxSize
        );

        for (Entity damagedEntity : entities
        ){
            DamageProcessor.dealDamage(player, damagedEntity, damageAmount);
        }

        return entities;
    }

}
