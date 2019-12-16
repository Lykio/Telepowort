package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class WarpConfig {
    private final Telepowort plugin;
    private final Logger log;
    private File warpConfigFile;
    private FileConfiguration warpConfig;

    public WarpConfig(Telepowort plugin) {
        this.plugin = plugin;
        this.log = plugin.getLogger();
        warpConfigFile = new File(plugin.getDataFolder(), "warps.yml");
        warpConfig = YamlConfiguration.loadConfiguration(warpConfigFile);
    }

    // Create warp config
    public void createWarpConfig() {
        try {
            if (warpConfigFile.createNewFile()) {
                createWarpConfigDefaults();
                log.info("Created warp config successfully.");
            } else {
                log.info("Warp config is pre-existing.");
            }
        } catch (IOException e) {
            log.severe("Failed to create warp config.");
            e.printStackTrace();
        }
    }

    // Set warp config's defaults
    private void createWarpConfigDefaults() {
        if (warpConfigFile.length() == 0) {
            warpConfig.set("Acknowledged", true);
            warpConfig.set("Warps", null); // I know this doesn't do anything, it's for personal readability
            saveWarpConfig();
        }
    }

    // This will reload this the warp config
    public void reload() {
        saveWarpConfig();
        warpConfig = YamlConfiguration.loadConfiguration(warpConfigFile);
    }

    // An inefficient check to see if warp config exists
    public boolean exists() {
        return warpConfig.getBoolean("Acknowledged");
    }

    // A getter for warp config
    public FileConfiguration getWarpConfig() {
        return warpConfig;
    }

    // Saves any changes made through setters
    public void saveWarpConfig() {
        try {
            getWarpConfig().save(warpConfigFile);
        } catch (IOException e) {
            log.severe("Failed to save warp config.");
            e.printStackTrace();
        }
    }

    // Checks the player invoker against a warp's owner
    boolean isCreator(String warp, String player) {
        try {
            if (warpConfig.getString("Warps." + warp + ".creator").equals(player)) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    // Set getter for all warps
    Set<String> getWarps() {
        try {
            return warpConfig.getConfigurationSection("Warps").getKeys(false);
        } catch (NullPointerException e) {
            return new HashSet<>();
        }
    }

    // Location getter for a specific warp
    Location getWarp(String warp) {
        return new Location(
                Bukkit.getWorld(warpConfig.getString("Warps." + warp + ".world")),
                warpConfig.getInt("Warps." + warp + ".x") + 0.5,
                warpConfig.getInt("Warps." + warp + ".y"),
                warpConfig.getInt("Warps." + warp + ".z") + 0.5,
                warpConfig.getInt("Warps." + warp + ".yaw"),
                warpConfig.getInt("Warps." + warp + ".pitch")
        );
    }

    // Sets a new warp
    void addWarp(String warp, String player, Location loc) {
        warpConfig.set("Warps." + warp + ".creator", player);
        warpConfig.set("Warps." + warp + ".world", loc.getWorld().getName());
        warpConfig.set("Warps." + warp + ".x", loc.getBlockX());
        warpConfig.set("Warps." + warp + ".y", loc.getY());
        warpConfig.set("Warps." + warp + ".z", loc.getBlockZ());
        warpConfig.set("Warps." + warp + ".yaw", loc.getYaw());
        warpConfig.set("Warps." + warp + ".pitch", loc.getPitch());
        saveWarpConfig();
    }

    // Nulls are pre-existing warp
    void delWarp(String warp) {
        warpConfig.set("Warps." + warp, null);
        saveWarpConfig();
    }
}
