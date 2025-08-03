package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForAbilities;
import steyn91.kitPvP.bundleRelated.cooldownHandlers.SimpleCooldown;
import steyn91.kitPvP.bundleRelated.inputHandlers.SimpleInputHandler;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.Property;

import java.util.LinkedList;
import java.util.List;

public class Dancer implements BundleInterface {
    @Setter private BundleCore core = new BundleCore(
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

    private double rageAmount = 0;
    private boolean isDashed = false;

    private final SimpleInputHandler primaryHandler;
    private final SimpleCooldown primaryCooldown;

    public final SimpleInputHandler secondaryHandler;
    private SimpleCooldown secondaryCooldown;

    public void destruct(){
        primaryHandler.destruct();
        primaryCooldown.destruct();
        secondaryHandler.destruct();
        secondaryCooldown.destruct();
    }

    public Dancer(PlayerModel playerModel){
        this.playerModel = playerModel;
        primaryHandler = new SimpleInputHandler(() -> usePrimary(playerModel));
        primaryCooldown = new SimpleCooldown(playerModel, 20*3, primaryHandler::inputSignal);

        secondaryHandler = new SimpleInputHandler(() -> useSecondary(playerModel));
        secondaryCooldown = new SimpleCooldown(playerModel, 20*10, secondaryHandler::inputSignal);
    }

    private void usePrimary(PlayerModel playerModel) {
        Player player = playerModel.getPlayer();
        List<Entity> entities = UtilsForAbilities.getAllEntitiesInCuboid(
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
