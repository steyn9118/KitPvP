package steyn91.kitPvP.bundleRelated.inputHandlers;

import steyn91.kitPvP.bundleRelated.abilityRelated.wraps.MethodWrap;
import steyn91.kitPvP.mechanicsRelated.customEffects.CustomEffectController;
import steyn91.kitPvP.models.PlayerModel;
import steyn91.kitPvP.models.parts.CoolDownController;
import steyn91.kitPvP.models.parts.CoolDownInterface;
import steyn91.kitPvP.models.parts.SimpleCoolDown;

public class SimpleInputHandler implements InputHandlerInterface{

    protected MethodWrap outputReceiver;
    protected CoolDownInterface simpleCoolDown;

    public SimpleInputHandler(MethodWrap outputReceiver, CoolDownInterface simpleCoolDown){
        this.outputReceiver = outputReceiver;
        this.simpleCoolDown = simpleCoolDown;
    }

    @Override
    public void destruct() {
    }

    @Override
    public void inputSignal() {
        if (simpleCoolDown.tick()) return;
        outputSignal();
    }

    public void outputSignal(){
        outputReceiver.execute();
        CoolDownController.addCoolDown(simpleCoolDown);

    }

    public void keepAliveSignal(){
    }

}
