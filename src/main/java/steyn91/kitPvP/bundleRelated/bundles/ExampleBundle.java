package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Setter;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityRelated.modules.AreaOfEffectModule;
import steyn91.kitPvP.bundleRelated.abilityRelated.modules.EntitySummonModule;
import steyn91.kitPvP.bundleRelated.inputHandlers.HoldInputHandler;
import steyn91.kitPvP.bundleRelated.abilityRelated.modules.MeleeModule;
import steyn91.kitPvP.bundleRelated.abilityRelated.modules.RangedModule;
import steyn91.kitPvP.bundleRelated.inputHandlers.SimpleInputHandler;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.CoolDownInterface;

public class ExampleBundle implements BundleInterface {

    @Setter
    private static BundleCore core = null;

    PlayerModel playerModel;
    private CoolDownInterface primaryCoolDown;

    public SimpleInputHandler primaryHandler;
    public HoldInputHandler secondaryHandler;

    public void destruct(){
        primaryHandler.destruct();
        secondaryHandler.destruct();
    }

    public ExampleBundle(PlayerModel playerModel){
        this.playerModel = playerModel;
        primaryHandler = new SimpleInputHandler(
                () -> usePrimary(playerModel), primaryCoolDown
        );
        secondaryHandler = new HoldInputHandler(
                4,
                4,
                () -> useSecondary(playerModel)
        );
        secondaryHandler.setKeepAliveReceiver(
                () -> holdSecondary(playerModel)
        );
    }



    private void usePrimary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        MeleeModule.meleeDamageSimple(
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                new Vector(3, 1.5, 1),
                5.5
        );
    }

    private void useSecondary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();

        RangedModule.shootProjectile(
                Arrow.class,
                1.0,
                10.0,
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(1)),
                player.getEyeLocation().getDirection()
        );

        EntitySummonModule.summonEntitySimple(
                playerModel,
                null,
                Allay.class,
                20.0,
                100
        );


        RangedModule.shootProjectile(
                Arrow.class,
                1.0,
                1.0,
                playerModel,
                null,
                new Vector(0,1,0)
        );
        AreaOfEffectModule.spawnAreaBox(
                playerModel,
                null,
                1,
                5.0,
                5.0,
                5.0
        );
    }

    private void holdSecondary(PlayerModel playerModel){

    }

    private void useAbilityF(PlayerModel playerModel) {

    }

    private void useAbilityQ(PlayerModel playerModel) {

    }

    private void useAbility1(PlayerModel playerModel) {

    }

    private void useAbility2(PlayerModel playerModel) {

    }

    private void useAbility3(PlayerModel playerModel) {

    }



    @Override
    public void inputPrimary() {
        primaryHandler.inputSignal();
    }

    @Override
    public void inputSecondary() {
        secondaryHandler.inputSignal();
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

    public BundleCore getBundleCore() {
        return core;
    }
}
