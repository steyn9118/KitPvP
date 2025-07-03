package steyn91.kitPvP.bundleRelated;

import org.bukkit.scheduler.BukkitRunnable;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityModules.MethodWrap;

// TODO ГОВНОКЛАСС С ГОВНОКОДОМ - ОБЯЗАТЕЛЬНО ПЕРЕПИСАТЬ
public class InputHandler {

    private interface Handle {
        void execute();
    }

    public enum InputHandlerType{
        SIMPLE,
        HOLD
    }

    private int ticks;
    private boolean state;
    private boolean inputInCurrentTick = false;
    private Handle handle;
    private final MethodWrap outputReceiver;
    private MethodWrap keepAliveReceiver;
    private BukkitRunnable clock;
    private int threshold = -1;
    private boolean sendSignalWhenThresholdReached = false;

    public void setSendSignalWhenThresholdReached(boolean sendSignalWhenThresholdReached) {
        this.sendSignalWhenThresholdReached = sendSignalWhenThresholdReached;
    }

    public void setKeepAliveReceiver(MethodWrap keepAliveReceiver) {
        this.keepAliveReceiver = keepAliveReceiver;
    }

    public void setThreshold(int threshold){
        this.threshold = threshold;
    }

    public int getThreshold(){
        return threshold;
    }

    public int getTicks() {
        return ticks;
    }

    public InputHandler(InputHandlerType type, MethodWrap outputReceiver){
        this.outputReceiver = outputReceiver;
        switch (type){
            case HOLD -> {
                clock = new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (!inputInCurrentTick & !state) return;
                        if (inputInCurrentTick){
                            state = true;
                            inputInCurrentTick = false;
                            if (ticks <= threshold) ticks++;
                            else if (sendSignalWhenThresholdReached) {
                                outputSignal();
                                ticks = 0;
                                state = false;
                                return;
                            }
                        }
                        else {
                            outputSignal();
                            ticks = 0;
                            state = false;
                            return;
                        }
                        if (ticks % 5 == 0){
                            keepAliveSignal();
                        }
                    }
                };
                clock.runTaskTimer(KitPvP.getPlugin(), 0, 1);
                handle = () -> inputInCurrentTick = true;
            }

            case SIMPLE -> handle = this::outputSignal;
        }
    }

    public void destruct(){
        clock.cancel();
    }

    public void inputSignal(){
        handle.execute();
    }

    private void outputSignal(){
        outputReceiver.execute();
    }

    private void keepAliveSignal(){
        keepAliveReceiver.execute();
    }


}
