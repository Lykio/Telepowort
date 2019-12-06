package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// IT BEGINS
public class treload implements CommandExecutor {
    private final Telepowort plugin;

    public treload(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        this.plugin.reloadConfig();
        player.sendMessage("Telepowort reloaded!");
        return true;
    }
}
