package steyn91.kitPvP.bundleRelated.abilityRelated;

import lombok.Getter;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

/// Менеджер для подписчиков (любое что хочет ожидать ответа листенера) и публишеров (сами листенеры), работает для их связки
public class SubscriptionsManager {
    @Getter private static final HashMap<UUID, Consumer<Location>> subscribersLocation = new HashMap<>();

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
