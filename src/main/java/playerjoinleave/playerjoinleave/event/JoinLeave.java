package playerjoinleave.playerjoinleave.event;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;
import playerjoinleave.playerjoinleave.Utils.util;

public class JoinLeave implements Listener {

    public PlayerJoinLeave plugin;
    public JoinLeave() {
        this.plugin = plugin;
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        FileConfiguration setting = plugin.plugin.file.getConfig();
        FileConfiguration message = plugin.plugin.message.getConfig();
        boolean join_message_enabled = setting.getBoolean("join-message-enabled");
        String join_message = util.formatText(message.getString("join-message").replace("{player}", player.getName()));
        String first_time_join_message = util.formatText(message.getString("first-time-join-message").replace("{player}", player.getName()));

        if (player.hasPlayedBefore()) {
            if (join_message_enabled) {
                event.setJoinMessage("");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(join_message);
                }
            } else {
                event.setJoinMessage("");
            }

            util.Join(player);
            return;
        } else {

            if (join_message_enabled) {
                event.setJoinMessage("");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(first_time_join_message);
                }
            } else {
                event.setJoinMessage("");
            }

            util.Join(player);
            return;
        }
    }

    @EventHandler
    public void Quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        util.Leave(player);
    }
}
