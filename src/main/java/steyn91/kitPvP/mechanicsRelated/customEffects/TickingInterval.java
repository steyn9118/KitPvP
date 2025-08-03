package steyn91.kitPvP.mechanicsRelated.customEffects;

import lombok.Getter;

public enum TickingInterval {

    EVERY (1), // Для супер сложных эффектов, где критично постоянно проверять что-то
    EVERY_2 (2), // +- тоже что и сверху, но чуть медленее
    EVERY_5 (5), // Уже довольно ленивый интервал
    HALF_SECOND (10), // Что-то прям совсем не важное
    SECOND (20); // В основном для эффектов с логикой только при наложении/снятии. Хорошо для обновления интерфейса

    @Getter
    private final int interval;

    TickingInterval(int interval){
        this.interval = interval;
    }

}
