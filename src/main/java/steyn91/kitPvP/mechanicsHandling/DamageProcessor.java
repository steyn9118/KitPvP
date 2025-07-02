package steyn91.kitPvP.mechanicsHandling;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import steyn91.kitPvP.models.PlayerModelController;
import steyn91.kitPvP.models.ProjectileModelController;

public class DamageProcessor implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (event.getHitEntity() == null) return;
        dealDamage(ProjectileModelController.getProjectileModel(event.getEntity().getUniqueId()).getProjectileSource().getPlayer(),event.getHitEntity(), 1);
        event.setCancelled(true);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PlayerModelController.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        PlayerModelController.removeModel(event.getPlayer().getUniqueId());
    }

    // TODO обработка нанесения/получения урона
    public static void dealDamage(Entity source, Entity target, double damageAmount){
        source.sendMessage(Component.text(source + " дал пизды " + target.toString() + " на " + damageAmount + " урона"));
    }

}
