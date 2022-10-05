package playermessage.playermessage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import playermessage.playermessage.File.about;
import playermessage.playermessage.File.setting;
import playermessage.playermessage.File.message;
import playermessage.playermessage.Utils.util;
import playermessage.playermessage.command.TabComplete;
import playermessage.playermessage.command.playermessage;
import playermessage.playermessage.command.msg;
import playermessage.playermessage.event.JoinLeave;
import playermessage.playermessage.event.PlayerSendMessage;


public final class PlayerMessage extends JavaPlugin {

    public static PlayerMessage plugin;
    public static setting setting;
    public static message message;
    public static about aboutfile;

    @Override
    public void onEnable() {
        setupCommand();

        plugin = this;
        this.getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerSendMessage(), this);
        this.setting = new setting(this);
        this.message = new message(this);
        this.aboutfile = new about(this);
    }

    @Override
    public void onDisable() {
    }

    public void setupCommand() {
        getCommand("playermessage").setExecutor(new playermessage());
        getCommand("playermessage").setTabCompleter(new TabComplete());
        getCommand("m").setExecutor(new msg());
        getCommand("msg").setExecutor(new msg());
        getCommand("tell").setExecutor(new msg());
    }
}
