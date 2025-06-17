package steyn91.kitPvP.models;

import java.util.HashMap;
import java.util.UUID;

public class PlayerModelController {

    private static final HashMap<UUID, PlayerModel> playerModels = new HashMap<>();

    public static PlayerModel getModelByUUID(UUID uuid) throws Exception{
        PlayerModel model = playerModels.get(uuid);
        if (model == null) throw new Exception("Игрок с UUID " + uuid.toString() + " не найден!");
        return model;
    }

    public static void putModel(UUID uuid, PlayerModel model){
        playerModels.put(uuid, model);
    }

    public static void removeModel(UUID uuid){
        playerModels.remove(uuid);
    }
}
