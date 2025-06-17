package steyn91.kitPvP.models;

import java.util.HashMap;
import java.util.UUID;

public class PlayerModelController {

    HashMap<UUID, PlayerModel> playerModels = new HashMap<>();

    public PlayerModel getModelByUUID(UUID uuid) throws Exception{
        PlayerModel model = playerModels.get(uuid);
        if (model == null) throw new Exception("Игрок с UUID " + uuid.toString() + " не найден!");
        return model;
    }

    public void putModel(UUID uuid, PlayerModel model){
        playerModels.put(uuid, model);
    }

    public void removeModel(UUID uuid){
        playerModels.remove(uuid);
    }
}
