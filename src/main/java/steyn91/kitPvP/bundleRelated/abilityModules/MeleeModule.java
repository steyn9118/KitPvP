package steyn91.kitPvP.bundleRelated.abilityModules;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import steyn91.kitPvP.mechanicsHandling.DamageProcessor;
import steyn91.kitPvP.models.PlayerModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MeleeModule {
    public static void meleeDamageSimple(PlayerModel sourceModel, Location anchorPoint, Vector boxSize, double damageAmount, ChainedMethodWrap wrap){
        Player player = sourceModel.getPlayer();

        // ДЛЯ ДЕБАГА
        visualizeRotatedBox(
                anchorPoint,
                boxSize.getX(), boxSize.getY(), boxSize.getZ(),
                player.getPitch(), player.getYaw(), 0,
                20
        );

        for (Entity damagedEntity : getEntitiesInRotatedBox(
                anchorPoint,
                boxSize.getX(), boxSize.getY(), boxSize.getZ(),
                player.getPitch(), player.getYaw(), 0)
        ){
            DamageProcessor.dealDamage(player, damagedEntity, damageAmount);
        }

        wrap.chain();
    }

    /// ТЕХНИЧЕСКИЕ МЕТОДЫ (ЖЁСКИЙ ВАЙБ КОДИНГ)

    private static List<Entity> getEntitiesInRotatedBox(
            Location center,
            double width, double height, double depth,
            double yawDegrees, double pitchDegrees, double rollDegrees) {

        List<Entity> result = new ArrayList<>();

        double cx = center.getX();
        double cy = center.getY();
        double cz = center.getZ();

        double halfWidth = width / 2;
        double halfHeight = height / 2;
        double halfDepth = depth / 2;

        // Convert Euler angles from degrees to radians
        double yawRad = Math.toRadians(-yawDegrees);     // Invert yaw
        double pitchRad = Math.toRadians(-pitchDegrees); // Invert pitch
        double rollRad = Math.toRadians(-rollDegrees);   // Invert roll

        // Rotation matrices for inverse rotation
        double cosYaw = Math.cos(yawRad);
        double sinYaw = Math.sin(yawRad);

        double cosPitch = Math.cos(pitchRad);
        double sinPitch = Math.sin(pitchRad);

        double cosRoll = Math.cos(rollRad);
        double sinRoll = Math.sin(rollRad);

        // Get nearby entities (adjust distance as needed)
        Collection<Entity> nearbyEntities = center.getWorld().getNearbyEntities(center, width*2, height*2, depth*2);

        for (Entity entity : nearbyEntities) {
            Location loc = entity.getLocation();
            Vector pos = loc.toVector();

            // Step 1: Translate to local space
            double x = pos.getX() - cx;
            double y = pos.getY() - cy;
            double z = pos.getZ() - cz;

            // Step 2: Apply inverse rotation (Z -> X -> Y order)
            // Rotate around Z (roll)
            double x1 =  x * cosRoll + y * sinRoll;
            double y1 = -x * sinRoll + y * cosRoll;
            double z1 =  z;

            // Rotate around X (pitch)
            double x2 =  x1;
            double y2 =  y1 * cosPitch - z1 * sinPitch;
            double z2 =  y1 * sinPitch + z1 * cosPitch;

            // Rotate around Y (yaw)
            double x3 =  x2 * cosYaw + z2 * sinYaw;
            double y3 =  y2;
            double z3 = -x2 * sinYaw + z2 * cosYaw;

            // Step 3: Check if transformed point is within unrotated box
            if (Math.abs(x3) <= halfWidth &&
                    Math.abs(y3) <= halfHeight &&
                    Math.abs(z3) <= halfDepth) {
                result.add(entity);
            }
        }

        return result;
    }

    private static void visualizeRotatedBox(Location center, double width, double height, double depth,
                                           double yawDeg, double pitchDeg, double rollDeg, int particlesPerEdge) {

        Location worldCenter = center.clone();
        Vector halfSize = new Vector(width / 2, height / 2, depth / 2);

        // Define the 8 corners of an axis-aligned box centered at origin
        Vector[] localCorners = new Vector[]{
                new Vector(-1, -1, -1),
                new Vector(-1, -1, 1),
                new Vector(-1, 1, -1),
                new Vector(-1, 1, 1),
                new Vector(1, -1, -1),
                new Vector(1, -1, 1),
                new Vector(1, 1, -1),
                new Vector(1, 1, 1)
        };

        // Rotate each corner into world space
        Vector[] worldCorners = new Vector[localCorners.length];
        for (int i = 0; i < localCorners.length; i++) {
            Vector corner = localCorners[i].clone().multiply(halfSize);
            corner = rotateVector(corner, yawDeg, pitchDeg, rollDeg);
            corner.add(center.toVector());
            worldCorners[i] = corner;
        }

        // Draw lines between connected corners
        int[][] edges = {
                {0,1}, {0,2}, {0,4}, {1,3}, {1,5}, {2,3}, {2,6},
                {3,7}, {4,5}, {4,6}, {5,7}, {6,7}
        };

        for (int[] edge : edges) {
            Vector start = worldCorners[edge[0]];
            Vector end = worldCorners[edge[1]];
            drawLine(start, end, worldCenter.getWorld(), particlesPerEdge);
        }
    }

    private static void drawLine(Vector start, Vector end, org.bukkit.World world, int steps) {
        Vector delta = end.clone().subtract(start).multiply(1.0 / steps);
        for (int i = 0; i <= steps; i++) {
            Vector pos = start.clone().add(delta.clone().multiply(i));
            Location loc = pos.toLocation(world);
            world.spawnParticle(Particle.DUST, loc, 1, new Particle.DustOptions(Color.RED, 1));
        }
    }

    // Rotate vector by given Euler angles (yaw, pitch, roll) in degrees
    private static Vector rotateVector(Vector v, double yawDeg, double pitchDeg, double rollDeg) {
        double yawRad = Math.toRadians(yawDeg);
        double pitchRad = Math.toRadians(pitchDeg);
        double rollRad = Math.toRadians(rollDeg);

        double cosYaw = Math.cos(yawRad);
        double sinYaw = Math.sin(yawRad);

        double cosPitch = Math.cos(pitchRad);
        double sinPitch = Math.sin(pitchRad);

        double cosRoll = Math.cos(rollRad);
        double sinRoll = Math.sin(rollRad);

        double x = v.getX();
        double y = v.getY();
        double z = v.getZ();

        // Apply rotation in Y -> X -> Z order
        double x1 = x * cosYaw + z * sinYaw;
        double z1 = -x * sinYaw + z * cosYaw;
        double y1 = y;

        double x2 = x1;
        double y2 = y1 * cosPitch - z1 * sinPitch;
        double z2 = y1 * sinPitch + z1 * cosPitch;

        double x3 = x2 * cosRoll - y2 * sinRoll;
        double y3 = x2 * sinRoll + y2 * cosRoll;
        double z3 = z2;

        return new Vector(x3, y3, z3);
    }
}
