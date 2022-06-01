package playerjoinleave.playerjoinleave.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<String>();
              arguments.add("info");
              arguments.add("list");
              arguments.add("help");
              arguments.add("reload");
              return arguments;
        } else if (args[0].equals("set")) {
            List<String> arguments2 = new ArrayList<String>();
              arguments2.add("subtitle");
              arguments2.add("title");
              arguments2.add("leavemessage");
              arguments2.add("joinmessage");
              arguments2.add("firsttimejoinmessage");
              return arguments2;
        } else if (args[0].equals("reload")) {
            List<String> arguments3 = new ArrayList<String>();
              arguments3.add("setting");
              arguments3.add("message");
              return arguments3;
        }
        return null;
    }
}
