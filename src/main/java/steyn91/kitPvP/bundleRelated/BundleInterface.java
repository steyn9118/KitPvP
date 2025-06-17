package steyn91.kitPvP.bundleRelated;

public interface BundleInterface {

    void usePrimary(); // ЛКМ
    void useSecondary(); // ПКМ
    void useAbilityF(); // F
    void useAbilityQ(); // Q
    void useAbility1(); // 1
    void useAbility2(); // 2
    void useAbility3(); // 3

    BundleCore getBundleCore();

    // TODO остальные способности и варианты их использования (зажатие и тд.)

}
