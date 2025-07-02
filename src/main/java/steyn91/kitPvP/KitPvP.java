package steyn91.kitPvP;

import hm.zelha.particlesfx.util.ParticleSFX;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import steyn91.kitPvP.mechanicsHandling.DamageProcessor;
import steyn91.kitPvP.mechanicsHandling.InputProcessor;

public final class KitPvP extends JavaPlugin {

    private static KitPvP plugin;

    @Override
    public void onEnable() {
        ParticleSFX.setPlugin(this);
        plugin = this;

        PluginManager manager = Bukkit.getServer().getPluginManager();
        manager.registerEvents(new DamageProcessor(), this);
        manager.registerEvents(new InputProcessor(), this);

    }

    public static KitPvP getPlugin(){
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
