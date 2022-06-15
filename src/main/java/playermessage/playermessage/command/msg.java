package playermessage.playermessage.command;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import playermessage.playermessage.PlayerMessage;
import playermessage.playermessage.Utils.util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class msg implements CommandExecutor {

    public PlayerMessage plugin;

    public msg() {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration message = plugin.plugin.message.getConfig();
        FileConfiguration setting = plugin.plugin.setting.getConfig();
        String prefix = util.formatText(message.getString("Prefix"));
        String no_permission = prefix + util.formatText(message.getString("command-no-permission"));
        String command_tutorial = util.formatText(prefix + message.getString("Msg-Command-tutorial"));
        boolean sound_enabled = setting.getBoolean("Msg-Sound-enabled");

        if (!(sender instanceof Player)) {
            util.Background_unavailable();
            return true;
        }

        if (!sender.hasPermission("PlayerMessage.msg")) {
            sender.sendMessage(no_permission);
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(command_tutorial);
            return true;
        }

        Player receiver = Bukkit.getServer().getPlayer(args[0]);

        if (receiver == null) {
            sender.sendMessage(PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), prefix + util.formatText(message.getString("Player-not-online"))));
            sender.sendMessage(command_tutorial);
            return true;
        }

        if (args.length == 1) {
            sender.sendMessage(command_tutorial);
            return true;
        }

        String sender_name = sender.getName();
        String receiver_name = receiver.getName();

        String receiverMsg = util.formatText(message.getString("receiver-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));
        String senderMsg = util.formatText(message.getString("sender-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));
        String OpMsg = util.formatText(message.getString("spy-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));

        if(label.equalsIgnoreCase("m") || label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("tell")) {
            for (int i = 1; i < args.length; i++) {
                receiverMsg += util.formatText(args[i] +" ");
                senderMsg += util.formatText(args[i] + " ");
                OpMsg += util.formatText(args[i] + " ");
            }

            if (sound_enabled) {
                receiver.playSound(receiver.getLocation(), Sound.valueOf(setting.getString("Msg-Sound-Type")), 1F, 1f);
            }

            sender.sendMessage(senderMsg);
            receiver.sendMessage(receiverMsg);

            for (Player player : Bukkit.getOnlinePlayers()) {
               if (player.hasPermission("PlayerMessage.msg.spy")) {
                   player.sendMessage(OpMsg);
                   return true;
               }
            }
            return true;
        }
        return false;
    }
}
