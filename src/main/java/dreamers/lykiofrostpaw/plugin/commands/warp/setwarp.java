package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setwarp implements CommandExecutor {
    private final Telepowort plugin;

    public setwarp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String warp = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create warps!");
            return true;
        }

        if (args.length != 0) {
            warp = args[0];
        }

        Player player = (Player) sender;
        WarpConfig warpConfig = new WarpConfig(plugin);

        if (warp == null) {
            sender.sendMessage(ChatColor.RED + "Your warp needs a name.");
            return false;
        }

        warpConfig.addWarp(warp, player.getName(), player.getLocation());
        sender.sendMessage(ChatColor.YELLOW + "Created new warp: " + ChatColor.GOLD + warp);

        warpConfig.reload();
        return true;
    }

}
