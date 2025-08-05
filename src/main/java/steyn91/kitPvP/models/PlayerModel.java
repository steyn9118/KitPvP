package steyn91.kitPvP.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import steyn91.kitPvP.bundleRelated.BundleInterface;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.mechanicsRelated.DamageType;

public class PlayerModel implements Alive {

    @Getter @Setter private State state;
    @Getter @Setter private BundleInterface bundle;
    @Getter private final Player player;

    public PlayerModel(Player player){
        state = State.PLAYING; //TODO убрать в релизе
        bundle = null;
        this.player = player;
        DamageProcessor.addPhysicalEntity(this);
    }

    @Override
    public boolean takeDamage(double damageAmount, DamageType type) {
        bundle.takeDamage(damageAmount, type);
        return false;
    }

    @Override
    public void heal(double healAmount) {
        bundle.heal(healAmount);
    }

    @Override
    public Entity getEntity(){
        return player;
    }

    public enum State {
        IN_LOBBY,
        PLAYING,
        SPECTATING
    }

}
