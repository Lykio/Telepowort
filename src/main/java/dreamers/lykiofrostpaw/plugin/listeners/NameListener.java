package dreamers.lykiofrostpaw.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Map;

public class NameListener implements Listener {

    private final Plugin plugin;
    private FileConfiguration nickConfig = null;

    public NameListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void reloadNicknames() {
        File nickConfigFile = new File(this.plugin.getDataFolder(), "nickname.yml");
        nickConfig = YamlConfiguration.loadConfiguration(nickConfigFile);
    }

    public FileConfiguration getNicknames() {
        if (nickConfig == null) {
            reloadNicknames();
        }
        return nickConfig;
    }

    public void onLogin(PlayerLoginEvent event) {
        // Get player nickname, if it exists
        Map<String, Object> nicknames = getNicknames().getValues(false);
        String player = event.getPlayer().getName();
        if (nicknames.containsKey(player)) {
            String nickname = (String) nicknames.get(player);
        }
    }

}
