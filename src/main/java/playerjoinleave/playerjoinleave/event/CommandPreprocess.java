package playerjoinleave.playerjoinleave.event;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;
import playerjoinleave.playerjoinleave.Utils.util;


public class CommandPreprocess implements Listener {

    public PlayerJoinLeave plugin;
    public CommandPreprocess() {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        FileConfiguration file = plugin.plugin.message.getConfig();
        String prefix = file.getString("Prefix");

        String msg = event.getMessage();
        String[] args = msg.split(" ");

        if (Bukkit.getServer().getHelpMap().getHelpTopic(args[0]) == null) {
            event.getPlayer().sendMessage(util.formatText(prefix + file.getString("unknown-command")));
            event.setCancelled(true);
        }
    }
}
