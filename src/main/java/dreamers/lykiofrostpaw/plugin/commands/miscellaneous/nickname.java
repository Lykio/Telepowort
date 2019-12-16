package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// IT BEGINS
public class nickname implements CommandExecutor {
    private final Telepowort plugin;

    public nickname(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * Store args[0] into playerConfig's Nickname key and call it a day
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String name = null;

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (args.length != 0) {
            name = args[0].replaceAll("&", "§") + ChatColor.RESET;
        }

        Player player = (Player) sender;
        PlayerConfig playerConfig = new PlayerConfig(player);

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You didn't specify a nickname.");
            return true;
        }

        if (args.length >= 2) {
            player.sendMessage(ChatColor.RED + "Nicknames can't have spaces.");
            return true;
        }

        playerConfig.setNickname(name);
        player.setDisplayName(name);
        player.sendMessage("Changed your nickname. ÒwÓ");
        return true;
    }
}