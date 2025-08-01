package steyn91.kitPvP.bundleRelated.bundles;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForModules;
import steyn91.kitPvP.bundleRelated.inputHandlers.SimpleInputHandler;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.*;

import java.util.List;

public class Dancer implements BundleInterface {

    private final BundleCore core = new BundleCore(
            new Property(200), // max hp
            new Property(1), // regen hp
            new Property(10), // base damage
            new Property(1000), // resource
            new Property(0), // regen resource
            new Property(1), // speed
            new Property(1.25), // size
            new Property(1), // cooldown rate
            new Property(1), // primary cooldown rate
            new Property(0), // resistance
            new Property(0) // endurance
    );

    private double rageAmount = 0;
    private CoolDownInterface primaryCoolDown = new SimpleCoolDown(10);
    private CoolDownInterface secondaryCoolDown = new SimpleCoolDown(10);
    private boolean isDashed = false;


    private final SimpleInputHandler primaryHandler;
    private final SimpleInputHandler secondaryHandler;
    public void destruct(){
        primaryHandler.destruct();
        secondaryHandler.destruct();
    }

    public Dancer(PlayerModel playerModel){
        primaryHandler = new SimpleInputHandler(
                () -> usePrimary(playerModel, primaryCoolDown), primaryCoolDown
        );
        secondaryHandler = new SimpleInputHandler(
                () -> useSecondary(playerModel), secondaryCoolDown
        );
    }

    private void usePrimary(PlayerModel playerModel, CoolDownInterface primaryCoolDown) {
        Player player = playerModel.getPlayer();
        List<Entity> entities = UtilsForModules.getAllEntitiesInCuboid(
                player.getEyeLocation().getDirection(),
                player.getEyeLocation().clone().add(player.getEyeLocation().getDirection().clone().multiply(2)),
                new Vector(3, 1.5, 1)
        );

        for (Entity damagedEntity : entities
        ){
            if (isDashed) {
                DamageProcessor.dealDamage(player, damagedEntity, 2 * 1.2);
                return;
            }
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
        playerModel.getBundle().getBundleCore().baseSpeed().addModifier(new PropertyModifier(PropertyModifier.PropertyModifierType.MULTIPLY, 1.5)); // увеличение скорости после рывка
        playerModel.getBundle().getBundleCore().naturalHealthRegen().addModifier(new PropertyModifier(PropertyModifier.PropertyModifierType.MULTIPLY, 1.5)); // увеличение хп регена после рывка
        DamageProcessor.dealDamage(player, hitEntity, 2);
        //TODO перезарядка сбрасывается если на ентити была метка
        if (hitEntity.isDead()) {
        }
    }

    private void holdSecondary(PlayerModel playerModel) {
    }


    @Override
    public void inputPrimary() {
        primaryHandler.inputSignal();
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
