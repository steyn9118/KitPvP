package steyn91.kitPvP.bundleRelated.bundles;

import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityModules.ChainedMethodWrap;
import steyn91.kitPvP.bundleRelated.abilityModules.MeleeModule;
import steyn91.kitPvP.bundleRelated.abilityModules.RangedModule;

public class ExampleBundle implements BundleInterface {

    BundleCore core = new BundleCore(
            200,
            200,
            100,
            100
    );

    @Override
    public void usePrimary() {
        RangedModule.shootProjectile(() -> {

        });
    }

    @Override
    public void useSecondary() {

    }

    @Override
    public void useAbilityF() {

    }

    @Override
    public void useAbilityQ() {

    }

    @Override
    public void useAbility1() {

    }

    @Override
    public void useAbility2() {

    }

    @Override
    public void useAbility3() {

    }

    @Override
    public BundleCore getBundleCore() {
        return null;
    }
}
