package dreamers.lykiofrostpaw.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class PlayerConfig {
    private final UUID playerID;
    private Player player;
    private File playerConfigFile;
    private FileConfiguration playerConfig;

    public PlayerConfig(Player player) {
        this.player = player;
        this.playerID = player.getUniqueId();
        playerConfigFile = new File("plugins/Telepowort/users/" + playerID);
        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
    }

    public void createPlayerConfig() {
        try {
            if (playerConfigFile.createNewFile()) {
                createPlayerConfigDefaults();
                System.out.println("Player " + playerID + "didn't exist, created new user config successfully!");
            } else {
                System.out.println("Player " + playerID + "is pre-existing.");
            }
        } catch (IOException exception) {
            System.out.println("Failed to create new Player config for " + playerID + ".\n");
            exception.printStackTrace();
        }
    }

    private void createPlayerConfigDefaults() {
        if (playerConfigFile.length() == 0) {
            playerConfig.set("Acknowledged", true);
            playerConfig.set("Name", player.getName());
            playerConfig.set("Nickname", null);
            playerConfig.set("Last-Teleport-Location", null);
            playerConfig.set("Homes", null);
            savePlayerConfig();
        }
    }

    public boolean exists() {
        return playerConfig.getBoolean("Acknowledged");
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public void savePlayerConfig() {
        try {
            getPlayerConfig().save(playerConfigFile);
        } catch (IOException exception) {
            System.out.println("Failed to save Player config for " + this.playerID + ".\n");
            exception.printStackTrace();
        }
    }

    public String getName() {
        return playerConfig.getString("Name");
    }

    public String getNickname() {
        return playerConfig.getString("Nickname");
    }

    public void setNickname(String nickname) {
        playerConfig.set("Nickname", nickname);
        savePlayerConfig();
        System.out.println("Player " + player.getDisplayName() + "changed their nickname to " + nickname);
    }

    public Location getLastTeleportLocation() {
        return new Location(
                Bukkit.getWorld(playerConfig.getString("Last-Teleport-Location.world")),
                playerConfig.getInt("Last-Teleport-Location.x"),
                playerConfig.getInt("Last-Teleport-Location.y"),
                playerConfig.getInt("Last-Teleport-Location.z"),
                playerConfig.getInt("Last-Teleport-Location.yaw"),
                playerConfig.getInt("Last-Teleport-Location.pitch")
        );
    }

    public void setLastTeleportLocation(Location loc) {
        playerConfig.set("Last-Teleport-Location.world", loc.getWorld().getName());
        playerConfig.set("Last-Teleport-Location.x", loc.getBlockX());
        playerConfig.set("Last-Teleport-Location.y", loc.getBlockY());
        playerConfig.set("Last-Teleport-Location.z", loc.getBlockZ());
        playerConfig.set("Last-Teleport-Location.yaw", loc.getYaw());
        playerConfig.set("Last-Teleport-Location.pitch", loc.getPitch());
        savePlayerConfig();
    }

    public Set<String> getHomes() {
        return playerConfig.getConfigurationSection("Homes").getKeys(false);
    }

    public Location getHome(String home) {
        return new Location(
                Bukkit.getWorld(playerConfig.getString("Homes." + home + ".world")),
                playerConfig.getInt("Homes." + home + ".x"),
                playerConfig.getInt("Homes." + home + ".y"),
                playerConfig.getInt("Homes." + home + ".z"),
                playerConfig.getInt("Homes." + home + ".yaw"),
                playerConfig.getInt("Homes." + home + ".pitch")
        );
    }

    public void addHome(String home, Location loc) {
        playerConfig.set("Homes." + home + ".world", loc.getWorld().getName());
        playerConfig.set("Homes." + home + ".x", loc.getBlockX());
        playerConfig.set("Homes." + home + ".y", loc.getBlockY());
        playerConfig.set("Homes." + home + ".z", loc.getBlockZ());
        playerConfig.set("Homes." + home + ".yaw", loc.getYaw());
        playerConfig.set("Homes." + home + ".pitch", loc.getPitch());
        savePlayerConfig();
    }

    public void delHome(String home) {
        playerConfig.set("Homes." + home, null);
        savePlayerConfig();
    }
}