package playerjoinleave.playerjoinleave.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;

import java.util.List;

public class joinleave implements CommandExecutor {

    public PlayerJoinLeave plugin;

    public joinleave() {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender; //取得輸入指令的玩家
        FileConfiguration file = plugin.plugin.file.getConfig(); //取得setting.yml資料夾
        FileConfiguration message = plugin.plugin.message.getConfig(); //取得message.yml資料夾
        List<String> help_list = file.getStringList("help_command_message"); //取得指令幫助訊息列表
        List<String> help_list_reload = file.getStringList("no_specify_file"); //取得資料夾重製幫助訊息列表
        String no_permission = ChatColor.translateAlternateColorCodes('&', file.getString("command_no_permission")); //沒有指令權限時發送的訊息
        String join_message = ChatColor.translateAlternateColorCodes('&', message.getString("join_message").replace("{player}", player.getName())); //加入訊息
        String first_time_join_message = ChatColor.translateAlternateColorCodes('&', message.getString("first_time_join_message").replace("{player}", player.getName())); //首次加入訊息
        String quit_message = ChatColor.translateAlternateColorCodes('&', message.getString("quit_message").replace("{player}", player.getName())); //退出訊息
        String title_message = ChatColor.translateAlternateColorCodes('&', message.getString("Title_message")); //加入標題
        String subtitle_message = ChatColor.translateAlternateColorCodes('&', message.getString("Sub_Title_message")); //加入副標題

        if (label.equalsIgnoreCase("joinleave")) { //joinleave
            if (args.length == 0) { //判斷是否有輸入後綴
               if (player.hasPermission("Playerjoinleave.info")) {
                   for (String help_message : help_list) {
                       sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help_message));
                   }
               } else {
                   player.sendMessage(no_permission);
               }
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) { //joinleave reload
                if (sender.hasPermission("Playerjoinleave.reload")) {
                    if (args.length == 1) {
                        for (String help_message : help_list_reload) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', help_message));
                        }
                        return true;
                    }
                    if (args[1].equals("setting")) {
                        plugin.plugin.file.reloadConfig();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("reload_setting_file"))); //reload setting file
                    }
                    if (args[1].equals("message")) {
                        plugin.plugin.message.reloadConfig();
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("reload_message_file"))); //reload message file
                    }
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("help")) { //joinleave help
                if (sender.hasPermission("Playerjoinleave.help")) {
                    for (String help_message : help_list) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help_message));
                    }
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("list")) { //joinleave list
                if (sender.hasPermission("Playerjoinleave.list")) {
                    String info_first_time_join_message = ChatColor.translateAlternateColorCodes('&', file.getString("info_first_time_join_message"));
                    String info_join_message = ChatColor.translateAlternateColorCodes('&', file.getString("info_join_message"));
                    String info_quit_message = ChatColor.translateAlternateColorCodes('&', file.getString("info_quit_message"));
                    String info_title_message = ChatColor.translateAlternateColorCodes('&', file.getString("info_title_message"));
                    String info_subtitle_message = ChatColor.translateAlternateColorCodes('&', file.getString("info_subtitle_message"));
                    player.sendMessage(info_first_time_join_message.replace("{command}", first_time_join_message));
                    player.sendMessage(info_join_message.replace("{command}", join_message));
                    player.sendMessage(info_quit_message.replace("{command}", quit_message));
                    player.sendMessage(info_title_message.replace("{command}", title_message));
                    player.sendMessage(info_subtitle_message.replace("{command}", subtitle_message));
                } else {
                    player.sendMessage(no_permission);
                }
            }
            if (args[0].equalsIgnoreCase("info")) { //joinleave info
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件名稱&f: &b&lPlayerJoinLeave"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件版本&f: 1.0"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件設計&f: &6tw_abc"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l插件製作&f: &6tw_abc"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lGithub&f: &3https://github.com/twabc"));
            }
            if (args[0].equals("set")) { //joinleave set
                if (sender.hasPermission("Playerjoinleave.set")) {
                 if (args.length == 1 || args.length == 2) { //joinleave set
                     for (String help_message : help_list) {
                         sender.sendMessage(ChatColor.translateAlternateColorCodes('&', help_message));
                     }
                     return true;
                 }
                    if (args[1].equals("joinmessage")) { //joinleave set joinmessage
                        SetMessage(args, file, message, player, "join_message", "set_join_message");
                 }
                    if (args[1].equals("leavemessage")) { //joinleave set leavemessage
                        SetMessage(args, file, message, player, "quit_message", "set_quit_message");
                 }
                    if (args[1].equals("title")) { //joinleave set title
                        SetMessage(args, file, message, player, "Title_message", "set_title");
                 }
                    if (args[1].equals("subtitle")) { //joinleave set subtitle
                        SetMessage(args, file, message, player, "Sub_Title_message", "set_subtitle");
                 }
                    if (args[1].equals("firsttimejoinmessage")) { //joinleave set firsttimejoinmessage
                        SetMessage(args, file, message, player, "first_time_join_message", "set_first_time_join_message");
                 }
                } else {
                    player.sendMessage(no_permission);
                }
            }
            return false;
        }
        return false;
    }

    public void SetMessage(String[] args, FileConfiguration file, FileConfiguration message_file, Player player, String target_message, String file_message) {
        String message = "";
        for (int i = 2; i < args.length; i++) {
            message += args[i];
        }
        String change_message = file.getString(file_message).replace("{message}", message); //取得成功設置後系統發送的訊息
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', change_message));
        message_file.set(target_message, message);
        plugin.plugin.message.saveConfig();
    }
}
