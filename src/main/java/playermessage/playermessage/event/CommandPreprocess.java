package playermessage.playermessage.event;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import playermessage.playermessage.PlayerMessage;
import playermessage.playermessage.Utils.util;


public class CommandPreprocess implements Listener {

    public PlayerMessage plugin;
    public CommandPreprocess() {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        FileConfiguration message = plugin.plugin.message.getConfig();
        FileConfiguration setting = plugin.plugin.setting.getConfig();
        String prefix = message.getString("Prefix");

        String msg = event.getMessage();
        String[] args = msg.split(" ");


        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            if (setting.getBoolean("unknown-command-enabled")) {
                event.getPlayer().sendMessage(util.formatText(prefix + message.getString("unknown-command")));
                event.setCancelled(true);
            }
        }
    }
}
