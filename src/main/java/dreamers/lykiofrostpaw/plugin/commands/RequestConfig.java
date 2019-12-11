package dreamers.lykiofrostpaw.plugin.commands;

import dreamers.lykiofrostpaw.plugin.Telepowort;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class RequestConfig {
    private final Telepowort plugin;
    private File requestConfigFile;
    private FileConfiguration requestConfig;

    public RequestConfig(Telepowort plugin) {
        this.plugin = plugin;
        requestConfigFile = new File(plugin.getDataFolder() + "requests.yml");
        requestConfig = YamlConfiguration.loadConfiguration(requestConfigFile);
    }

    public void createWarpConfig() {
        try {
            if (requestConfigFile.createNewFile()) {
                createRequestsConfigDefaults();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createRequestsConfigDefaults() {
        if (requestConfigFile.length() == 0) {
            requestConfig.set("Acknowledged", true);
            requestConfig.set("Requests", null);
            saveRequestConfig();
        }
    }

    public boolean exists() {
        return requestConfig.getBoolean("Acknowledged");
    }

    public FileConfiguration getRequestConfig() {
        return requestConfig;
    }

    public void saveRequestConfig() {
        try {
            createRequestsConfigDefaults();
            getRequestConfig().save(requestConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getRequests() {
        return requestConfig.getConfigurationSection("Requests").getKeys(false);
    }

    public String getRequest(String request) {
        return requestConfig.getConfigurationSection("Requests").getString(request);
    }

    public void addRequest(String request, String requestee) {
        requestConfig.set("Requests." + request, requestee);
        saveRequestConfig();
    }

    public void delRequest(String request) {
        requestConfig.set("Requests." + request, null);
        saveRequestConfig();
    }
}
