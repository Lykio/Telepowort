package dreamers.lykiofrostpaw.plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
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
        if (playerConfigFile.length() <= 0) {
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
        playerConfig.set("Last-Teleport-Location.world", loc.getWorld());
        playerConfig.set("Last-Teleport-Location.x", loc.getBlockX());
        playerConfig.set("Last-Teleport-Location.y", loc.getBlockY());
        playerConfig.set("Last-Teleport-Location.z", loc.getBlockZ());
        playerConfig.set("Last-Teleport-Location.yaw", loc.getYaw());
        playerConfig.set("Last-Teleport-Location.pitch", loc.getPitch());
    }

}