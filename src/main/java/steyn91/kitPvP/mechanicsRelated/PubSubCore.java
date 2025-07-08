package steyn91.kitPvP.mechanicsRelated;

import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class PubSubCore {
    @Getter
    private static final HashMap<UUID, Consumer<Location>> subscribersLocation = new HashMap<>();

    public static void subLocation(UUID uuid, Consumer<Location> consumer) {
        subscribersLocation.put(uuid, consumer);
    }

    public static void publish(UUID uuid, Location location) {
        if (subscribersLocation.containsKey(uuid)) {
            subscribersLocation.get(uuid).accept(location);
            subscribersLocation.remove(uuid);
        }
    }
}
