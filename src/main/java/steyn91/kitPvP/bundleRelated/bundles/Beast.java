package steyn91.kitPvP.bundleRelated.bundles;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.abilityRelated.UtilsForAbilities;
import steyn91.kitPvP.bundleRelated.abilityRelated.events.PlayerDamagesPlayerEvent;
import steyn91.kitPvP.bundleRelated.cooldownHandlers.SimpleCooldown;
import steyn91.kitPvP.bundleRelated.inputHandlers.SimpleInputHandler;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.mechanicsRelated.DamageType;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.propertiesRelated.ModifierType;
import steyn91.kitPvP.models.propertiesRelated.Property;
import steyn91.kitPvP.models.propertiesRelated.PropertyModifier;

import java.util.LinkedList;
import java.util.List;

public class Beast implements BundleInterface {

    @Getter private final BundleCore core = new BundleCore(
            new Property(200), // hp
            new Property(200), // max hp
            new Property(1), // regen hp
            new Property(0), // resource
            new Property(100), // max resource
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
        Bukkit.getServer().getPluginManager().registerEvents(this, KitPvP.getPlugin());

        primaryInputHandler = new SimpleInputHandler(this::usePrimary);
        primaryCooldown = new SimpleCooldown(playerModel, 30, primaryInputHandler::inputSignal);

        secondaryInputHandler = new SimpleInputHandler(this::useSecondary);
        secondaryCooldown = new SimpleCooldown(playerModel, 30, secondaryInputHandler::inputSignal);


        core.currentHealth().setMaxValue(core.maxHealth());
        core.currentResource().setMaxValue(core.maxResource());
    }

    @Override
    public void takeDamage(double amount, DamageType type) {
        core.currentHealth().decreaseBaseValue(amount);
        playerModel.getPlayer().setHealth(core.currentHealth().getRelativeValue());
        core.currentResource().addModifier(
                new PropertyModifier(ModifierType.FLAT, amount * 0.6));
        checkForHunt();
    }

    @Override
    public void heal(double amount) {
        core.currentHealth().increaseBaseValue(amount);
    }

    @EventHandler
    public void dealDamage(PlayerDamagesPlayerEvent event){
        core.currentResource().addModifier(
                new PropertyModifier(ModifierType.FLAT, event.getDamageAmount() * 0.3));
    }

    private boolean huntMode = false;

    private void checkForHunt(){
        if (core.currentResource().getValue() >= core.maxResource().getValue()){
            huntMode = true;
            BukkitRunnable huntTimer = new BukkitRunnable() {
                int time = 0;
                @Override
                public void run() {
                    time += 10;
                    if (time >= 20*15) endHunt();
                }

                public void endHunt() {
                    this.cancel();
                    huntMode = false;
                }
            };
            huntTimer.runTaskTimer(KitPvP.getPlugin(), 0, 10);
        }
    }


    // Первичка

    private final SimpleInputHandler primaryInputHandler;
    private final SimpleCooldown primaryCooldown;

    private void usePrimary(){
        Vector viewDirection = playerModel.getPlayer().getEyeLocation().getDirection();
        List<Entity> entities = UtilsForAbilities.getAllEntitiesInCuboid(
                viewDirection,
                playerModel.getPlayer().getEyeLocation().clone().add(viewDirection.clone().multiply(2)),
                new Vector(3, 2, 2)
        );
        DamageProcessor.dealDamageToEvery(playerModel.getPlayer(), entities, 25, DamageType.FLAT);
    }

    @Override
    public void inputPrimary() {
        primaryCooldown.input();
    }


    // Вторичка

    private final SimpleInputHandler secondaryInputHandler;
    private final SimpleCooldown secondaryCooldown;

    private void useSecondary(){

    }

    @Override
    public void inputSecondary() {
        secondaryCooldown.input();
    }


    // Неиспользуемое

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
        primaryCooldown.destruct();
        primaryInputHandler.destruct();
        secondaryCooldown.destruct();
        secondaryInputHandler.destruct();

        PlayerDamagesPlayerEvent.getHandlerList().unregister(this);
    }
}
