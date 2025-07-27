package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Setter;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;

public class Beast implements BundleInterface {

    @Setter
    private static BundleCore core = new BundleCore(
            200,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0,
            0
    );

    double rageAmount = 0;


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
        return null;
    }
}
