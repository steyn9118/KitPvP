package steyn91.kitPvP.mechanicsRelated.customEffects.effects;

import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;

public class CustomSpeedEffect implements EffectInterface {

    private int remainingTime;
    private int tickingInterval;

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public int getTickingInterval() {
        return tickingInterval;
    }

    @Override
    public boolean tick() {
        if (remainingTime % tickingInterval == 0){

        }

        boolean isEnded = remainingTime == 0;
        return isEnded;
    }


}
