package steyn91.kitPvP.models;

import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;

import java.util.UUID;

public class PlayerModel {

    public enum State {
        IDLE,
        PLAYING,
        SPECTATING
    }

    // Все динамические характеристики игрока
    public class PlayerModelCore {
        private int currentHealth;
        private int currentResource;
        // TODO ещё характеристики

        public int getCurrentHealth(){
            return currentHealth;
        }

        public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
        }
    }

    private State state;
    private BundleInterface bundle;
    private PlayerModelCore core;

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
