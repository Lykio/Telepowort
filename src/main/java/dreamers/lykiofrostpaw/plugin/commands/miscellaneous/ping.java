package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// IT BEGINS
public class ping implements CommandExecutor {
    private final Telepowort plugin;

    public ping(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        player.sendMessage("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
        return true;
    }
}
