package playermessage.playermessage.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import playermessage.playermessage.PlayerMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class setting {

    private PlayerMessage plugin;
    private FileConfiguration settingfile = null;
    private File configFile = null;


    public setting(PlayerMessage plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "setting.yml");

        this.settingfile = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream a = this.plugin.getResource("setting.yml");
        if(a != null) {
            YamlConfiguration b = YamlConfiguration.loadConfiguration(new InputStreamReader(a));
            this.settingfile.setDefaults(b);
        }
    }

    public FileConfiguration getConfig() {
        if(this.settingfile == null)
            reloadConfig();
        return this.settingfile;
    }

    public void saveConfig() {
        if(this.settingfile == null || this.configFile == null)
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