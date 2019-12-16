package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class delhome implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public delhome(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * This essentially just nulls a Home key and stores that as a key
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String home = null;

        if (args.length != 0) {
            home = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player to delete a home.");
            return true;
        }

        if (home == null) {
            sender.sendMessage(ChatColor.RED + "You need to specify a warp.");
            return true;
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        if (playerConfig.getHomes().contains(home)) {
            playerConfig.delHome(home);
            player.sendMessage(ChatColor.RED + "Deleted " + ChatColor.AQUA + home);
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        PlayerConfig playerConfig = new PlayerConfig((Player) sender);

        if (!playerConfig.getHomes().isEmpty()) return new ArrayList<>(playerConfig.getHomes());
        return new ArrayList<>();
    }
}
