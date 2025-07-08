package steyn91.kitPvP.bundleRelated.inputHandlers;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityRelated.wraps.MethodWrap;

public class HoldInputHandler implements InputHandlerInterface {

    private boolean state;
    private boolean previousInput = false;
    private final BukkitRunnable clock;
    @Getter
    private int ticks;
    @Getter
    @Setter
    private int threshold = -1;
    @Setter
    private boolean sendSignalWhenThresholdReached = false;
    protected MethodWrap outputReceiver;
    @Setter
    protected MethodWrap keepAliveReceiver;

    public HoldInputHandler(int clockTickFrequency, int keepAliveFrequency, MethodWrap outputReceiver){
        this.outputReceiver = outputReceiver;

        clock = new BukkitRunnable() {
            @Override
            public void run() {
                if (!previousInput & !state) return;
                if (previousInput){
                    state = true;
                    previousInput = false;
                    if (threshold != -1 && ticks <= threshold) ticks+=clockTickFrequency;
                    else if (threshold == -1) ticks+=clockTickFrequency;
                    else if (sendSignalWhenThresholdReached) {
                        outputSignal();
                        state = false;
                        return;
                    }
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

    public void inputSignal(){
        previousInput = true;
    }

    public void destruct(){
        clock.cancel();
    }

    public void outputSignal(){
        outputReceiver.execute();
        ticks = 0;
    }

    public void keepAliveSignal(){
        keepAliveReceiver.execute();
    }

}
