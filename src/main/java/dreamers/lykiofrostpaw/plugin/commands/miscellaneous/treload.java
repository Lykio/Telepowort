package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// IT BEGINS
public class treload implements CommandExecutor {
    private final Telepowort plugin;

    public treload(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        this.plugin.reloadConfig();
        this.plugin.getLogger().warning("Telepowort reloaded!");
        if (!(player instanceof Player)) {
            player.sendMessage(ChatColor.GREEN + "Telepowort reloaded!");
            return true;
        }
        return true;
    }
}
