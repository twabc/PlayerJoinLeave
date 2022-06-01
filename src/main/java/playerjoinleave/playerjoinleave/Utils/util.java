package playerjoinleave.playerjoinleave.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;

import java.util.List;

public class util {

    public static PlayerJoinLeave plugin;
    public util() {
        this.plugin = plugin;
    }

    public static String formatText(String text) {
        text = ChatColor.translateAlternateColorCodes('&', text);
        for (ChatColor style : ChatColor.values())
        try {
            int from = 0;
            while (text.indexOf("&#", from) >= 0) {
                from = text.indexOf("&#", from) + 1;
                text = text.replace(text.substring(from - 1, from + 7),
                        net.md_5.bungee.api.ChatColor.of(text.substring(from, from + 7)).toString());
            }
        } catch (Throwable t) {
        }
        return text;
    }

    public static void Leave(Player target) {
        FileConfiguration message = plugin.plugin.message.getConfig();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(util.formatText(message.getString("quit-message").replace("{player}", target.getName())));
        }
    }

    public static void Join(Player player) {
        FileConfiguration file = plugin.plugin.file.getConfig();
        FileConfiguration message = plugin.plugin.message.getConfig();

        boolean title_enabled = file.getBoolean("Title-enabled");
        String title = util.formatText(message.getString("Title-message"));
        String sub_title = util.formatText(message.getString("Sub-Title-message"));
        int fadeln = file.getInt("fadeln");
        int stay = file.getInt("stay");
        int fadeOut = file.getInt("fadeOut");

        boolean join_commands_enabled = file.getBoolean("join-commands-enabled");
        List<String> join_commands = file.getStringList("join_commands");

        boolean sound_enabled = file.getBoolean("Sound-enabled");
        Sound sound_type = Sound.valueOf(file.getString("Sound-type"));

        boolean message_login_enabled = file.getBoolean("message-login-enabled");

        if (message_login_enabled) {
            for (String message_login : file.getStringList("message-login")) {
                player.sendMessage(util.formatText(message_login));
            }
        }

        if (title_enabled) {
            player.sendTitle(title, sub_title, fadeln, stay, fadeOut);
        }

        if (join_commands_enabled) {
            for (String join_command : join_commands) {
                player.sendMessage(join_command);
            }
        }

        if (sound_enabled) {
            player.playSound(player.getLocation(), sound_type, 2.0F, 1.0F);
        }
    }

    public static void Background_unavailable() {
        Bukkit.getServer().getLogger().info("後台無法使用此指令");
    }
}
