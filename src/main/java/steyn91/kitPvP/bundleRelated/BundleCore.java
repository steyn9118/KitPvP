package steyn91.kitPvP.bundleRelated;

// TODO остальные характеристики для перса
// Статические характеристики для каждого кита
public record BundleCore (
        double maxHealth,
        double naturalHealthRegen,

        double maxResource,
        double naturalResourceRegen,

        double baseSpeed,

        double cooldownRate,
        double primaryCooldownRate,

        double damageResistance,
        double healingEfficiency,

        double endurance
) {}
