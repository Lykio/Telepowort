package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delhome implements CommandExecutor {
    private final Telepowort plugin;
    private String homeName = null;

    public delhome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            homeName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player to delete home.");
            return true;
        }

        Player player = (Player) sender;

        if (this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()) == null) {
            this.plugin.getConfig().createSection("Homes." + player.getUniqueId().toString());
        }

        if (homeName == null) {
            sender.sendMessage(ChatColor.RED + "You need to specify a home.");
            return true;
        }


        if (this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).get(homeName) == null) {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
            return true;
        }

        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName, null);
        this.plugin.saveConfig();
        player.sendMessage(ChatColor.RED + "Deleted " + ChatColor.AQUA + homeName + ChatColor.RED + ".");

        return true;
    }
}
