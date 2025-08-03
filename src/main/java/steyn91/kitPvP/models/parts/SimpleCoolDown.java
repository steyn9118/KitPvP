package steyn91.kitPvP.models.parts;

/// КД для обычного хэндлера (единичное нажатие)
public class SimpleCoolDown implements CoolDownInterface {
    private int remainingTime; // 10 = 1 секунда


    public SimpleCoolDown(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public boolean tick() {
        return remainingTime <= 0;
    }

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }
}
