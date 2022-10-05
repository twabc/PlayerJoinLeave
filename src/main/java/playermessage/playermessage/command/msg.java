package playermessage.playermessage.command;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;
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

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        FileConfiguration messageFile = PlayerMessage.message.getConfig();
        FileConfiguration settingFile = PlayerMessage.setting.getConfig();
        String prefix = util.formatText(messageFile.getString("Prefix"));
        String no_permission = prefix + util.formatText(messageFile.getString("command-no-permission"));
        String command_tutorial = util.formatText(prefix + messageFile.getString("Msg-Command-tutorial"));
        boolean sound_enabled = settingFile.getBoolean("Msg-Sound-enabled");

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
            sender.sendMessage(PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), prefix + util.formatText(messageFile.getString("Player-not-online"))));
            sender.sendMessage(command_tutorial);
            return true;
        }

        if (args.length == 1) {
            sender.sendMessage(command_tutorial);
            return true;
        }

        String sender_name = sender.getName();
        String receiver_name = receiver.getName();

        String receiverMsg = util.formatText(messageFile.getString("receiver-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));
        String senderMsg = util.formatText(messageFile.getString("sender-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));
        String OpMsg = util.formatText(messageFile.getString("spy-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));

        if(label.equalsIgnoreCase("m") || label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("tell")) {
            for (int i = 1; i < args.length; i++) {
                receiverMsg += util.formatText(args[i] +" ");
                senderMsg += util.formatText(args[i] + " ");
                OpMsg += util.formatText(args[i] + " ");
            }

            if (sound_enabled) {
                receiver.playSound(receiver.getLocation(), Sound.valueOf(settingFile.getString("Msg-Sound-Type")), 1F, 1f);
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
