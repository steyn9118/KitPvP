package steyn91.kitPvP.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import net.kyori.adventure.text.Component;
import steyn91.kitPvP.dataRelated.Configs;

public class AdminCommands {

    public static void register(){
        new CommandAPICommand("reloadpvp")
                .withPermission(CommandPermission.OP)
                .executes((commandSender, commandArguments) -> {
                    // TODO перезагрузка
                    Configs.loadPluginConfig();
                    Configs.loadArenasConfig();
                    commandSender.sendMessage(Component.text("Плагин перезагружен"));
                })
                .register();
        // TODO остальные команды
    }

}
