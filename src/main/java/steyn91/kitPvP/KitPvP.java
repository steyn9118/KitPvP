package steyn91.kitPvP;

import hm.zelha.particlesfx.util.ParticleSFX;
import org.bukkit.plugin.java.JavaPlugin;

public final class KitPvP extends JavaPlugin {

    private static KitPvP plugin;

    @Override
    public void onEnable() {
        ParticleSFX.setPlugin(this);
        plugin = this;

    }

    public static KitPvP getPlugin(){
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
