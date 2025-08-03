package steyn91.kitPvP.bundleRelated.inputHandlers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityRelated.MethodWrap;

public class TriggerInputHandler implements InputHandlerInterface {

    private boolean state;
    private final BukkitRunnable clock;
    @Getter private int ticks;
    @Getter @Setter private int threshold = -1;
    @Setter private boolean sendSignalWhenThresholdReached = false;
    protected final MethodWrap outputReceiver;
    @Setter protected MethodWrap keepAliveReceiver;

    public TriggerInputHandler(int clockTickFrequency, int keepAliveFrequency, MethodWrap outputReceiver){
        this.outputReceiver = outputReceiver;

        clock = new BukkitRunnable() {
            @Override
            public void run() {
                if (!state) return;
                if (threshold != -1 && ticks <= threshold) ticks+=clockTickFrequency;
                else if (threshold == -1) ticks+=clockTickFrequency;
                else if (sendSignalWhenThresholdReached) {
                    outputSignal();
                    state = false;
                    return;
                }
                else {
                    outputSignal();
                    state = false;
                    return;
                }
                if (ticks % keepAliveFrequency == 0){
                    keepAliveSignal();
                }
            }
        };
        clock.runTaskTimer(KitPvP.getPlugin(), 0, clockTickFrequency);
    }

    @Override
    public void destruct() {
        clock.cancel();
    }

    @Override
    public void inputSignal() {
        state = true;
    }

    @Override
    public void outputSignal() {
        outputReceiver.execute();
    }

    @Override
    public void keepAliveSignal() {
        keepAliveReceiver.execute();
    }
}
