package playerjoinleave.playerjoinleave;

import org.bukkit.plugin.java.JavaPlugin;
import playerjoinleave.playerjoinleave.File.about;
import playerjoinleave.playerjoinleave.File.setting;
import playerjoinleave.playerjoinleave.File.message;
import playerjoinleave.playerjoinleave.command.TabComplete;
import playerjoinleave.playerjoinleave.command.joinleave;
import playerjoinleave.playerjoinleave.event.JoinLeave;

public final class PlayerJoinLeave extends JavaPlugin {

    public static PlayerJoinLeave plugin;
    public static setting file;
    public static message message;
    public static about aboutfile;

    @Override
    public void onEnable() {
        setupCommand();

        plugin = this;
        this.getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        this.file = new setting(this);
        this.message = new message(this);
        this.aboutfile = new about(this);
    }

    @Override
    public void onDisable() {
    }

    public void setupCommand() {
        getCommand("joinleave").setExecutor(new joinleave());
        getCommand("joinleave").setTabCompleter(new TabComplete());
    }
}
