package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class back implements CommandExecutor, Listener {
    private final Telepowort plugin;
    private PlayerTeleportEvent event;

    public back(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You need to be a player to use back!");
            return true;
        }

        Player player = (Player) sender;


        return true;
    }

    @EventHandler
    public void onHome(PlayerTeleportEvent event) {
        this.event = event;
        System.out.println("Fired");
    }
}
