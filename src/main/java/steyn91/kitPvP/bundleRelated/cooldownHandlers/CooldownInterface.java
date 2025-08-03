package steyn91.kitPvP.bundleRelated.cooldownHandlers;

public interface CooldownInterface {
    // выполняется всеми кулдаунами какждый раз когда проходит 2 тика,
    // возвращает True, если кулдаун закончился, False - если нет
    void tick();
    void destruct();
    boolean isActive();
    void input();
    void output();
}
