package dreamers.lykiofrostpaw.plugin.commands.tpa;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import dreamers.lykiofrostpaw.plugin.commands.RequestConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpahere implements CommandExecutor {
    private final Telepowort plugin;

    public tpahere(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can invoke that command!");
            return true;
        }

        if (args.length != 0) {
            name = args[0];
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);
        RequestConfig requestConfig = new RequestConfig(plugin);

        if (plugin.getServer().getOnlinePlayers().contains(player)) {
            player.sendMessage(ChatColor.RED + "You can only teleport to existing online players!");
            return true;
        }



        return false;
    }
}
