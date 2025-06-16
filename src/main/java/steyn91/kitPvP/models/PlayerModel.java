package steyn91.kitPvP.models;

import steyn91.kitPvP.bundleRelated.BundleInterface;

import java.util.UUID;

public class PlayerModel {

    public enum State{
        IDLE,
        PLAYING,
        SPECTATING
    }

    private UUID uuid;
    private State state;
    private BundleInterface bundle;


}
