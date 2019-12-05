package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class delwarp implements CommandExecutor {
    private final Telepowort telepowort;

    public delwarp(Telepowort telepowort) {
        this.telepowort = telepowort;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        return false;
    }
}
