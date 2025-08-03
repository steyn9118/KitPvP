package steyn91.kitPvP.mechanicsRelated.customEffects.effects;

import lombok.Getter;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectsController;
import steyn91.kitPvP.mechanicsRelated.customEffects.TickingInterval;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.PropertyModifier;

public class SpeedEffect implements EffectInterface {

    @Getter private int remainingTime; // в тиках
    private final PlayerModel model;
    @Getter private final static TickingInterval tickingInterval = TickingInterval.SECOND; // в тиках
    private final PropertyModifier modifier;

    @Override
    public void tick() {
        remainingTime -= tickingInterval.getInterval();
        if (remainingTime <= 0) stop();
    }

    public SpeedEffect(PlayerModel model, int remainingTime, PropertyModifier modifier){
        this.model = model;
        this.remainingTime = remainingTime;
        this.modifier = modifier;
        apply();
    }

    private void apply(){
        model.getBundle().getBundleCore().speed().addModifier(modifier);
        EffectsController.addEffect(this);
    }

    public void stop(){
        model.getBundle().getBundleCore().speed().removeModifier(modifier);
        model.getBundle().getEffects().remove(this);
        EffectsController.removeEffect(this);
    }
}
