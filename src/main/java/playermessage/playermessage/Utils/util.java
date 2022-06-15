package playermessage.playermessage.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playermessage.playermessage.PlayerMessage;

import java.util.List;

public class util<taskID> {

    public static PlayerMessage plugin;
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
        FileConfiguration setting = plugin.plugin.setting.getConfig();
        FileConfiguration message = plugin.plugin.message.getConfig();

        boolean title_enabled = setting.getBoolean("Title-enabled");
        String title = util.formatText(message.getString("Title-message"));
        String sub_title = util.formatText(message.getString("Sub-Title-message"));
        int fadeln = setting.getInt("fadeln");
        int stay = setting.getInt("stay");
        int fadeOut = setting.getInt("fadeOut");

        boolean join_commands_enabled = setting.getBoolean("join-commands-enabled");
        List<String> join_commands = message.getStringList("join_commands");

        boolean join_sound_enabled = setting.getBoolean("Join-Sound-enabled");
        Sound join_sound_type = Sound.valueOf(setting.getString("Join-Sound-Type"));

        boolean message_login_enabled = setting.getBoolean("message-login-enabled");

        if (message_login_enabled) {
            for (String message_login : message.getStringList("message-login")) {
                player.sendMessage(util.formatText(message_login));
            }
        }

        if (title_enabled) {
            player.sendTitle(title, sub_title, fadeln, stay, fadeOut);
        }

        if (join_commands_enabled) {
            for (String join_command : join_commands) {
                player.chat(join_command);
            }
        }

        if (join_sound_enabled) {
            player.playSound(player.getLocation(), join_sound_type, 2.0F, 1.0F);
        }
    }

    public static void Background_unavailable() {
        Bukkit.getServer().getLogger().info("後台無法使用此指令");
    }

    public static int task;
    public static void startCountdown(){
        FileConfiguration message = plugin.plugin.message.getConfig();
        FileConfiguration setting = plugin.plugin.setting.getConfig();
        int interval = (setting.getInt("Announce-interval") * 20);
        int list_size = message.getStringList("Announcements-list").size();
        boolean sound_enable = setting.getBoolean("Announce-Sound-enabled");
        task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin.plugin, new Runnable() {
            int number = 0;
            public void run() {
               String Announcement_list = message.getStringList("Announcements-list").get(number);
               for (String Announcements_message :  message.getStringList("Announcements-message." + Announcement_list)) {
                   for (Player player : Bukkit.getOnlinePlayers()) {
                       player.sendMessage(util.formatText(Announcements_message));
                       if (sound_enable) {
                           player.playSound(player.getLocation(), Sound.valueOf(setting.getString("Announce-Sound-type")), 1F , 1F);
                       }
                   }
               }
               if (number < message.getStringList("Announcements-list").size()) {
                   number = number + 1;
               }
               if (number == list_size) {
                   number = 0;
               }
            }
        },interval,interval);
    }

    public static void stopCountdown(){
        Bukkit.getServer().getScheduler().cancelTask(task);
    }
}
