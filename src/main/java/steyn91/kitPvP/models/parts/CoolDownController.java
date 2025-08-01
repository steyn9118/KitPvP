package steyn91.kitPvP.models.parts;

import lombok.Getter;
import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;

import java.util.LinkedList;
import java.util.List;

public class CoolDownController {

    private static final List<CoolDownInterface> coolDowns = new LinkedList<>();
    private static final List<CoolDownInterface> toBeRemoved = new LinkedList<>();

    public void start() {
        BukkitRunnable runnable = new BukkitRunnable() {
            private static int currentTime;
            @Override
            public void run() {
                coolDowns.removeAll(toBeRemoved);
                toBeRemoved.clear();

                if (coolDowns.isEmpty()) {
                    currentTime = 0;
                    return;
                }

                for (CoolDownInterface coolDown : coolDowns) {
                    if (coolDown.tick()) toBeRemoved.add(coolDown);
                }
                coolDowns.removeAll(toBeRemoved);
                toBeRemoved.clear();

                currentTime++;
            }
        };
        runnable.runTaskTimerAsynchronously(KitPvP.getPlugin(), 0, 2);
    }

    public static void addCoolDown(CoolDownInterface newCoolDown) {
        coolDowns.add(newCoolDown);
    }

    public static void removeCoolDown(CoolDownInterface coolDownToRemove) {
        coolDowns.remove(coolDownToRemove);
    }


}
