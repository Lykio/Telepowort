package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class back implements CommandExecutor {
    private final Telepowort plugin;
    public back(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You need to be a player to use back!");
            return true;
        }

        if (args.length != 0) {
            sender.sendMessage(ChatColor.RED + "There are no arguments to this command!");
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        if (playerConfig.exists()) {
            if (playerConfig.getLastTeleportLocation() != null) {
                player.teleport(playerConfig.getLastTeleportLocation());
            } else {
                player.sendMessage(ChatColor.RED + "You have not been teleported previously.");
            }
        } else {
            playerConfig.createPlayerConfig();
            player.sendMessage(ChatColor.RED + "You have not been teleported previously.");
        }

        return true;
    }

}
