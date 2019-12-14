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

public class setwarp implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public setwarp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String warp = null;

        if (args.length != 0) {
            warp = args[0];
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create warps!");
            return true;
        }

        if (warp == null) {
            sender.sendMessage(ChatColor.RED + "Your warp needs a name.");
            return false;
        }

        Player player = (Player) sender;
        WarpConfig warpConfig = new WarpConfig(plugin);


        warpConfig.addWarp(warp, player.getName(), player.getLocation());
        sender.sendMessage(ChatColor.RED + "Created new warp: " + ChatColor.AQUA + warp);

        warpConfig.reload();
        return true;
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
