package dreamers.lykiofrostpaw.plugin.commands.tpa;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class tpa implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public tpa(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * Glorified /tp, but with no permissions
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String receiver = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can teleport.");
            return true;
        }

        if (args.length != 0) {
            receiver = args[0];
        }

        try {
            if (Bukkit.getPlayer(receiver) == null) {
                sender.sendMessage(ChatColor.RED + "Choose a player.");
                return true;
            }
        } catch (IllegalArgumentException e) {
            sender.sendMessage(ChatColor.RED + "You need to choose a player.");
            return true;
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);
        Player target = Bukkit.getPlayer(receiver);
        if (target != null) {
            playerConfig.setLastTeleportLocation(player.getLocation());
            player.teleport(target.getLocation());
        } else {
            player.sendMessage(ChatColor.YELLOW + "That player doesn't uwu!");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
