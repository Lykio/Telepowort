package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor {
    private final Telepowort plugin;
    private String warpName = null;

    public sethome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            warpName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create homes!");
            return true;
        }

        if (warpName == null) {
            sender.sendMessage(ChatColor.RED + "Your home needs a name.");
            return true;
        }

        if (this.plugin.getConfig().getConfigurationSection("Homes") == null) {
            this.plugin.getConfig().createSection("Homes");
        }
        Player player = (Player) sender;
        Location playerLocation = player.getLocation();

        if (!(this.plugin.getConfig().getConfigurationSection("Homes").contains(warpName))) { // CREATE NEW WARP

            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".UUID", player.getUniqueId().toString());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".WORLD", playerLocation.getWorld().getName());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".X", playerLocation.getBlockX());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".Y", playerLocation.getBlockY());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".Z", playerLocation.getBlockZ());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".YAW", playerLocation.getYaw());
            this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".PITCH", playerLocation.getPitch());

            this.plugin.saveConfig();
            player.sendMessage(ChatColor.YELLOW + "Created new home: " + ChatColor.AQUA + warpName);
            return true;
        } else if (this.plugin.getConfig().contains("Warps." + warpName)) { // CHECK IF WARP EXISTS
            if (player.getUniqueId().toString().equals(this.plugin.getConfig().getConfigurationSection("Homes").get(warpName + ".UUID"))) { // CHECK IF INVOKER OWNS WARP
                // THEY DID
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".WORLD", playerLocation.getWorld().getName());
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".X", playerLocation.getBlockX());
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".Y", playerLocation.getBlockY());
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".Z", playerLocation.getBlockZ());
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".YAW", playerLocation.getYaw());
                this.plugin.getConfig().getConfigurationSection("Homes").set(warpName + ".PITCH", playerLocation.getPitch());

                this.plugin.saveConfig();
                player.sendMessage(ChatColor.YELLOW + "Updated your home's stuff.");
                return true;
            }
        }

        return false;
    }

}
