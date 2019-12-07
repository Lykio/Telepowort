package dreamers.lykiofrostpaw.plugin.commands.warp;

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

public class delwarp implements CommandExecutor {
    private final Telepowort plugin;
    private FileConfiguration warpConfig = null;
    private File warpConfigFile = null;

    public delwarp(Telepowort plugin) {
        this.plugin = plugin;
    }

    private void reloadWarps() {
        warpConfigFile = new File(this.plugin.getDataFolder(), "warp.yml");
        warpConfig = YamlConfiguration.loadConfiguration(warpConfigFile);
    }

    private FileConfiguration getWarps() {
        if (warpConfig == null) {
            reloadWarps();
        }
        return warpConfig;
    }

    private void saveWarps() {
        if (warpConfigFile == null) {
            warpConfigFile = new File(this.plugin.getDataFolder(), "warp.yml");
        }

        try {
            warpConfig.save(warpConfigFile);
        } catch (IOException e) {
            this.plugin.getLogger().severe(e.getMessage());
        }
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (player instanceof Player) {
            player.sendMessage(ChatColor.RED + "You need to be a player to delete warps.");
            return true;
        }
        if (args[0] == null) {
            player.sendMessage(ChatColor.RED + "You need to specify a warp you created.");
            return true;
        }

        FileConfiguration pendingConfig = getWarps();
        Player sender = (Player) player;

        if (sender.getUniqueId().equals(pendingConfig.getList(args[0]).get(0))) {
            pendingConfig.set(args[0], null);
        } else {
            sender.sendMessage(ChatColor.RED + "That's not your to delete!");
            return true;
        }

        return false;
    }
}
