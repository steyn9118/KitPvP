package steyn91.kitPvP.bundleRelated;

// TODO остальные характеристики для перса
// Статические характеристики для каждого кита
public record BundleCore (
        double maxHealth,
        double naturalHealthRegen,

        double maxResource,
        double naturalResourceRegen,

        double baseSpeed,
        double baseSize,

        double cooldownRate,
        double primaryCooldownRate,

        double damageResistance,

        double endurance
) {}
