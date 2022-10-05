package playermessage.playermessage.command;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playermessage.playermessage.PlayerMessage;
import playermessage.playermessage.Utils.util;

import java.util.List;

public class playermessage implements CommandExecutor {

    public PlayerMessage plugin;

    public playermessage() {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            util.Background_unavailable();
            return true;
        }

        Player player = (Player) sender;
        FileConfiguration setting = plugin.plugin.setting.getConfig();
        FileConfiguration message = plugin.plugin.message.getConfig();
        List<String> help_list = message.getStringList("help-command-message");
        List<String> help_list_reload = setting.getStringList("no-specify-setting");
        String prefix = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("Prefix")));
        String no_permission = PlaceholderAPI.setPlaceholders(player, prefix + util.formatText(message.getString("command-no-permission")));
        String join_message = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("join-message").replace("{player}", player.getName())));
        String first_time_join_message = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("first-time-join-message").replace("{player}", player.getName())));
        String quit_message = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("quit-message").replace("{player}", player.getName())));
        String title_message = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("Title-message")));
        String subtitle_message = PlaceholderAPI.setPlaceholders(player, util.formatText(message.getString("Sub-Title-message")));

        if (label.equalsIgnoreCase("playermessage")) {
            if (args.length == 0) {
                if (player.hasPermission("PlayerMessage.info")) {
                    for (String help_message : help_list) {
                        sender.sendMessage(util.formatText(help_message));
                    }
                } else {
                    player.sendMessage(no_permission);
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("reload"))
                if (sender.hasPermission("PlayerMessage.reload")) {
                    if (args.length == 1) {
                        for (String help_message : help_list_reload) {
                            player.sendMessage(prefix + util.formatText(help_message));
                        }
                        return true;
                    }
                    if (args[1].equals("setting")) {
                        plugin.plugin.setting.reloadConfig();
                        player.sendMessage(prefix + util.formatText(message.getString("reload-setting-file")));
                    }
                    if (args[1].equals("message")) {
                        plugin.plugin.message.reloadConfig();
                        player.sendMessage(prefix + util.formatText(message.getString("reload-message-file")));
                    }
                    if (args[1].equals("all")) {
                        plugin.plugin.message.reloadConfig();
                        plugin.plugin.setting.reloadConfig();
                        player.sendMessage(prefix + util.formatText(message.getString("reload-all-file")));
                    }
                } else {
                    player.sendMessage(no_permission);
                }
        }
        if (args[0].equalsIgnoreCase("help")) {
            if (sender.hasPermission("PlayerMessage.help")) {
                for (String help_message : help_list) {
                    sender.sendMessage(util.formatText(help_message));
                }
            } else {
                player.sendMessage(no_permission);
            }
        }
        if (args[0].equalsIgnoreCase("list")) {
            if (sender.hasPermission("PlayerMessage.list")) {
                String list_first_time_join_message = util.formatText(message.getString("list-first-time-join-message"));
                String list_join_message = util.formatText(message.getString("list-join-message"));
                String list_quit_message = util.formatText(message.getString("list-quit-message"));
                String list_title_message = util.formatText(message.getString("list-title-message"));
                String list_subtitle_message = util.formatText(message.getString("list-subtitle-message"));
                player.sendMessage(prefix + list_first_time_join_message.replace("{command}", first_time_join_message));
                player.sendMessage(prefix + list_join_message.replace("{command}", join_message));
                player.sendMessage(prefix + list_quit_message.replace("{command}", quit_message));
                player.sendMessage(prefix + list_title_message.replace("{command}", title_message));
                player.sendMessage(prefix + list_subtitle_message.replace("{command}", subtitle_message));
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
            return true;
        }
        return false;
    }
}
