package steyn91.kitPvP.bundleRelated;

import steyn91.kitPvP.models.parts.Property;

// TODO остальные характеристики для перса
// Общие характеристики персонажей
public record BundleCore (
        Property currentHealth,
        Property maxHealth,
        Property naturalHealthRegen,

        Property currentResource,
        Property maxResource,
        Property naturalResourceRegen,

        Property speed,
        Property size,

        Property cooldownRate, // скорость перезарядки абилок
        Property primaryCooldownRate, // скорость перезарядки конкретно первички

        Property allDamageModifier,
        Property damageResistance,

        Property endurance // ?
) {}
