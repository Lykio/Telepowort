package dreamers.lykiofrostpaw.plugin.commands.tpa;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class tpa implements CommandExecutor, Listener {
    private final Telepowort plugin;
    private HashMap<Player, Player> tpaQ = new HashMap<Player, Player>();

    public tpa(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        String playerName = null;
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You can only teleport to existing online players!");
        } else {
            playerName = args[0];
        }


        return false;
    }

}
