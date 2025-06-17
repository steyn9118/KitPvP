package steyn91.kitPvP.mechanicsHandling;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageProcessor implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }

    // TODO обработка нанесения/получения урона
    public void dealDamage(Entity source, Entity target, int damageAmount){

    }

}
