package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delhome implements CommandExecutor {
    private final Telepowort plugin;
    private String home = null;

    public delhome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player to delete home.");
            return true;
        }

        if (args.length != 0) {
            home = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        if (playerConfig.getHomes().contains(home)) {
            playerConfig.delHome(home);
            player.sendMessage(ChatColor.RED + "Deleted " + ChatColor.AQUA + home + ChatColor.RED + ".");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
        }

        return false;
    }
}
