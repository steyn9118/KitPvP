package steyn91.kitPvP.models;


import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.UUID;

public class LivingEntityModelController {
    private static final HashMap<UUID, LivingEntityModel> livingEntityModels = new HashMap<>();

    public static LivingEntityModel addLivingEntityModel(
            Class entity,
            PlayerModel playerModel,
            Location sourceLocation,
            Double entityDamage,
            int maxTimeAlive
    ){
        LivingEntityModel livingEntityModel = new LivingEntityModel(
                playerModel.getPlayer().getWorld().spawn(sourceLocation, entity),
                playerModel,
                entityDamage,
                maxTimeAlive
        );
        livingEntityModels.put(livingEntityModel.getEntity().getUniqueId(), livingEntityModel);
        return livingEntityModel;
    }

    public static void removeLivingEntityModel(UUID uuid) {
        livingEntityModels.get(uuid).getEntity().remove();
        livingEntityModels.remove(uuid);
    }

    public static LivingEntityModel getLivingEntityModel(UUID uuid) {
        return livingEntityModels.get(uuid);
    }
}
