package steyn91.kitPvP.bundleRelated.bundles;

import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityModules.MeleeModule;
import steyn91.kitPvP.bundleRelated.abilityModules.RangedModule;
import steyn91.kitPvP.models.PlayerModel;

public class ExampleBundle implements BundleInterface {

    BundleCore core = new BundleCore(200, 0,0,0,0,0,0,0,0,0);

    @Override
    public void usePrimary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        MeleeModule.meleeDamageSimple(
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                new Vector(3, 1.5, 1),
                5.5,
                () -> {}
        );
    }

    @Override
    public void useSecondary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        RangedModule.shootProjectile(
                Arrow.class,
                1.0,
                10.0,
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                () -> {}
        );
    }

    @Override
    public void useAbilityF(PlayerModel playerModel) {

    }

    @Override
    public void useAbilityQ(PlayerModel playerModel) {

    }

    @Override
    public void useAbility1(PlayerModel playerModel) {

    }

    @Override
    public void useAbility2(PlayerModel playerModel) {

    }

    @Override
    public void useAbility3(PlayerModel playerModel) {

    }

    @Override
    public BundleCore getBundleCore() {
        return core;
    }
}
