package steyn91.kitPvP.bundleRelated;

import lombok.Getter;
import steyn91.kitPvP.models.parts.Property;

// TODO остальные характеристики для перса
// Общие характеристики персонажей

public record BundleCore (
        Property maxHealth,
        Property naturalHealthRegen,

        Property baseDamage,

        Property maxResource,
        Property naturalResourceRegen,

        Property baseSpeed,
        Property baseSize,

        Property cooldownRate,
        Property primaryCooldownRate,

        Property damageResistance,

        Property endurance
) {}
