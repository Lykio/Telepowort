package dreamers.lykiofrostpaw.plugin.commands.miscellaneous;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

// IT BEGINS
public class nickname implements CommandExecutor {
    private final Telepowort plugin;
    private FileConfiguration nickConfig = null;
    private File nickConfigFile = null;

    private void reloadNicknames() {
        nickConfigFile = new File(this.plugin.getDataFolder(), "nickname.yml");
        nickConfig = YamlConfiguration.loadConfiguration(nickConfigFile);
    }

    public FileConfiguration getNicknames() {
        if (nickConfig == null) {
            reloadNicknames();
        }
        return nickConfig;
    }

    public void saveNicknames() {
        if (nickConfigFile == null) {
            nickConfigFile = new File(this.plugin.getDataFolder(), "nickname.yml");
        }

        try {
            nickConfig.save(nickConfigFile);
        } catch (IOException e) {
            this.plugin.getLogger().severe(e.getMessage());
        }
    }

    public nickname(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You didn't specify a nickname.");
            return true;
        }
        if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "Nicknames can't have spaces.");
            return true;
        }

        FileConfiguration nicknames = getNicknames();
        nicknames.set(player.getName(), args[0]);
        saveNicknames();
        player.setDisplayName((String) nicknames.get(player.getName()));
        player.sendMessage("Changed your nickname. :)");
        return true;
    }
}