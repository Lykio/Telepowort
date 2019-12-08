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
    private String homeName = null;

    public sethome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            homeName = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create homes!");
            return true;
        }

        Player player = (Player) sender;

        if (this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()) == null) {
            this.plugin.getConfig().createSection("Homes." + player.getUniqueId().toString());
        }

        if (homeName == null) {
            sender.sendMessage(ChatColor.RED + "Your home needs a name.");
            return true;
        }

        Location playerLocation = player.getLocation();

        // CREATE NEW WARP

        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".WORLD", playerLocation.getWorld().getName());
        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".X", playerLocation.getBlockX());
        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".Y", playerLocation.getBlockY());
        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".Z", playerLocation.getBlockZ());
        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".YAW", playerLocation.getYaw());
        this.plugin.getConfig().getConfigurationSection("Homes." + player.getUniqueId().toString()).set(homeName + ".PITCH", playerLocation.getPitch());

        this.plugin.saveConfig();
        player.sendMessage(ChatColor.YELLOW + "Created new home: " + ChatColor.AQUA + homeName);

        return true;
    }
}
