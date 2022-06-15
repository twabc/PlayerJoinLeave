package playermessage.playermessage.event;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import playermessage.playermessage.PlayerMessage;
import playermessage.playermessage.Utils.util;


    public class PlayerSendMessage implements Listener {

        public PlayerMessage plugin;

        public PlayerSendMessage() {
            this.plugin = plugin;
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void sendmessage(AsyncPlayerChatEvent event) {
             Player player = event.getPlayer();
             FileConfiguration message = plugin.plugin.message.getConfig();
             FileConfiguration tag = plugin.plugin.tag.getConfig();
                 if (tag.getString(player.getName()) != null) {
                     String prefix = tag.getString(player.getName());
                     if (player.hasPermission("PlayerMessage.ChatColor")) {
                         event.setFormat(util.formatText(PlaceholderAPI.setPlaceholders(player, message.getString("Player-send-message").replace("{Tag}", util.formatText(prefix))) + ChatColor.translateAlternateColorCodes('&', event.getMessage())));
                     } else {
                         event.setFormat(util.formatText(PlaceholderAPI.setPlaceholders(player, message.getString("Player-send-message").replace("{Tag}", util.formatText(prefix)))) + event.getMessage());
                     }
                 } else {
                     if (player.hasPermission("PlayerMessage.ChatColor")) {
                         event.setFormat(util.formatText(PlaceholderAPI.setPlaceholders(player, message.getString("Player-send-message")) + ChatColor.translateAlternateColorCodes('&', event.getMessage())));
                     } else {
                         event.setFormat(util.formatText(PlaceholderAPI.setPlaceholders(player, message.getString("Player-send-message"))) + event.getMessage());
                     }
                 }
        return;
        }
    }

