package steyn91.kitPvP.bundleRelated;

import org.bukkit.event.Listener;
import steyn91.kitPvP.mechanicsRelated.DamageType;
import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;

import java.util.List;

public interface BundleInterface extends Listener {

    void inputPrimary(); // ЛКМ
    void inputSecondary(); // ПКМ
    void inputAbilityF(); // F
    void inputAbilityQ(); // Q
    void inputAbility1(); // 1
    void inputAbility2(); // 2
    void inputAbility3(); // 3

    void destruct();

    BundleCore getCore();

    List<EffectInterface> getEffects();

    void takeDamage(double amount, DamageType type); // Получение урона ПОСЛЕ применения резиста (резист считаеся в DamageProcessor)
    void heal(double amount);
}
