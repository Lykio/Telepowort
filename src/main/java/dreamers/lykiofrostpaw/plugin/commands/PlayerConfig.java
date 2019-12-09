package dreamers.lykiofrostpaw.plugin.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {
    private final UUID playerID;
    public Player player;
    public FileConfiguration playerConfig;
    public File playerConfigFile;

    public PlayerConfig(Player player) {
        this.player = player;
        this.playerID = this.player.getUniqueId();
        playerConfigFile = new File("plugins/Telepowort/data/" + playerID);
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
        if (playerConfigFile.length() <= 0) {
            playerConfig.set("Name", player.getName());
            playerConfig.set("Nickname", null);
            playerConfig.set("Last-Teleport-Location", null);
            playerConfig.set("Homes", null);
        }
    }

    public FileConfiguration PlayerConfig() {
        return playerConfig;
    }

    public void savePlayerConfig() {
        try {
            PlayerConfig().save(playerConfigFile);
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
        System.out.println("Player " + this.player.getDisplayName() + "changed their nickname to " + nickname);
    }

    public Location getLastTeleportLocation() {
        return playerConfig.getLocation("Last-Teleport-Location");
    }

}