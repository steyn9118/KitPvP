package steyn91.kitPvP.bundleRelated.abilityRelated;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.abilityRelated.wraps.MethodWrap;

import java.util.*;

/// Технические методы, которые нужны или могут понадобиться для абилити модулей
//TODO добавить методы для упрощения поиска сущностей по разной по формам и размерам области
// подумать нужны ли методы для запуска прожектайлов и энтити по каким то сэмплам траекторий
public class UtilsForModules {
    public static void startTaskLater(int delay, MethodWrap wrap) {
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                wrap.execute();
            }
        };
        runnable.runTaskLater(KitPvP.getPlugin(), delay);
    }

    public static void startTask(int period, int delay, int time, MethodWrap wrap) {
        BukkitRunnable runnable = new BukkitRunnable() {
            int counter;
            @Override
            public void run() {
                if (counter == time) cancel();
                wrap.execute();
                counter++;
            }
        };
        runnable.runTaskTimer(KitPvP.getPlugin(), delay, period);
    }

    public static List<Entity> getAllEntitiesInCuboid(Vector direction, Location center, Vector boxSize) {
        List<Entity> intersectingEntities = new ArrayList<>();

        // Размеры
        double length = boxSize.getZ(); // глубина (по взгляду)
        double width = boxSize.getX();  // ширина
        double height = boxSize.getY(); // высота
        double step = 0.25;

        // Оси
        Vector forward = direction.clone().normalize(); // вдоль взгляда
        Vector right = forward.clone().crossProduct(new Vector(0, 1, 0)).normalize();
        Vector up = right.clone().crossProduct(forward).normalize();

        double halfLength = length / 2.0;
        double halfWidth = width / 2.0;
        double halfHeight = height / 2.0;

        World world = center.getWorld();
        Set<Location> cuboidPoints = new HashSet<>();

        // TODO убрать в релизе
        // Построение визуализации
        for (double x = -halfWidth; x <= halfWidth; x += step) {
            for (double y = -halfHeight; y <= halfHeight; y += step) {
                for (double z = -halfLength; z <= halfLength; z += step) {
                    int edges = 0;
                    if (Math.abs(x) == halfWidth) edges++;
                    if (Math.abs(y) == halfHeight) edges++;
                    if (Math.abs(z) == halfLength) edges++;

                    if (edges >= 2) {
                        Vector offset = forward.clone().multiply(z)
                                .add(right.clone().multiply(x))
                                .add(up.clone().multiply(y));
                        Location point = center.clone().add(offset);
                        cuboidPoints.add(point);
                        world.spawnParticle(Particle.DUST, point, 0, new Particle.DustOptions(Color.RED, 1));
                    }
                }
            }
        }

        // Поиск сущностей рядом
        double maxDist = Math.max(Math.max(length, width), height);
        Collection<Entity> nearby = world.getNearbyEntities(center, maxDist, maxDist, maxDist); // TODO добавить сюда фильтр по типу сущности

        for (Entity entity : nearby) {
            BoundingBox entityHitbox = entity.getBoundingBox();
            boolean intersects = false;

            // A. Перебор точек визуализации (как раньше)
            for (Location point : cuboidPoints) {
                if (entityHitbox.contains(point.toVector())) {
                    intersects = true;
                    break;
                }
            }

            // B. Доп. проверка: находится ли центр хитбокса внутри кубоида
            if (!intersects) {
                Vector boxCenter = entityHitbox.getCenter(); // в мировых координатах
                Vector relative = boxCenter.clone().subtract(center.toVector());

                // Перевод точки в локальные координаты кубоида
                double localX = relative.dot(right);
                double localY = relative.dot(up);
                double localZ = relative.dot(forward);

                if (Math.abs(localX) <= halfWidth &&
                        Math.abs(localY) <= halfHeight &&
                        Math.abs(localZ) <= halfLength) {
                    intersects = true;
                }
            }

            if (intersects) {
                intersectingEntities.add(entity);
            }
        }

        return intersectingEntities;
    }

}
