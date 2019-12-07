package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;
import java.util.Map;

public class warp implements CommandExecutor {
    private final Telepowort plugin;
    private FileConfiguration warpConfig = null;
    private File warpConfigFile = null;

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

    private Location translateArgs(List args) {

        World world = Bukkit.getWorld(args.get(1).toString());
        double x = Double.parseDouble(args.get(2).toString());
        double y = Double.parseDouble(args.get(3).toString());
        double z = Double.parseDouble(args.get(4).toString());
        float yaw = Float.parseFloat(args.get(5).toString());
        float pitch = Float.parseFloat(args.get(6).toString());

        return new Location(world, x, y, z, yaw, pitch);
    }

    public warp(Telepowort plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender player, Command command, String label, String[] args) {
        if (!(player instanceof Player)) {
            player.sendMessage("Only players can invoke that command!");
            return true;
        }

        // List warps
        if (args.length == 0) {
            Map<String, Object> warpConfig = getWarps().getValues(false);
            StringBuilder warpBuilder = new StringBuilder();
            warpBuilder.append(ChatColor.GOLD + "Warps:");
            warpConfig.forEach((warp, location) -> warpBuilder.append(ChatColor.YELLOW + "\n" + warp));
            player.sendMessage(warpBuilder.toString());
        }

        // Teleport
        if (args.length == 1) {
            ((Player) player).teleport(translateArgs(warpConfig.getList(args[0])));
            return true;
        }

        return false;
    }
}
