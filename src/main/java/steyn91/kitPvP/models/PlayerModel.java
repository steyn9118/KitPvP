package steyn91.kitPvP.models;

import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.bundleRelated.bundles.ExampleBundle;

public class PlayerModel {

    public enum State {
        IDLE,
        PLAYING,
        SPECTATING
    }

    // Все динамические характеристики игрока
    public class PlayerModelCore {
        private double currentHealth;
        private double currentResource;
        // TODO ещё характеристики


        public PlayerModelCore(int currentHealth, int currentResource) {
            this.currentHealth = currentHealth;
            this.currentResource = currentResource;
        }

        public double getCurrentHealth(){
            return currentHealth;
        }

        public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
        }
    }



    private State state;
    private BundleInterface bundle;
    private PlayerModelCore core;

    public PlayerModel(){
        state = State.IDLE;
        bundle = null;
        core = new PlayerModelCore(
                200,
                1000
        );
    }

    public BundleInterface getBundle() {
        return bundle;
    }

    public void setBundle(BundleInterface newBundle){
        bundle = newBundle;
        BundleCore bundleCore = bundle.getBundleCore();

        // Копирование максимальных характеристик персонажа в игрока
        core.currentHealth = bundleCore.maxHealth();
        core.currentResource = bundleCore.maxResource();
        // TODO остальные характеристики
    }

    public PlayerModelCore getCore() {
        return core;
    }

    public void setCore(PlayerModelCore core) {
        this.core = core;
    }


}
