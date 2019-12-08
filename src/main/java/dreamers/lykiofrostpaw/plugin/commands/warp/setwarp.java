package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setwarp implements CommandExecutor {
    private final Telepowort plugin;
    private String warpName = null;

    public setwarp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            warpName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create warps!");
            return true;
        }

        if (warpName == null) {
            sender.sendMessage(ChatColor.RED + "Your warp needs a name.");
            return true;
        }

        if (this.plugin.getConfig().getConfigurationSection("Warps") == null) {
            this.plugin.getConfig().createSection("Warps");
        }
        Player player = (Player) sender;
        Location playerLocation = player.getLocation();

        if (!(this.plugin.getConfig().getConfigurationSection("Warps").contains(warpName))) { // CREATE NEW WARP

            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".UUID", player.getUniqueId().toString());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".WORLD", playerLocation.getWorld().getName());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".X", playerLocation.getBlockX());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".Y", playerLocation.getBlockY());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".Z", playerLocation.getBlockZ());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".YAW", playerLocation.getYaw());
            this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".PITCH", playerLocation.getPitch());

            this.plugin.saveConfig();
            player.sendMessage(ChatColor.YELLOW + "Created new warp: " + ChatColor.AQUA + warpName);
            return true;
        } else if (this.plugin.getConfig().contains("Warps." + warpName)) { // CHECK IF WARP EXISTS
            if (player.getUniqueId().toString().equals(this.plugin.getConfig().getConfigurationSection("Warps").get(warpName + ".UUID"))) { // CHECK IF INVOKER OWNS WARP
                // THEY DID
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".WORLD", playerLocation.getWorld().getName());
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".X", playerLocation.getBlockX());
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".Y", playerLocation.getBlockY());
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".Z", playerLocation.getBlockZ());
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".YAW", playerLocation.getYaw());
                this.plugin.getConfig().getConfigurationSection("Warps").set(warpName + ".PITCH", playerLocation.getPitch());

                this.plugin.saveConfig();
                player.sendMessage(ChatColor.YELLOW + "Updated your warp's stuff.");
                return true;
            } else {
                // THEY DIDN'T
                player.sendMessage(ChatColor.RED + "That's not your warp.");
                return true;
            }
        }

        return false;
    }

}
