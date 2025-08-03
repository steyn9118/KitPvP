package steyn91.kitPvP.mechanicsRelated.customEffects;

import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;

import java.util.LinkedList;
import java.util.List;

public class EffectsController {

    private static final EffectsHandler everyTickEffects = new EffectsHandler(TickingInterval.EVERY);
    private static final EffectsHandler every2TickEffects = new EffectsHandler(TickingInterval.EVERY_2);
    private static final EffectsHandler every5TickEffects = new EffectsHandler(TickingInterval.EVERY_5);
    private static final EffectsHandler halfSecondEffects = new EffectsHandler(TickingInterval.HALF_SECOND);
    private static final EffectsHandler secondEffects = new EffectsHandler(TickingInterval.SECOND);

    public static void addEffect(EffectInterface effect){
        switch (effect.getTickingInterval()){
            case EVERY -> everyTickEffects.addEffect(effect);
            case EVERY_2 -> every2TickEffects.addEffect(effect);
            case EVERY_5 -> every5TickEffects.addEffect(effect);
            case HALF_SECOND -> halfSecondEffects.addEffect(effect);
            case SECOND -> secondEffects.addEffect(effect);
        }
    }

    public static void removeEffect(EffectInterface effect){
        switch (effect.getTickingInterval()){
            case EVERY -> everyTickEffects.removeEffect(effect);
            case EVERY_2 -> every2TickEffects.removeEffect(effect);
            case EVERY_5 -> every5TickEffects.removeEffect(effect);
            case HALF_SECOND -> halfSecondEffects.removeEffect(effect);
            case SECOND -> secondEffects.removeEffect(effect);
        }
    }

    private static class EffectsHandler{

        private final LinkedList<EffectInterface> effects = new LinkedList<>();
        private final List<EffectInterface> toBeRemoved = new LinkedList<>();

        public EffectsHandler(TickingInterval interval){

            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    effects.removeAll(toBeRemoved);
                    toBeRemoved.clear();
                    if (effects.isEmpty()) return;
                    for (EffectInterface effect : effects) effect.tick();
                }
            };
            runnable.runTaskTimerAsynchronously(KitPvP.getPlugin(), 0, interval.getInterval());
        }

        public void addEffect(EffectInterface newEffect){
            effects.add(newEffect);
        }

        public void removeEffect(EffectInterface effectToRemove){
            toBeRemoved.add(effectToRemove);
        }

    }

}
