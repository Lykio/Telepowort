package dreamers.lykiofrostpaw.plugin.commands.warp;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
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

    private void createWarpConfigDefaults() {
        if (warpConfigFile.length() == 0) {
            warpConfig.set("Acknowledged", true);
            warpConfig.set("Warps", null); // I know this doesn't do anything, it's for personal readability
            saveWarpConfig();
        }
    }

    public void reload() {
        saveWarpConfig();
        warpConfig = YamlConfiguration.loadConfiguration(warpConfigFile);
    }

    public boolean exists() {
        return warpConfig.getBoolean("Acknowledged");
    }

    public FileConfiguration getWarpConfig() {
        return warpConfig;
    }

    public void saveWarpConfig() {
        try {
            getWarpConfig().save(warpConfigFile);
        } catch (IOException e) {
            log.severe("Failed to save warp config.");
            e.printStackTrace();
        }
    }

    public boolean isCreator(String warp, String player) {
        try {
            if (warpConfig.getString("Warps." + warp + ".creator").equals(player)) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }

        return false;
    }

    public Set<String> getWarps() {
        try {
            return warpConfig.getConfigurationSection("Warps").getKeys(false);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Location getWarp(String warp) {
        return new Location(
                Bukkit.getWorld(warpConfig.getString("Warps." + warp + ".world")),
                warpConfig.getInt("Warps." + warp + ".x"),
                warpConfig.getInt("Warps." + warp + ".y"),
                warpConfig.getInt("Warps." + warp + ".z"),
                warpConfig.getInt("Warps." + warp + ".yaw"),
                warpConfig.getInt("Warps." + warp + ".pitch")
        );
    }

    public void addWarp(String warp, String player, Location loc) {
        warpConfig.set("Warps." + warp + ".creator", player);
        warpConfig.set("Warps." + warp + ".world", loc.getWorld().getName());
        warpConfig.set("Warps." + warp + ".x", loc.getBlockX());
        warpConfig.set("Warps." + warp + ".y", loc.getBlockY());
        warpConfig.set("Warps." + warp + ".z", loc.getBlockZ());
        warpConfig.set("Warps." + warp + ".yaw", loc.getYaw());
        warpConfig.set("Warps." + warp + ".pitch", loc.getPitch());
        saveWarpConfig();
    }

    public void delWarp(String warp, String player) {
        if (player.equals(warpConfig.getString("Homes." + warp + "creator")))
            warpConfig.set("Warps." + warp, null);
        saveWarpConfig();
    }
}
