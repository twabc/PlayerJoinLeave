package playerjoinleave.playerjoinleave.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;
import playerjoinleave.playerjoinleave.Utils.util;

import java.util.List;

public class joinleave implements CommandExecutor {

    public PlayerJoinLeave plugin;
    public joinleave() {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
               util.Background_unavailable();
               return true;
        }

        Player player = (Player) sender;
        FileConfiguration file = plugin.plugin.file.getConfig();
        FileConfiguration message = plugin.plugin.message.getConfig();
        List<String> help_list = file.getStringList("help-command-message");
        List<String> help_list_reload = file.getStringList("no-specify-file");
        String prefix = util.formatText(message.getString("Prefix"));
        String no_permission = prefix + util.formatText(file.getString("command-no-permission"));
        String join_message = util.formatText(message.getString("join-message").replace("{player}", player.getName()));
        String first_time_join_message = util.formatText(message.getString("first-time-join-message").replace("{player}", player.getName()));
        String quit_message = util.formatText(message.getString("quit-message").replace("{player}", player.getName()));
        String title_message = util.formatText(message.getString("Title-message"));
        String subtitle_message = util.formatText( message.getString("Sub-Title-message"));

        if (label.equalsIgnoreCase("joinleave")) {
            if (args.length == 0) {
               if (player.hasPermission("Playerjoinleave.info")) {
                   for (String help_message : help_list) {
                       sender.sendMessage(util.formatText(help_message));
                   }
               } else {
                   player.sendMessage(no_permission);
               }
                return true;
            }
            if (args[0].equalsIgnoreCase("reload"))
                if (sender.hasPermission("Playerjoinleave.reload")) {
                    if (args.length == 1) {
                        for (String help_message : help_list_reload) {
                            player.sendMessage(prefix + util.formatText(help_message));
                        }
                        return true;
                    }
                    if (args[1].equals("setting")) {
                        plugin.plugin.file.reloadConfig();
                        player.sendMessage(prefix + util.formatText(file.getString("reload-setting-file")));
                    }
                    if (args[1].equals("message")) {
                        plugin.plugin.message.reloadConfig();
                        player.sendMessage(prefix + util.formatText(file.getString("reload-message-file")));
                    }
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("Playerjoinleave.help")) {
                    for (String help_message : help_list) {
                        sender.sendMessage(util.formatText(help_message));
                    }
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("list")) {
                if (sender.hasPermission("Playerjoinleave.list")) {
                    String info_first_time_join_message = util.formatText(file.getString("info-first-time-join-message"));
                    String info_join_message = util.formatText( file.getString("info-join-message"));
                    String info_quit_message = util.formatText( file.getString("info-quit-message"));
                    String info_title_message = util.formatText( file.getString("info-title-message"));
                    String info_subtitle_message = util.formatText( file.getString("info-subtitle-message"));
                    player.sendMessage(prefix + info_first_time_join_message.replace("{command}", first_time_join_message));
                    player.sendMessage(prefix + info_join_message.replace("{command}", join_message));
                    player.sendMessage(prefix + info_quit_message.replace("{command}", quit_message));
                    player.sendMessage(prefix + info_title_message.replace("{command}", title_message));
                    player.sendMessage(prefix + info_subtitle_message.replace("{command}", subtitle_message));
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("info")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件名稱&f: &b&lPlayerMessage"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件版本&f: 1.0"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件設計&f: &6tw_abc"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件製作&f: &6tw_abc"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lGithub&f: &3https://github.com/twabc"));
            }
            return false;
        }

    public void SetMessage(String[] args, FileConfiguration file, FileConfiguration message_file, Player player, String target_message, String file_message) {
        String message = "";
        for (int i = 2; i < args.length; i++) {
            message += args[i];
        }
        String change_message = file.getString(file_message).replace("{message}", message); //取得成功設置後系統發送的訊息
        player.sendMessage(util.formatText(change_message));
        message_file.set(target_message, message);
        plugin.plugin.message.saveConfig();
    }
}
