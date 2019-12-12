package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
            StringBuilder homeBuilder = new StringBuilder();
            homeBuilder.append(ChatColor.GOLD + "Wolf prisons: ");
            if (playerConfig.getHomes() == null) {
                homeBuilder.append(ChatColor.GRAY + "You have no wolf prisons. Disappointing.");
            } else {
                for (String h : playerConfig.getHomes()) {
                    homeBuilder.append(ChatColor.YELLOW + "\n").append(h);
                }
            }

            player.sendMessage(homeBuilder.toString());
        }

        if (args.length != 0) {
            if (playerConfig.getHomes().contains(home)) { // DO THIS IF WARP EXISTS
                player.teleport(playerConfig.getHome(home));
            } else {
                player.sendMessage(ChatColor.RED + "That home doesn't exist!");
            }
        }

        return true;
    }
}
