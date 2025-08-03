package steyn91.kitPvP.bundleRelated.cooldownHandlers;

import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;

import java.util.LinkedList;
import java.util.List;

public class CooldownsController {

    private static final List<CooldownInterface> coolDowns = new LinkedList<>();
    private static final List<CooldownInterface> toBeRemoved = new LinkedList<>();

    public static void start() {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                coolDowns.removeAll(toBeRemoved);
                toBeRemoved.clear();

                if (coolDowns.isEmpty()) return;

                for (CooldownInterface coolDown : coolDowns) {
                    coolDown.tick();
                }
            }
        };
        runnable.runTaskTimerAsynchronously(KitPvP.getPlugin(), 0, 2);
    }

    public static void addCoolDown(CooldownInterface newCoolDown) {
        coolDowns.add(newCoolDown);
    }

    public static void removeCoolDown(CooldownInterface coolDownToRemove) {
        toBeRemoved.add(coolDownToRemove);
    }


}
