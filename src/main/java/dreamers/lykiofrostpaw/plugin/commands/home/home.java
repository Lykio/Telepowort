package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
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
        String home = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
        }

        if (args.length != 0) {
            home = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        // LIST WARPS IF ARGS EMPTY
        if (args.length == 0) {
            Set<String> homes = null;
            try {
                homes = playerConfig.getHomes();
                StringBuilder homeBuilder = new StringBuilder();

                homeBuilder.append(ChatColor.GOLD + "Wolf prisons: ");
                homes.forEach((ahome) -> homeBuilder.append(ChatColor.YELLOW + "\n").append(ahome));

                player.sendMessage(homeBuilder.toString());
            } catch (NullPointerException e) {
                player.sendMessage(ChatColor.RED + "You have no homes yet!");
            }
        }

        if (playerConfig.getHomes().contains(home)) { // DO THIS IF WARP EXISTS
            player.teleport(playerConfig.getHome(home));
        } else {
            player.sendMessage(ChatColor.RED + "That home doesn't exist!");
        }

        return true;
    }
}
