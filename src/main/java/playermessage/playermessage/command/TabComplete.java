package playermessage.playermessage.command;

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
              arguments.add("announcer");
              arguments.add("tag");
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
              arguments3.add("tag");
              arguments3.add("all");
              return arguments3;
        } else if (args[0].equals("announcer")) {
            List<String> arguments4 = new ArrayList<String>();
              arguments4.add("stop");
              arguments4.add("start");
              return arguments4;
        } else if (args[0].equals("tag")) {
            List<String> arguments5 = new ArrayList<String>();
              arguments5.add("set");
              if (args.length == 2) {
                  return arguments5;
              }
        }
        return null;
    }
}
