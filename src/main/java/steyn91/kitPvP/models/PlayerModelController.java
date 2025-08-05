package steyn91.kitPvP.models;

import org.bukkit.entity.Player;
import steyn91.kitPvP.bundleRelated.bundles.Beast;

import java.util.HashMap;
import java.util.UUID;

public class PlayerModelController {

    private static final HashMap<UUID, PlayerModel> playerModels = new HashMap<>();

    public static PlayerModel getModelByUUID(UUID uuid) {
        return playerModels.get(uuid);
    }

    public static void addPlayer(Player player){
        PlayerModel newModel = new PlayerModel(player);
        playerModels.put(player.getUniqueId(), newModel);
        // TODO исправить после добавления выбора классов
        newModel.setBundle(new Beast(newModel));
    }
    public static void removeModel(UUID uuid){
        playerModels.remove(uuid).getBundle().destruct();
    }
    public static void addModel(UUID uuid, PlayerModel model) {
        playerModels.put(uuid,model);
    }
}
