package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delhome implements CommandExecutor {
    private final Telepowort plugin;
    private String warpName = null;

    public delhome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            warpName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player to delete home.");
            return true;
        }

        if (warpName == null) {
            sender.sendMessage(ChatColor.RED + "You need to specify a home.");
            return true;
        }

        Player player = (Player) sender;

        if (this.plugin.getConfig().getConfigurationSection("Homes").get(warpName) == null) {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
            return true;
        }

        if (player.getUniqueId().toString().equals(this.plugin.getConfig().getConfigurationSection("Homes").get(warpName + ".UUID"))) {
            this.plugin.getConfig().set("Homes." + warpName, null);
            this.plugin.saveConfig();
            player.sendMessage(ChatColor.RED + "Deleted " + ChatColor.AQUA + warpName + ChatColor.RED + ".");
            return true;
        }

        return false;
    }
}
