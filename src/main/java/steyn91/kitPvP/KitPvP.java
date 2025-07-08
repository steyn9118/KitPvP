package steyn91.kitPvP;

import hm.zelha.particlesfx.util.ParticleSFX;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import steyn91.kitPvP.mechanicsRelated.BasicListeners;
import steyn91.kitPvP.mechanicsRelated.DamageProcessor;
import steyn91.kitPvP.mechanicsRelated.InputProcessor;

public final class KitPvP extends JavaPlugin {

    @Getter
    private static KitPvP plugin;

    @Override
    public void onEnable() {
        plugin = this;

        PluginManager manager = Bukkit.getServer().getPluginManager();
        manager.registerEvents(new DamageProcessor(), this);
        manager.registerEvents(new InputProcessor(), this);
        manager.registerEvents(new BasicListeners(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
