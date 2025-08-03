package steyn91.kitPvP.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import steyn91.kitPvP.bundleRelated.BundleInterface;

public class PlayerModel {

    @Getter @Setter private State state;
    @Getter @Setter private BundleInterface bundle;
    @Getter private final Player player;

    public PlayerModel(Player player){
        state = State.PLAYING; //TODO убрать в релизе
        bundle = null;
        this.player = player;
    }

    public enum State {
        IN_LOBBY,
        PLAYING,
        SPECTATING
    }

}
