package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import dreamers.lykiofrostpaw.plugin.commands.warp.WarpConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class telepowort implements CommandExecutor, TabCompleter {
    private final Telepowort plugin;

    public telepowort(Telepowort plugin) {
        this.plugin = plugin;
    }

    /*
     * Give the user information about the plugin
     */

    private final String[] commands = {
            "version",
            "reload"
    };

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (args[0] != null && args[0].equalsIgnoreCase(commands[0])) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ChatColor.BOLD + "Telepowort is on");
                stringBuilder.append(ChatColor.BOLD + "\nVersion: " + ChatColor.RESET + ChatColor.ITALIC + plugin.getDescription().getVersion());
                stringBuilder.append(ChatColor.BOLD + "\nAPI Version: " + ChatColor.RESET + ChatColor.ITALIC + plugin.getDescription().getAPIVersion());

                sender.sendMessage(stringBuilder.toString());
                return true;
            }

            if (args[0] != null && args[0].equalsIgnoreCase(commands[1])) {
                plugin.reloadConfig();

                if (sender instanceof Player) {
                    PlayerConfig playerConfig = new PlayerConfig((Player) sender);
                    playerConfig.reload();
                }

                WarpConfig warpConfig = new WarpConfig(plugin);
                warpConfig.reload();

                plugin.getLogger().warning("Telepowort reloaded!");
                sender.sendMessage(ChatColor.BOLD + "Telepowort reloaded!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            sender.sendMessage(ChatColor.BOLD + "Arguments are version and reload.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>(Arrays.asList(commands));
    }
}
