package steyn91.kitPvP.bundleRelated;

import steyn91.kitPvP.models.PlayerModel;

public interface BundleInterface {

    void usePrimary(PlayerModel playerModel); // ЛКМ
    void useSecondary(PlayerModel playerModel); // ПКМ
    void useAbilityF(PlayerModel playerModel); // F
    void useAbilityQ(PlayerModel playerModel); // Q
    void useAbility1(PlayerModel playerModel); // 1
    void useAbility2(PlayerModel playerModel); // 2
    void useAbility3(PlayerModel playerModel); // 3

    BundleCore getBundleCore();

    // TODO остальные способности и варианты их использования (зажатие и тд.)

}
