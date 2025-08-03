package steyn91.kitPvP.models;

import org.bukkit.entity.Player;
import steyn91.kitPvP.bundleRelated.bundles.Beast;

import java.util.HashMap;
import java.util.UUID;

public class PlayerModelController {

    private static final HashMap<UUID, PlayerModel> playerModels = new HashMap<>();

    public static PlayerModel getModelByUUID(UUID uuid) throws Exception{
        PlayerModel model = playerModels.get(uuid);
        if (model == null) throw new Exception("Игрок с UUID " + uuid.toString() + " не найден!");
        return model;
    }

    public static void addPlayer(Player player){
        PlayerModel newModel = new PlayerModel(player);
        playerModels.put(player.getUniqueId(), newModel);
        newModel.setBundle(new Beast(newModel));
    }
    public static void removeModel(UUID uuid){
        playerModels.remove(uuid).getBundle().destruct();
    }
    public static void addModel(UUID uuid, PlayerModel model) {
        playerModels.put(uuid,model);
    }
}
