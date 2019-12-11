package dreamers.lykiofrostpaw.plugin.commands;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class WarpConfig {
    private final Telepowort plugin;
    private File warpConfigFile;
    private FileConfiguration warpConfig;

    public WarpConfig(Telepowort plugin) {
        this.plugin = plugin;
        warpConfigFile = new File(plugin.getDataFolder() + "warps.yml");
        warpConfig = YamlConfiguration.loadConfiguration(warpConfigFile);
    }

    public void createWarpConfig() {
        try {
            if (warpConfigFile.createNewFile()) {
                createWarpConfigDefaults();
                System.out.println("Created warp config successfully.");
            } else {
                System.out.println("Warp filed is pre-existing.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create warp config.");
            e.printStackTrace();
        }
    }

    private void createWarpConfigDefaults() {
        if (warpConfigFile.length() == 0) {
            warpConfig.set("Acknowledged", true);
            warpConfig.set("Warps", null);
            saveWarpConfig();
        }
    }

    public boolean exists() {
        return warpConfig.getBoolean("Acknowledged");
    }

    public FileConfiguration getWarpConfig() {
        return warpConfig;
    }

    public void saveWarpConfig() {
        try {
            createWarpConfigDefaults();
            getWarpConfig().save(warpConfigFile);
        } catch (IOException e) {
            System.out.println("Failed to save warp config.");
            e.printStackTrace();
        }
    }

    public Set<String> getWarps() {
        return warpConfig.getConfigurationSection("Warps").getKeys(false);
    }

    public Location getWarp(String warp) {
        return new Location(
                Bukkit.getWorld(warpConfig.getString("Homes." + warp + ".world")),
                warpConfig.getInt("Homes." + warp + ".x"),
                warpConfig.getInt("Homes." + warp + ".y"),
                warpConfig.getInt("Homes." + warp + ".z"),
                warpConfig.getInt("Homes." + warp + ".yaw"),
                warpConfig.getInt("Homes." + warp + ".pitch")
        );
    }

    public void addWarp(String warp, String player, Location loc) {
        warpConfig.set("Homes." + warp + ".creator", player);
        warpConfig.set("Homes." + warp + ".world", loc.getWorld());
        warpConfig.set("Homes." + warp + ".x", loc.getBlockX());
        warpConfig.set("Homes." + warp + ".y", loc.getBlockY());
        warpConfig.set("Homes." + warp + ".z", loc.getBlockZ());
        warpConfig.set("Homes." + warp + ".yaw", loc.getYaw());
        warpConfig.set("Homes." + warp + ".pitch", loc.getPitch());
        saveWarpConfig();
    }

    public void delWarp(String warp, String player) {
        if (player.equals(warpConfig.getString("Homes." + warp + "creator")))
            warpConfig.set("Homes." + warp, null);
        saveWarpConfig();
    }
}
