package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.WarpConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warp implements CommandExecutor {
    private final Telepowort plugin;

    public warp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder warpBuilder = new StringBuilder();
        String warp = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
        }

        if (args.length != 0) {
            warp = args[0];
        }

        Player player = (Player) sender;
        WarpConfig warpConfig = new WarpConfig(plugin);

        // LIST WARPS
        if (args.length == 0) {
            warpBuilder = new StringBuilder();
            warpBuilder.append(ChatColor.GOLD + "SUPER  G A Y  Warps:\n");
            if (warpConfig.getWarps() == null) {
                warpBuilder.append(ChatColor.GOLD + "There's no gay warps, yet.");
            } else {
                for (String w : warpConfig.getWarps()) {
                    warpBuilder.append(ChatColor.YELLOW + "\n").append(w);
                }
            }

            player.sendMessage(warpBuilder.toString());
        }

        if (args.length != 0) {
            if (warpConfig.getWarps().contains(warp)) { // DO THIS IF WARP EXISTS
                if (warp == null) {
                    player.sendMessage(warpBuilder.toString());
                    return true;
                }
                player.teleport(warpConfig.getWarp(warp));
            } else {
                player.sendMessage(ChatColor.RED + "That warp doesn't exist!");
            }
        }

        return true;
    }
}
