package playermessage.playermessage.event;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import playermessage.playermessage.PlayerMessage;
import playermessage.playermessage.Utils.util;

public class PlayerSendMessage implements Listener {

    public PlayerMessage plugin;

    public PlayerSendMessage() {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void sendmessage(PlayerChatEvent event) {
         Player player = event.getPlayer();
         FileConfiguration messageFile = plugin.plugin.message.getConfig();
         String message = util.formatText(messageFile.getString("Player-send-message"));
         String placeholderMessage = PlaceholderAPI.setPlaceholders(player, message);
         if (player.hasPermission("PlayerMessage.ChatColor")) {
            event.setFormat(util.formatText(placeholderMessage) + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
         } else {
            event.setFormat(util.formatText(placeholderMessage) + event.getMessage());
         }
    }
}

