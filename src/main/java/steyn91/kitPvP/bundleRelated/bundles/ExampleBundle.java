package steyn91.kitPvP.bundleRelated.bundles;

import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
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
}
