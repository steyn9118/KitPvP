package steyn91.kitPvP.models.parts;

public interface CoolDownInterface {
    // выполняется всеми кулдаунами какждый раз когда проходит 2 тика,
    // возвращает True, если кулдаун закончился, False - если нет
    boolean tick();

    int getRemainingTime(); // возвращает оставшееся время кулдауна, 10 = 1 сек.
}
