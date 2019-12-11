package dreamers.lykiofrostpaw.plugin.commands.home;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor {
    private final Telepowort plugin;

    public sethome(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String home = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can create homes!");
        }

        if (args.length != 0) {
            home = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        if (home == null) {
            sender.sendMessage(ChatColor.RED + "Your home needs a name.");
        }

        playerConfig.addHome(home, player.getLocation());
        player.sendMessage(ChatColor.YELLOW + "Created new home: " + ChatColor.AQUA + home);

        return true;
    }
}
