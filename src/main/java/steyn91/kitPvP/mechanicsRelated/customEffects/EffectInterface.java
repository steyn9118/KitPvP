package steyn91.kitPvP.mechanicsRelated.customEffects;

/// ПРИ НАЛОЖЕНИИ НОВОГО ЭФФЕКТА НИКОГДА НЕ ИСПОЛЬЗУЙТЕ РАНЕЕ СОЗДАННЫЕ ЭКЗЕМПЛЯРЫ!!!

public interface EffectInterface {

    int getRemainingTime();
    void tick(); // TODO отображение эффекта игроку
    void stop();
    TickingInterval getTickingInterval();

}
