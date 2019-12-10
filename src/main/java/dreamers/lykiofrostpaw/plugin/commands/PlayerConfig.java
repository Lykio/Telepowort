package dreamers.lykiofrostpaw.plugin.commands;

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
    public Player player;
    public FileConfiguration playerConfig;
    public File playerConfigFile;

    public PlayerConfig(Player player) {
        this.player = player;
        this.playerID = this.player.getUniqueId();
        playerConfigFile = new File("plugins/Telepowort/players/" + playerID + ".yml");
        playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
    }

    public void createPlayerConfig() {
        try {
            if (playerConfigFile.createNewFile()) {
                System.out.println("Player " + this.playerID + "didn't exist, created new user config successfully!");
            } else {
                System.out.println("Player " + this.playerID + "is pre-existing.");
            }
        } catch (IOException exception) {
            System.out.println("Failed to create new Player config for " + this.playerID + ".\n");
            exception.printStackTrace();
        }
    }

    public void createPlayerConfigDefaults() {
        createPlayerConfig();
        if (playerConfigFile.length() <= 0) {
            playerConfig.set("Acknowledged", true);
            playerConfig.set("Name", player.getName());
            playerConfig.set("Nickname", null);
            playerConfig.set("Last-Teleport-Location", null);
            playerConfig.set("Homes", null);
        }
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public void savePlayerConfig() {
        try {
            createPlayerConfigDefaults();
            getPlayerConfig().save(playerConfigFile);
        } catch (IOException exception) {
            System.out.println("Failed to save Player config for " + this.playerID + ".\n");
            exception.printStackTrace();
        }
    }

    public boolean exists() {
        return playerConfig.getBoolean("Acknowledged");
    }

    public String getName() {
        return playerConfig.getString("Name");
    }

    public String getNickname() {
        return playerConfig.getString("Nickname");
    }

    public boolean setNickname(String nickname) {
        playerConfig.set("Nickname", nickname);
        System.out.println("Player " + this.player.getDisplayName() + "changed their nickname to " + nickname);

        return playerConfig.get("Nickname") != null;
    }

    public Location getLastTeleportLocation() {
        return playerConfig.getLocation("Last-Teleport-Location");
    }

    public void setLastTeleportLocation(Location LastLeleportLocation) {
        playerConfig.set("Last-Teleport-Location", LastLeleportLocation);
    }

    public Set<String> getHomes() {
        return playerConfig.getConfigurationSection("Homes").getKeys(false);
    }

    public boolean addHome(Location newhome) {
        playerConfig.set("Homes." + newhome + ".world", newhome.getWorld());
        playerConfig.set("Homes." + newhome + ".x", newhome.getBlockX());
        playerConfig.set("Homes." + newhome + ".y", newhome.getBlockY());
        playerConfig.set("Homes." + newhome + ".z", newhome.getBlockZ());
        playerConfig.set("Homes." + newhome + ".yaw", newhome.getYaw());
        playerConfig.set("Homes." + newhome + ".pitch", newhome.getPitch());

        return playerConfig.get("Home" + newhome) != null;
    }

    public boolean delHome(Location newhome) {
        playerConfig.set("Homes." + newhome, null);

        return playerConfig.get("Homes." + newhome) == null;
    }
}