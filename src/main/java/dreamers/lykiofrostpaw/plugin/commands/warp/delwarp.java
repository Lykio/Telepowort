package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class delwarp implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public delwarp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String warp = null;

        if (args.length != 0) {
            warp = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You should be a player to delete warps.");
            return true;
        }

        if (warp == null) {
            sender.sendMessage(ChatColor.RED + "You need to specify a warp.");
            return true;
        }

        Player player = (Player) sender;
        WarpConfig warpConfig = new WarpConfig(plugin);

        if (warpConfig.getWarps().contains(warp)) {
            if (warpConfig.isCreator(warp, player.getName())) {
                warpConfig.delWarp(warp);
                player.sendMessage(ChatColor.RED + "Deleted warp: " + ChatColor.AQUA + warp);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "That's not your warp!");
                return true;
            }
        } else {
            player.sendMessage(ChatColor.RED + "That warp doesn't exist.");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        WarpConfig warpConfig = new WarpConfig(plugin);

        if (!warpConfig.getWarps().isEmpty()) {
            return new ArrayList<>(warpConfig.getWarps());
        }

        return new ArrayList<>();
    }
}
