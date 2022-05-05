package playerjoinleave.playerjoinleave.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;

public class joinleavereload implements CommandExecutor {

    public PlayerJoinLeave plugin;

    public joinleavereload(PlayerJoinLeave playerJoinLeave) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        FileConfiguration file = plugin.plugin.file.getConfig();

        if (label.equals("playerjoinleavereload")) {
            if (sender.hasPermission("Playerjoinleave.reload")) {
                plugin.plugin.file.reloadConfig();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', file.getString("reload_command_message")));
            } else {
               player.sendMessage(ChatColor.translateAlternateColorCodes('&',file.getString("reload_command_no_permission")));
            }
        }
        return false;
    }
}
