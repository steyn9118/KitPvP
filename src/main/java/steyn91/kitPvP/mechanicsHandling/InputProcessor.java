package steyn91.kitPvP.mechanicsHandling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.PlayerModelController;

public class InputProcessor implements Listener {
    @EventHandler
    public void onPressLeftRight(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerModel model;
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (event.getAction().isLeftClick()) {
            model.getBundle().usePrimary(model);
        }
        else model.getBundle().useSecondary(model);
    }
    @EventHandler
    public void onPressF(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        PlayerModel model;
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.getBundle().useAbilityF(model);
    }
    @EventHandler
    public void onPressQ(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        PlayerModel model;
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.getBundle().useAbilityQ(model);
    }
    @EventHandler
    public void onPress123(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        PlayerModel model;
        try {
            model = PlayerModelController.getModelByUUID(player.getUniqueId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() == null) {
            return;
        }
        if (event.getInventory().getHolder().equals(event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }
}
