package steyn91.kitPvP;

import hm.zelha.particlesfx.util.ParticleSFX;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitPvP extends JavaPlugin {

    @Override
    public void onEnable() {
        ParticleSFX.setPlugin(this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
