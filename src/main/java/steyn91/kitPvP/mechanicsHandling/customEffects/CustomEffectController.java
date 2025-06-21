package steyn91.kitPvP.mechanicsHandling.customEffects;

import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;

import java.util.LinkedList;
import java.util.List;

public class CustomEffectController {

    private final LinkedList<EffectInterface> effects = new LinkedList<>();
    private final List<EffectInterface> toBeRemoved = new LinkedList<>();

    public void start(){

        BukkitRunnable runnable = new BukkitRunnable() {
            private static int currentTime;
            private static int currentInterval;

            @Override
            public void run() {
                effects.removeAll(toBeRemoved);
                toBeRemoved.clear();

                if (effects.isEmpty()) {
                    currentTime = 0;
                    return;
                }

                for (EffectInterface effect : effects){
                    if (effect.tick()) toBeRemoved.add(effect);
                }
                effects.removeAll(toBeRemoved);
                toBeRemoved.clear();

                currentTime++;
            }
        };
        runnable.runTaskTimerAsynchronously(KitPvP.getPlugin(), 0, 1);
    }

    public void addEffect(EffectInterface newEffect){
        effects.add(newEffect);
    }

    public void removeEffect(EffectInterface effectToRemove){
        toBeRemoved.add(effectToRemove);
    }

}
