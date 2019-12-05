package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.names;
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
        if (command.getName().equalsIgnoreCase(names.command_ping)) {
            player.sendMessage("Pong, nigga. Fuck u want?");
            return true;
        }
        return false;
    }
}
