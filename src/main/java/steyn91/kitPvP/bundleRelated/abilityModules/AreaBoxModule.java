package steyn91.kitPvP.bundleRelated.abilityModules;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import steyn91.kitPvP.models.PlayerModel;


public class AreaBoxModule {
    public static void spawnAreaBox(PlayerModel sourceModel, Location spawnLocation, int duration, double xRadius, double yRadius, double zRadius) {
        for (Entity entity : spawnLocation.getWorld().getNearbyEntities(spawnLocation, xRadius, yRadius, zRadius)) {
            entity.remove();
        }
    }
}
