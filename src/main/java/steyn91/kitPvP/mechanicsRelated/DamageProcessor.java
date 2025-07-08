package steyn91.kitPvP.mechanicsRelated;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import steyn91.kitPvP.bundleRelated.abilityRelated.PubSubManager;
import steyn91.kitPvP.models.ProjectileModel;
import steyn91.kitPvP.models.ProjectileModelController;

import java.util.Collection;

public class DamageProcessor implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getDamage() < 999) event.setCancelled(true);
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        event.setCancelled(true);
        if (event.getHitEntity() != null) return;
        Entity entity = event.getEntity();
        PubSubManager.publish(entity.getUniqueId(), event.getEntity().getLocation());
        ProjectileModel projectileModel = ProjectileModelController.getProjectileModel(event.getEntity().getUniqueId());
        ProjectileModelController.removeProjectileModel(projectileModel.getProjectile().getUniqueId());
    }


    // TODO обработка нанесения/получения урона
    public static void dealDamage(Entity source, Entity target, double damageAmount){
        source.sendMessage(Component.text(source + " дал пизды " + target.toString() + " на " + damageAmount + " урона"));
        target.broadcastHurtAnimation((Collection<Player>) Bukkit.getOnlinePlayers());
    }
    public static void dealHeal(Entity source, Entity target, double healAmount) {
        source.sendMessage(Component.text(source + "присунул" + target.toString() + "на" + healAmount + "см"));
    }

}
