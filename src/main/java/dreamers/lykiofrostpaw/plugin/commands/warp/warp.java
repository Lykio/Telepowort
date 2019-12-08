package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class warp implements CommandExecutor {
    private final Telepowort plugin;

    public warp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String warpName = null;
        // LIST WARPS
        if (args.length == 0) {
            Set<String> warpMap = this.plugin.getConfig().getConfigurationSection("Warps").getKeys(false);
            StringBuilder warpBuilder = new StringBuilder();
            warpBuilder.append(ChatColor.GOLD + "WARPS: ");
            warpMap.forEach((warp) -> warpBuilder.append(ChatColor.YELLOW + "\n").append(warp));

            sender.sendMessage(warpBuilder.toString());
            return true;
        }

        if (args.length != 0) {
            warpName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        Player player = (Player) sender;

        if (this.plugin.getConfig().getConfigurationSection("Warps").contains(warpName)) { // DO THIS IF WARP EXISTS

            String playerWorld = this.plugin.getConfig().getConfigurationSection("Warps").getString(warpName + ".WORLD");

            player.teleport(new Location(
                    this.plugin.getServer().getWorld(playerWorld),
                    this.plugin.getConfig().getConfigurationSection("Warps").getInt(warpName + ".X"),
                    this.plugin.getConfig().getConfigurationSection("Warps").getInt(warpName + ".Y"),
                    this.plugin.getConfig().getConfigurationSection("Warps").getInt(warpName + ".Z"),
                    this.plugin.getConfig().getConfigurationSection("Warps").getInt(warpName + ".YAW"),
                    this.plugin.getConfig().getConfigurationSection("Warps").getInt(warpName + ".PITCH")
            ));
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "That warp doesn't exist!");
            return false;
        }
    }
}
