package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class home implements CommandExecutor {
    private final Telepowort plugin;

    public home(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String homeName = null;
        Set<String> homeMap = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        Player player = (Player) sender;

        if (this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()) == null) {
            this.plugin.getConfig().createSection("Homes." + player.getUniqueId().toString());
        }
        // LIST WARPS
        if (args.length == 0) {
            try {
                homeMap = this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getKeys(false);
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.RED + "You have no homes yet!");
            }
            StringBuilder homeBuilder = new StringBuilder();
            homeBuilder.append(ChatColor.GOLD + "Wolf prisons: ");
            homeMap.forEach((ahome) -> homeBuilder.append(ChatColor.YELLOW + "\n").append(ahome));

            sender.sendMessage(homeBuilder.toString());
            return true;
        }

        if (args.length != 0) {
            homeName = args[0];
        }

        if (this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).contains(homeName)) { // DO THIS IF WARP EXISTS

            String playerWorld = this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getString(homeName + ".WORLD");

            player.teleport(new Location(
                    this.plugin.getServer().getWorld(playerWorld),
                    this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getInt(homeName + ".X"),
                    this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getInt(homeName + ".Y"),
                    this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getInt(homeName + ".Z"),
                    this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getInt(homeName + ".YAW"),
                    this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).getInt(homeName + ".PITCH")
            ));
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
            return false;
        }
    }
}
