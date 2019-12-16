package dreamers.lykiofrostpaw.plugin.commands.warp;

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

public class warp implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public warp(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * Doubles as a command that lists all existing warps or a teleport
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String warp = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        if (args.length != 0) {
            warp = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);
        WarpConfig warpConfig = new WarpConfig(plugin);

        // LIST WARPS
        if (args.length == 0) {
            player.sendMessage(warpMessage(warpConfig));
            return true;
        }

        if (args.length != 0) {
            if (warpConfig.getWarps().contains(warp)) { // DO THIS IF WARP EXISTS
                if (warp == null) {
                    player.sendMessage(warpMessage(warpConfig));
                    return true;
                }
                playerConfig.setLastTeleportLocation(player.getLocation());
                player.teleport(warpConfig.getWarp(warp));
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "That warp doesn't exist!");
                return true;
            }
        }

        return true;
    }

    private String warpMessage(WarpConfig warpConfig) {
        StringBuilder warpBuilder = new StringBuilder();
        warpBuilder.append(ChatColor.GOLD + "SUPER  G A Y  Warps:\n");

        if (warpConfig.getWarps().isEmpty()) {
            warpBuilder.append(ChatColor.GRAY + "There's no gay warps, yet.");
        } else {
            for (String w : warpConfig.getWarps()) {
                warpBuilder.append(ChatColor.YELLOW + "\n").append(w);
            }
        }

        return warpBuilder.toString();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        WarpConfig warpConfig = new WarpConfig(plugin);

        if (!warpConfig.getWarps().isEmpty()) return new ArrayList<>(warpConfig.getWarps());
        return new ArrayList<>();
    }
}
