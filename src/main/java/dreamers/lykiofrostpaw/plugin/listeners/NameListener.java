package dreamers.lykiofrostpaw.plugin.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class NameListener implements Listener {

    private final Plugin plugin;
    private FileConfiguration nickConfig = null;
    private File nickConfigFile;

    public NameListener(Plugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void reloadNicknames() {
        File nickConfigFile = new File(this.plugin.getDataFolder(), "nickname.yml");
        nickConfig = YamlConfiguration.loadConfiguration(nickConfigFile);
    }

    public FileConfiguration getNicknames() {
        reloadNicknames();
        return nickConfig;
    }

    public void saveNicknames() {
        if (nickConfigFile == null) {
            nickConfigFile = new File(this.plugin.getDataFolder(), "nickname.yml");
        }

        try {
            nickConfig.save(nickConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Map<String, Object> nicknames = getNicknames().getValues(false);
        String player = event.getPlayer().getName();
        System.out.println("debugg");
        if (nicknames.containsKey(player)) {
            event.getPlayer().setDisplayName((String) nicknames.get(player));

        }
    }

}
