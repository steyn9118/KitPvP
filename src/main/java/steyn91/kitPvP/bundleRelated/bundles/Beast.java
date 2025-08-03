package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Getter;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.Property;

import java.util.LinkedList;
import java.util.List;

public class Beast implements BundleInterface {

    private final BundleCore core = new BundleCore(
            new Property(200), // hp
            new Property(200), // max hp
            new Property(1), // regen hp
            new Property(1000), // resource
            new Property(1000), // max resource
            new Property(0), // regen resource
            new Property(1), // speed
            new Property(1), // size
            new Property(1), // cooldown rate
            new Property(1), // primary cooldown rate
            new Property(1), // damage modifier
            new Property(0), // resistance
            new Property(0) // endurance
    );

    @Getter private final List<EffectInterface> effects = new LinkedList<>();
    @Getter private final PlayerModel playerModel;

    public Beast(PlayerModel playerModel){
        this.playerModel = playerModel;
    }


    @Override
    public void inputPrimary() {

    }

    @Override
    public void inputSecondary() {

    }

    @Override
    public void inputAbilityF() {

    }

    @Override
    public void inputAbilityQ() {

    }

    @Override
    public void inputAbility1() {

    }

    @Override
    public void inputAbility2() {

    }

    @Override
    public void inputAbility3() {

    }

    @Override
    public void destruct() {

    }

    @Override
    public BundleCore getBundleCore() {
        return core;
    }
}
