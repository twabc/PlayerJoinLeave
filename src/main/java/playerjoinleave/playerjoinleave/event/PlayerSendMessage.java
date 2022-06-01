package playerjoinleave.playerjoinleave.event;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;
import playerjoinleave.playerjoinleave.Utils.util;

public class PlayerSendMessage implements Listener {

    public PlayerJoinLeave plugin;

    public PlayerSendMessage() {
        this.plugin = plugin;
    }

    @EventHandler
    public void snedmessage(AsyncPlayerChatEvent event) {
         Player player = event.getPlayer();
         FileConfiguration file = plugin.plugin.message.getConfig();
         event.setFormat(util.formatText(PlaceholderAPI.setPlaceholders(player, file.getString("Player-send-message")) + ChatColor.translateAlternateColorCodes('&', event.getMessage())));
    }
}
