package steyn91.kitPvP.bundleRelated.abilityModules;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.TNTPrimed;

public class EntitySummonModule {
    public static void summonEntitySimple(Location location){
        Component.text("тнт заспавнился");
        TNTPrimed tntPrimed = location.getWorld().spawn(location, TNTPrimed.class);// для дебага чисто удалить нахуй потом
        tntPrimed.setFuseTicks(30);
    }
}
