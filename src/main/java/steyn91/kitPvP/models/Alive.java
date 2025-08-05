package steyn91.kitPvP.models;

import org.bukkit.entity.Entity;
import steyn91.kitPvP.mechanicsRelated.DamageType;

public interface Alive {

    boolean takeDamage(double damageAmount, DamageType type); // false - alive, true - dead
    void heal(double healAmount);
    Entity getEntity();

}
