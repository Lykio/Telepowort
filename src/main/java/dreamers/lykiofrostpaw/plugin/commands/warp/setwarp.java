package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.names;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class setwarp implements CommandExecutor {
    private final Telepowort telepowort;

    public setwarp(Telepowort telepowort) {
        this.telepowort = telepowort;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(names.command_setwarp)) {
            return true;
        }
        return false;
    }
}
