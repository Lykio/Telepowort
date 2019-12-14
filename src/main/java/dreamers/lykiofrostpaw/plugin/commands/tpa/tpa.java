package dreamers.lykiofrostpaw.plugin.commands.tpa;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
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
        String receiver = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can teleport.");
            return true;
        }

        if (args.length != 0) {
            receiver = args[0];
        }

        if (Bukkit.getPlayer(receiver) == null) {
            sender.sendMessage(ChatColor.RED + "Choose a player.");
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
}
