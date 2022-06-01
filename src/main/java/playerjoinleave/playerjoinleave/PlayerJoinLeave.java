package playerjoinleave.playerjoinleave;

import org.bukkit.plugin.java.JavaPlugin;
import playerjoinleave.playerjoinleave.File.about;
import playerjoinleave.playerjoinleave.File.setting;
import playerjoinleave.playerjoinleave.File.message;
import playerjoinleave.playerjoinleave.command.TabComplete;
import playerjoinleave.playerjoinleave.command.joinleave;
import playerjoinleave.playerjoinleave.command.msg;
import playerjoinleave.playerjoinleave.event.CommandPreprocess;
import playerjoinleave.playerjoinleave.event.JoinLeave;
import playerjoinleave.playerjoinleave.event.PlayerSendMessage;

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
        this.getServer().getPluginManager().registerEvents(new PlayerSendMessage(), this);
        this.getServer().getPluginManager().registerEvents(new CommandPreprocess(), this);
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
        getCommand("m").setExecutor(new msg());
        getCommand("msg").setExecutor(new msg());
        getCommand("tell").setExecutor(new msg());

    }
}
