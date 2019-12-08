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
        Player player = (Player) sender;
        // LIST WARPS
        if (args.length == 0) {
            Set<String> homeMap = this.plugin.getConfig().getConfigurationSection("Homes").getConfigurationSection(player.getUniqueId().toString()).getKeys(false);
            StringBuilder homeBuilder = new StringBuilder();
            homeBuilder.append(ChatColor.GOLD + "HOMES: ");
            homeMap.forEach((home) -> homeBuilder.append(ChatColor.YELLOW + "\n").append(home));

            sender.sendMessage(homeBuilder.toString());
            return true;
        }

        if (args.length != 0) {
            homeName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }


        if (this.plugin.getConfig().getConfigurationSection("Homes").contains(player.getUniqueId().toString() + "." + homeName)) { // DO THIS IF WARP EXISTS

            String playerWorld = this.plugin.getConfig().getConfigurationSection("Homes").getString(player.getUniqueId().toString() + "." + homeName + ".WORLD");

            player.teleport(new Location(
                    this.plugin.getServer().getWorld(playerWorld),
                    this.plugin.getConfig().getConfigurationSection("Homes").getInt(player.getUniqueId().toString() + "." + homeName + ".X"),
                    this.plugin.getConfig().getConfigurationSection("Homes").getInt(player.getUniqueId().toString() + "." + homeName + ".Y"),
                    this.plugin.getConfig().getConfigurationSection("Homes").getInt(player.getUniqueId().toString() + "." + homeName + ".Z"),
                    this.plugin.getConfig().getConfigurationSection("Homes").getInt(player.getUniqueId().toString() + "." + homeName + ".YAW"),
                    this.plugin.getConfig().getConfigurationSection("Homes").getInt(player.getUniqueId().toString() + "." + homeName + ".PITCH")
            ));
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "That warp doesn't exist!");
            return false;
        }
    }
}
