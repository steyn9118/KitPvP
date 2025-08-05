package steyn91.kitPvP.bundleRelated.cooldownHandlers;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import steyn91.kitPvP.bundleRelated.abilityRelated.MethodWrap;
import steyn91.kitPvP.models.PlayerModel;

/// Обычное КД (после первого нажатия)
public class SimpleCooldown implements CooldownInterface {
    private double remainingTime; // в тиках
    private final PlayerModel playerModel;
    private final double time;
    @Getter private boolean isActive = true;
    private final MethodWrap outputReceiver;

    public SimpleCooldown(PlayerModel playerModel, int time, MethodWrap outputReceiver) {
        this.playerModel = playerModel;
        this.remainingTime = time;
        this.time = time;
        this.outputReceiver = outputReceiver;
        CooldownsController.addCoolDown(this);
    }

    @Override
    public void tick() {
        if (!isActive) return;
        remainingTime -= (int) playerModel.getBundle().getCore().cooldownRate().getValue();
        if (remainingTime < 0) remainingTime = 0;
        isActive = false;
    }

    @Override
    public void input(){
        if (isActive) {
            playerModel.getPlayer().sendMessage(Component.text("Эта способность на перезарядке! Осталось: " + (remainingTime / 20) + " секунд"));
            return;
        }
        remainingTime = time;
        isActive = true;
        output();
    }

    @Override
    public void output(){
        outputReceiver.execute();
    }

    @Override
    public void destruct(){
        CooldownsController.removeCoolDown(this);
    }
}
