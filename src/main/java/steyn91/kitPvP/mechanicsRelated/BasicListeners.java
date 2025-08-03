package steyn91.kitPvP.mechanicsRelated;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import steyn91.kitPvP.models.PlayerModelController;

public class BasicListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PlayerModelController.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        PlayerModelController.removeModel(event.getPlayer().getUniqueId());
    }

}
