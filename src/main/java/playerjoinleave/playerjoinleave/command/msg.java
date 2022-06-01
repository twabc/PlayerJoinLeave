package playerjoinleave.playerjoinleave.command;

import me.clip.placeholderapi.PlaceholderAPI;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;
import playerjoinleave.playerjoinleave.Utils.util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class msg implements CommandExecutor {

    public PlayerJoinLeave plugin;

    public msg() {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration file = plugin.plugin.message.getConfig();
        String prefix = file.getString("Prefix");
        String command_tutorial = util.formatText(prefix + file.getString("Msg-Command-tutorial"));

        if (!(sender instanceof Player)) {
            util.Background_unavailable();
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(command_tutorial);
            return true;
        }

        Player receiver = Bukkit.getServer().getPlayer(args[0]);

        if (receiver == null) {
            sender.sendMessage(PlaceholderAPI.setPlaceholders(Bukkit.getPlayer(args[0]), prefix + util.formatText(file.getString("Player-not-online"))));
            sender.sendMessage(command_tutorial);
            return true;
        }

        if (args.length == 1) {
            sender.sendMessage(command_tutorial);
            return true;
        }

        String sender_name = sender.getName();
        String receiver_name = receiver.getName();

        String receiverMsg = util.formatText(file.getString("receiver-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));
        String senderMsg = util.formatText(file.getString("sender-message").replace("{sender}",sender_name).replace("{receiver}", receiver_name));

        if(label.equalsIgnoreCase("m") || label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("tell")) {
            for (int i = 1; i < args.length; i++) {
                receiverMsg += util.formatText(args[i] +" ");
                senderMsg += util.formatText(args[i] + " ");
            }

            sender.sendMessage(senderMsg);
            receiver.sendMessage(receiverMsg);

            return true;
        }
        return false;
    }
}
