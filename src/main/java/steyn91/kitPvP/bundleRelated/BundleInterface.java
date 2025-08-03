package steyn91.kitPvP.bundleRelated;

import steyn91.kitPvP.mechanicsRelated.customEffects.EffectInterface;

import java.util.List;

public interface BundleInterface {

    void inputPrimary(); // ЛКМ
    void inputSecondary(); // ПКМ
    void inputAbilityF(); // F
    void inputAbilityQ(); // Q
    void inputAbility1(); // 1
    void inputAbility2(); // 2
    void inputAbility3(); // 3

    void destruct();

    BundleCore getBundleCore();

    List<EffectInterface> getEffects();

}
