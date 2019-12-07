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
import java.util.Arrays;

public class setwarp implements CommandExecutor {
    private final Telepowort plugin;
    private FileConfiguration warpConfig = null;
    private File warpConfigFile = null;

    public setwarp(Telepowort plugin) {
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
        if (!(player instanceof Player)) {
            player.sendMessage(ChatColor.RED + "Only players can create warps!");
            return true;
        }

        if (args[0] == null) {
            player.sendMessage(ChatColor.RED + "Your warp needs a name.");
            return true;
        }

        FileConfiguration warpConfig = getWarps();
        Player sender = (Player) player;
        String[] warpDetails = {
                sender.getUniqueId().toString(),//0 (ownership check)
                sender.getWorld().toString(),//1
                String.valueOf(sender.getLocation().getBlockX()),//2
                String.valueOf(sender.getLocation().getBlockY()),//3
                String.valueOf(sender.getLocation().getBlockZ()),//4
                String.valueOf(sender.getLocation().getYaw()),//5
                String.valueOf(sender.getLocation().getPitch())//6
        };

        if (warpConfig.contains(args[0]) && sender.getUniqueId().equals(warpConfig.getList(args[0]).get(0))) {
            sender.sendMessage(ChatColor.YELLOW + "Warp " + args[0] + " changed.");
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Warp " + args[0] + "created!");
        }

        warpConfig.set(args[0], Arrays.asList(warpDetails));
        saveWarps();
        reloadWarps();

        return false;
    }
}
