package steyn91.kitPvP.mechanicsRelated;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityRelated.events.EntityDamagesEntityEvent;
import steyn91.kitPvP.bundleRelated.abilityRelated.events.EntityDamagesPlayerEvent;
import steyn91.kitPvP.bundleRelated.abilityRelated.events.PlayerDamagesEntityEvent;
import steyn91.kitPvP.bundleRelated.abilityRelated.events.PlayerDamagesPlayerEvent;
import steyn91.kitPvP.models.*;

import java.util.*;

public class DamageProcessor implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if (event.getDamage() < 999) event.setCancelled(true);
    }
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        event.setCancelled(true);
        if (event.getHitEntity() != null) return;
        Entity entity = event.getEntity();
        ProjectileModel projectileModel = ProjectileModelController.getProjectileModel(event.getEntity().getUniqueId());
        ProjectileModelController.removeProjectileModel(projectileModel.getProjectile().getUniqueId());
    }


    // TODO обработка нанесения/получения урона
    public static void dealDamage(Entity sourceEntity, Entity targetEntity, double damageAmount, DamageType type){
        Alive source;
        Alive target;

        source = PlayerModelController.getModelByUUID(sourceEntity.getUniqueId());
        if (source == null) source =  LivingEntityModelController.getLivingEntityModel(sourceEntity.getUniqueId());
        if (source == null) return;

        target = PlayerModelController.getModelByUUID(targetEntity.getUniqueId());
        if (target == null) target = LivingEntityModelController.getLivingEntityModel(targetEntity.getUniqueId());
        if (target == null) return;


        if (source instanceof PlayerModel) {
            if (target instanceof PlayerModel targetModel){
                // Игрок бьет игрока
                double realDamage;
                if (type == DamageType.FLAT) realDamage = damageAmount * targetModel.getBundle().getCore().damageResistance().getValue();
                else realDamage = damageAmount;
                targetModel.takeDamage(realDamage, type);

                Bukkit.getServer().getPluginManager().callEvent(new PlayerDamagesPlayerEvent(
                        (PlayerModel) source,
                        targetModel,
                        realDamage,
                        type
                ));
            }
            else {
                // Игрок бьет сущность
                double realDamage = damageAmount;
                // TODO дописать рассчет урона
                target.takeDamage(realDamage, type);

                Bukkit.getServer().getPluginManager().callEvent(new PlayerDamagesEntityEvent(
                        (PlayerModel) source,
                        (LivingEntityModel) target,
                        realDamage,
                        type
                ));
            }
        }
        else {
            if (target instanceof PlayerModel targetModel){
                // Сущность бьет игрока
                double realDamage = calculateDamage(damageAmount, targetModel.getBundle().getCore().damageResistance().getValue(), type);
                targetModel.takeDamage(realDamage, type);

                Bukkit.getServer().getPluginManager().callEvent(new EntityDamagesPlayerEvent(
                        (LivingEntityModel) source,
                        targetModel,
                        realDamage,
                        type
                ));
            }
            else {
                // Сущность бьет сущность
                double realDamage = damageAmount;
                // TODO дописать рассчет урона
                target.takeDamage(realDamage, type);

                Bukkit.getServer().getPluginManager().callEvent(new EntityDamagesEntityEvent(
                        (LivingEntityModel) source,
                        (LivingEntityModel) target,
                        realDamage,
                        type
                ));
            }
        }

        List<Player> players = new LinkedList<>();
        for (Player player : Bukkit.getOnlinePlayers()){
            if (player.equals(targetEntity)) continue;
            players.add(player);
        }
        targetEntity.broadcastHurtAnimation(players);
    }

    private static double calculateDamage(double damageAmount, double resistance, DamageType type){
        if (type == DamageType.FLAT) return damageAmount * resistance;
        return damageAmount;
    }

    public static void dealDamageToEvery(Entity source, List<Entity> targets, double damageAmount, DamageType type){
        for (Entity entity : targets){
            dealDamage(source, entity, damageAmount, type);
        }
    }

    /// Физика
    private static final HashMap<Alive, Double> velocities = new HashMap<>();
    public static void startPhysics(){
        BukkitRunnable physicsHandler = new BukkitRunnable() {
            @Override
            public void run() {
                for (var entry : velocities.entrySet()){
                    Entity entity = entry.getKey().getEntity();

                    // Обходим майновский предел скорости падения
                    if (!entity.isOnGround() && entity.getVelocity().getY() > 3.85){
                        entity.setVelocity(entity.getVelocity().clone().add(new Vector(0, -0.08, 0)));
                    }

                    // Считаем разницу в ускорении за 2 тика
                    Vector velocity = entry.getKey().getEntity().getVelocity();
                    double x = velocity.getX();
                    double y = velocity.getY() + 0.08;
                    double z = velocity.getZ();
                    double magnitude = Math.sqrt(x*x + y*y + z*z);
                    double difference = entry.getValue() - magnitude;
                    // И наносим урон от падения, если ускорение имзенилось больше чем на 1 блок в тик
                    // TODO подкорректировать после тестов
                    if (difference > 1d) entry.getKey().takeDamage(20 * difference, DamageType.FALL);
                    velocities.put(entry.getKey(), magnitude);
                }
            }
        };
        physicsHandler.runTaskTimer(KitPvP.getPlugin(), 0, 1);
    }

    public static void addPhysicalEntity(Alive entity){
        velocities.put(entity, 0d);
    }

    public static void removePhysicalEntity(Alive entity){
        velocities.remove(entity);
    }
}
