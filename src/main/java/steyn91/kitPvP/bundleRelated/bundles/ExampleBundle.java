package steyn91.kitPvP.bundleRelated.bundles;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.InputHandler;
import steyn91.kitPvP.bundleRelated.abilityModules.MeleeModule;
import steyn91.kitPvP.bundleRelated.abilityModules.RangedModule;
import steyn91.kitPvP.models.PlayerModel;

public class ExampleBundle implements BundleInterface {

    BundleCore core = new BundleCore(200, 0,0,0,0,0,0,0,0,0);

    PlayerModel playerModel;

    public InputHandler primaryHandler;
    public InputHandler secondaryHandler;

    public void destruct(){
        primaryHandler.destruct();
        secondaryHandler.destruct();
    }

    public ExampleBundle(PlayerModel playerModel){
        this.playerModel = playerModel;
        primaryHandler = new InputHandler(InputHandler.InputHandlerType.SIMPLE, () -> usePrimary(playerModel));
        secondaryHandler = new InputHandler(InputHandler.InputHandlerType.HOLD, () -> useSecondary(playerModel, secondaryHandler.getTicks()));
        secondaryHandler.setKeepAliveReceiver(() -> holdSecondary(playerModel, secondaryHandler.getTicks()));
    }

    private void usePrimary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        MeleeModule.meleeDamageSimple(
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                new Vector(3, 1.5, 1),
                5.5,
                () -> {}
        );
    }

    private void useSecondary(PlayerModel playerModel, int ticks) {
        Player player = playerModel.getPlayer();
        player.sendMessage(Component.text("Вы держались за яица " + ticks + " тиков"));
        RangedModule.shootProjectile(
                Arrow.class,
                1.0,
                10.0,
                playerModel,
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                () -> {}
        );
    }

    private void holdSecondary(PlayerModel playerModel, int ticks){
        playerModel.getPlayer().sendMessage(Component.text("Вы держитесь за яица уже " + ticks + " тиков"));
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
    public BundleCore getBundleCore() {
        return core;
    }
}
