package steyn91.kitPvP.bundleRelated.abilityRelated.modules;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import steyn91.kitPvP.models.PlayerModel;


public class AreaOfEffectModule {
    public static void spawnAreaBox(PlayerModel sourceModel, Location spawnLocation, int duration, double xRadius, double yRadius, double zRadius) {
        for (Entity entity : spawnLocation.getWorld().getNearbyEntities(spawnLocation, xRadius, yRadius, zRadius)) {
            entity.remove();
        }
    }
}
