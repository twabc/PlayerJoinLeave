package playerjoinleave.playerjoinleave.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class setting {

    private PlayerJoinLeave plugin;
    private FileConfiguration rankfile = null;
    private File configFile = null;


    public setting(PlayerJoinLeave plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "setting.yml");

        this.rankfile = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream a = this.plugin.getResource("setting.yml");
        if(a != null) {
            YamlConfiguration b = YamlConfiguration.loadConfiguration(new InputStreamReader(a));
            this.rankfile.setDefaults(b);
        }
    }

    public FileConfiguration getConfig() {
        if(this.rankfile == null)
            reloadConfig();
        return this.rankfile;
    }

    public void saveConfig() {
        if(this.rankfile == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "無法儲存資料夾" + this.getConfig(), e);
        }
    }

    public void saveDefaultConfig() {
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "setting.yml");

        if(!this.configFile.exists()) {
            this.plugin.saveResource("setting.yml", false);
        }
    }
}
