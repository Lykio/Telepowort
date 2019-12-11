package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.WarpConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class warp implements CommandExecutor {
    private final Telepowort plugin;

    public warp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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
            try {
                Set<String> warpMap = warpConfig.getWarps();
                StringBuilder warpBuilder = new StringBuilder();

                warpBuilder.append(ChatColor.GOLD + "SUPER  G A Y  Warps\n");
                warpMap.forEach((w) -> warpBuilder.append(ChatColor.YELLOW + "\n").append(w));

                sender.sendMessage(warpBuilder.toString());
            } catch (NullPointerException e) {
                sender.sendMessage(ChatColor.RED + "There are no warps yet!");
            }
        }


        if (warpConfig.getWarps().contains(warp)) { // DO THIS IF WARP EXISTS
            player.teleport(warpConfig.getWarp(warp));
        } else {
            player.sendMessage(ChatColor.RED + "That warp doesn't exist!");
        }

        return true;
    }
}
