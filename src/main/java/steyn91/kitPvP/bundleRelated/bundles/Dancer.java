package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForModules;
import steyn91.kitPvP.bundleRelated.inputHandlers.HoldInputHandler;
import steyn91.kitPvP.bundleRelated.inputHandlers.SimpleInputHandler;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.models.PlayerModel;

import java.util.List;

public class Dancer implements BundleInterface {
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

    private double rageAmount = 0;
    private boolean isDashed = false;

    public SimpleInputHandler primaryHandler;
    public SimpleInputHandler secondaryHandler;

    public void destruct(){
        primaryHandler.destruct();
        secondaryHandler.destruct();
    }

    public Dancer(PlayerModel playerModel){
        primaryHandler = new SimpleInputHandler(
                () -> usePrimary(playerModel)
        );
        secondaryHandler = new SimpleInputHandler(
                () -> useSecondary(playerModel)
        );
    }

    private void usePrimary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        List<Entity> entities = UtilsForModules.getAllEntitiesInCuboid(
                player.getEyeLocation().getDirection(),
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                new Vector(3, 1.5, 1)
        );

        for (Entity damagedEntity : entities
        ){
            //TODO сделать проверку, если после рывка, то наносить доп урон через проперти
            //if (isDashed)
            DamageProcessor.dealDamage(player, damagedEntity, 2);
        }
    }

    private void useSecondary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        RayTraceResult blockResult = player.rayTraceBlocks(10);
        RayTraceResult entityResult = player.rayTraceEntities(10);
        if (blockResult == null) return;
        if (entityResult == null) return;
        if (entityResult.getHitEntity() == null) return;
        Entity hitEntity = entityResult.getHitEntity();
        player.teleport(entityResult.getHitEntity().getLocation());
        isDashed = true;
        DamageProcessor.dealDamage(player, hitEntity, 2);
        //TODO перезарядка сбрасывается если на ентити была метка
        if (hitEntity.isDead()) {
        }
    }

    private void holdSecondary(PlayerModel playerModel) {
    }


    @Override
    public void inputPrimary() { primaryHandler.inputSignal();
    }

    @Override
    public void inputSecondary() { secondaryHandler.inputSignal();

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
        return null;
    }
}
