package steyn91.kitPvP.mechanicsHandling;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.PlayerModelController;

public class InputProcessor implements Listener {

    // TODO обработка нажатий на кнопки и передача на соответсвующий PlayerModel
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
            model.getBundle().usePrimary();
        }
        else model.getBundle().useSecondary();
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
        model.getBundle().useAbilityF();
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
        model.getBundle().useAbilityQ();
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
            case 0: model.getBundle().useAbility1();
                break;
            case 1: model.getBundle().useAbility2();
                break;
            case 2: model.getBundle().useAbility3();
                break;
        }
    }
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
