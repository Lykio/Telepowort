package dreamers.lykiofrostpaw.plugin.listeners;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import dreamers.lykiofrostpaw.plugin.commands.PlayerConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {
    private final Telepowort plugin;

    public PlayerLogin(Telepowort plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler // Change their display name to nickname on LoginEvent
    public void setNick(PlayerLoginEvent event) {
        PlayerConfig playerConfig = new PlayerConfig(event.getPlayer());
        if (playerConfig.getNickname() != null) {
            event.getPlayer().setDisplayName(playerConfig.getNickname());
        }
    }

    @EventHandler // Creating their config, if it doesn't exist, on LoginEvent
    public void createConfig(PlayerLoginEvent event) {
        PlayerConfig playerConfig = new PlayerConfig(event.getPlayer());
        playerConfig.createPlayerConfig();
    }

}
