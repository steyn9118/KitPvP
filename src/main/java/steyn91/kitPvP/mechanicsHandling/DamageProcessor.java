package steyn91.kitPvP.mechanicsHandling;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import steyn91.kitPvP.models.ProjectileModel;
import steyn91.kitPvP.models.ProjectileModelController;

import java.util.Collection;
import java.util.Collections;

public class DamageProcessor implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getDamage() < 999) event.setCancelled(true);
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        ProjectileModel projectileModel = ProjectileModelController.getProjectileModel(event.getEntity().getUniqueId());
        dealDamage(
                projectileModel.getProjectileSource().getPlayer(),
                event.getHitEntity(),
                projectileModel.getProjectileDamage()
        );
        event.setCancelled(true);
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
