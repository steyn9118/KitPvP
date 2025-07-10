package steyn91.kitPvP.dataRelated;

import de.leonhard.storage.Config;
import de.leonhard.storage.Yaml;
import steyn91.kitPvP.KitPvP;
import steyn91.kitPvP.bundleRelated.BundleCore;
import steyn91.kitPvP.bundleRelated.bundles.ExampleBundle;
import steyn91.kitPvP.mapRelated.ArenaClassic;
import steyn91.kitPvP.mapRelated.GameController;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Configs {

    private static final KitPvP plugin = KitPvP.getPlugin();

    private static Config pluginConfig;
    private static final List<Yaml> arenasConfigs = new LinkedList<>();
    private static final List<Yaml> bundleConfigs = new LinkedList<>();

    public static void loadPluginConfig(){
        // TODO переменные для плагина

    }

    public static void loadArenasConfig(){
        arenasConfigs.clear();

        File arenasFolder = new File(plugin.getDataFolder() + "/Arenas");
        if (!arenasFolder.exists()) arenasFolder.mkdir();

        File[] configFiles = arenasFolder.listFiles();
        if (configFiles == null){
            plugin.getLogger().severe("Не получилось получить файлы арен!");
            return;
        }
        if (configFiles.length == 0){
            plugin.getLogger().warning("Конфигурации арен отсутсвуют.");
            return;
        }

        for (File file : configFiles){
            arenasConfigs.add(new Yaml(file));
        }

        for (Yaml arenaConfig : arenasConfigs){
            // TODO все переменные из конфига, как только ярик их придумает
            ArenaClassic newArena = new ArenaClassic();

            GameController.addClassicArena(newArena);
        }
    }

    public static void loadBundlesConfig(){
        bundleConfigs.clear();

        File bundlesFolder = new File(plugin.getDataFolder() + "/Bundles");
        if (!bundlesFolder.exists()) bundlesFolder.mkdir();

        File[] configFiles = bundlesFolder.listFiles();
        if (configFiles == null){
            plugin.getLogger().severe("Не получилось получить файлы арен!");
            return;
        }
        if (configFiles.length == 0){
            plugin.getLogger().warning("Конфигурации арен отсутсвуют.");
            return;
        }

        for (File file : configFiles){
            bundleConfigs.add(new Yaml(file));
        }

        for (Yaml bundleConfig : bundleConfigs){
            // TODO все переменные из конфига

            switch (bundleConfig.getString("bundle_id")){
                case "example_bundle" -> {
                    ExampleBundle.setCore(new BundleCore(
                            bundleConfig.getOrSetDefault("max_health", 200d),
                            bundleConfig.getOrSetDefault("natural_health_regen", 0.2d),// 2/20
                            bundleConfig.getOrSetDefault("max_resource", 100d),
                            bundleConfig.getOrSetDefault("natural_resource_regen", 0.5d),// 10/20
                            bundleConfig.getOrSetDefault("base_speed", 1d),
                            bundleConfig.getOrSetDefault("base_size", 1d),
                            bundleConfig.getOrSetDefault("cooldown_rate", 1d),
                            bundleConfig.getOrSetDefault("primary_cooldown_rate", 1d),
                            bundleConfig.getOrSetDefault("damage_resistance", 1d),
                            bundleConfig.getOrSetDefault("endurance", 1d)
                    ));
                }
                case "бкбра" -> {

                }
            }

        }
    }

}
