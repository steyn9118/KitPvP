package steyn91.kitPvP.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.BundleInterface;

public class PlayerModel {



    // Все динамические характеристики игрока
    public class PlayerModelCore {
        @Getter
        private double currentHealth;
        @Getter
        private double currentResource;
        @Getter
        private double scale;
        // TODO ещё характеристики из bundle core и не только


        public PlayerModelCore(int currentHealth, int currentResource) {
            this.currentHealth = currentHealth;
            this.currentResource = currentResource;
        }

        public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
        }
    }


    @Getter
    @Setter
    private State state;
    @Getter
    private BundleInterface bundle;
    @Getter
    private final PlayerModelCore core;
    @Getter
    private final Player player;

    public PlayerModel(Player player){
        state = State.PLAYING; //TODO убрать в релизе
        bundle = null;
        core = new PlayerModelCore(
                200,
                1000
        );
        this.player = player;
    }

    public void setBundle(BundleInterface newBundle){
        bundle = newBundle;
        BundleCore bundleCore = bundle.getBundleCore();

        // Копирование статических характеристик персонажа в игрока
        core.currentHealth = bundleCore.maxHealth();
        core.currentResource = bundleCore.maxResource();
        // TODO остальные характеристики
    }

    public enum State {
        IDLE,
        PLAYING,
        SPECTATING
    }

}
