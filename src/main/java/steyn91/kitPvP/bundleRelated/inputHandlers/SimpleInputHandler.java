package steyn91.kitPvP.bundleRelated.inputHandlers;

import steyn91.kitPvP.bundleRelated.abilityModules.MethodWrap;

public class SimpleInputHandler implements InputHandlerInterface{

    protected MethodWrap outputReceiver;

    public SimpleInputHandler(MethodWrap outputReceiver){
        this.outputReceiver = outputReceiver;
    }

    @Override
    public void destruct() {
    }

    @Override
    public void inputSignal() {
        outputSignal();
    }

    public void outputSignal(){
        outputReceiver.execute();
    }

    public void keepAliveSignal(){
    }

}
