package playermessage.playermessage.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import playermessage.playermessage.PlayerMessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class tag {

    private PlayerMessage plugin;
    private FileConfiguration tagfile = null;
    private File configFile = null;


    public tag(PlayerMessage plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "tag.yml");

        this.tagfile = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream a = this.plugin.getResource("tag.yml");
        if(a != null) {
            YamlConfiguration b = YamlConfiguration.loadConfiguration(new InputStreamReader(a));
            this.tagfile.setDefaults(b);
        }
    }

    public FileConfiguration getConfig() {
        if(this.tagfile == null)
            reloadConfig();
        return this.tagfile;
    }

    public void saveConfig() {
        if(this.tagfile == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "無法儲存資料夾" + this.getConfig(), e);
        }
    }

    public void saveDefaultConfig() {
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "tag.yml");

        if(!this.configFile.exists()) {
            this.plugin.saveResource("tag.yml", false);
        }
    }
}