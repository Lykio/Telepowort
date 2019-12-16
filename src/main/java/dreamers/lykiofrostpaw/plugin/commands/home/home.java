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

public class home implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public home(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * Doubles both as a informational command and a teleport
     * Empty arguments will return a StringBuilder to the player
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String home = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        if (args.length != 0) {
            home = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        // LIST WARPS IF ARGS EMPTY
        if (args.length == 0) {
            player.sendMessage(homeMessage(playerConfig));
            return true;
        }

        if (home != null) {
            if (playerConfig.getHomes().contains(home)) { // DO THIS IF WARP EXISTS
                playerConfig.setLastTeleportLocation(player.getLocation());
                player.teleport(playerConfig.getHome(home));
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "That home doesn't exist!");
                return true;
            }
        }

        return true;
    }

    private String homeMessage(PlayerConfig playerConfig) {
        playerConfig.reload();
        StringBuilder homeBuilder = new StringBuilder();
        homeBuilder.append(ChatColor.GOLD + "Wolf prisons: ");

        if (playerConfig.getHomes().isEmpty()) {
            homeBuilder.append(ChatColor.GRAY + "\nYou have no wolf prisons. Disappointing.");
        } else {
            for (String h : playerConfig.getHomes()) {
                homeBuilder.append(ChatColor.YELLOW + "\n").append(h);
            }
        }

        return homeBuilder.toString();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        PlayerConfig playerConfig = new PlayerConfig((Player) sender);

        if (!playerConfig.getHomes().isEmpty()) return new ArrayList<>(playerConfig.getHomes());

        return new ArrayList<>();
    }
}
