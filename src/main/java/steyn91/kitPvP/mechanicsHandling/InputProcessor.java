package steyn91.kitPvP.mechanicsHandling;

import io.papermc.paper.event.entity.EntityDamageItemEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.PlayerModelController;

public class InputProcessor implements Listener {
    // Для левой кнопки мыши
    @EventHandler
    public void onPlayerInteract(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;
        PlayerModel model;
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!model.getState().equals(PlayerModel.State.PLAYING)) return;
        model.getBundle().usePrimary(model);
    }
    // Для левой и правой кнопки мыши
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        PlayerModel model;
        Player player = event.getPlayer();
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!model.getState().equals(PlayerModel.State.PLAYING)) return;
        if (event.getAction().isLeftClick()) {
            model.getBundle().usePrimary(model);
        }
        else if (event.getAction().isRightClick()) {
            model.getBundle().useSecondary(model);
        }
    }
    @EventHandler
    public void onPressF(PlayerSwapHandItemsEvent event) {
        PlayerModel model;
        Player player = event.getPlayer();
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!model.getState().equals(PlayerModel.State.PLAYING)) return;
        model.getBundle().useAbilityF(model);
    }
    @EventHandler
    public void onPressQ(PlayerDropItemEvent event) {
        PlayerModel model;
        Player player = event.getPlayer();
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!model.getState().equals(PlayerModel.State.PLAYING)) return;
        model.getBundle().useAbilityQ(model);
    }
    @EventHandler
    public void onPress123(PlayerItemHeldEvent event) {
        PlayerModel model;
        Player player = event.getPlayer();
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!model.getState().equals(PlayerModel.State.PLAYING)) return;
        switch (event.getNewSlot()) {
            case 0: model.getBundle().useAbility1(model);
                break;
            case 1: model.getBundle().useAbility2(model);
                break;
            case 2: model.getBundle().useAbility3(model);
                break;
        }
    }
    //TODO откючит взаимодействие с инв, только просомтр лора предметов
    /*@EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (event.getInventory().getHolder().equals(event.getWhoClicked())) {
            event.setCancelled(true);
        }
    } */
}
