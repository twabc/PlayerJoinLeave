package playerjoinleave.playerjoinleave;

import org.bukkit.plugin.java.JavaPlugin;
import playerjoinleave.playerjoinleave.File.setting;
import playerjoinleave.playerjoinleave.command.joinleavereload;
import playerjoinleave.playerjoinleave.event.JoinLeave;

public final class PlayerJoinLeave extends JavaPlugin {

    public static PlayerJoinLeave plugin;
    public static setting file;

    @Override
    public void onEnable() {
        setupCommand();

        plugin = this;
        this.getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        this.file = new setting(this);
    }

    @Override
    public void onDisable() {
    }

    public void setupCommand() {
        getCommand("playerjoinleavereload").setExecutor(new joinleavereload(this));
    }
}
