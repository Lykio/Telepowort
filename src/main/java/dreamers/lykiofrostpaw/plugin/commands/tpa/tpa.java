package dreamers.lykiofrostpaw.plugin.commands.tpa;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpa implements CommandExecutor {
    private final Telepowort plugin;
    public tpa(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String request = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can teleport.");
            return true;
        }


        if (args.length != 0) {
            request = args[0];
        }

        if (Bukkit.getPlayer(request) == null) {
            sender.sendMessage(ChatColor.RED + "That's not a player.");
            return true;
        }

        Player player = (Player) sender;
        Player requestee = Bukkit.getPlayer(request);

        if (request == null) {
            player.sendMessage(ChatColor.RED + "You need to select a player to teleport to.");
            return true;
        }

        if (!Bukkit.getOnlinePlayers().contains(requestee)) {
            player.sendMessage(ChatColor.RED + "Player needs to be online.");
            return true;
        }


        return false;
    }

}
