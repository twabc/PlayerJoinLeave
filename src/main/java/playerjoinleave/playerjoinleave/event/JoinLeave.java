package playerjoinleave.playerjoinleave.event;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import playerjoinleave.playerjoinleave.PlayerJoinLeave;

import java.util.ArrayList;
import java.util.List;

public class JoinLeave implements Listener {

    public PlayerJoinLeave plugin;

    public JoinLeave() {
        this.plugin = plugin;
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) { //玩家加入遊戲事件
        Player player = event.getPlayer(); //觸發事件玩家
        FileConfiguration file = plugin.plugin.file.getConfig(); //config.yml資料夾

        String hover_command = file.getString("message_suggest_command").replace("{player}", player.getName()); //點擊加入訊息後輸入的指令
        TextComponent hover_message = new TextComponent(ChatColor.translateAlternateColorCodes('&', file.getString("join_message").replace("{player}", player.getName()))); //加入訊息顯示文字
        TextComponent hover_message_first_time_join = new TextComponent(ChatColor.translateAlternateColorCodes('&', file.getString("first_time_join_message").replace("{player}", player.getName()))); //首次加入訊息顯示文字
        List<String> hover_message_list = file.getStringList("message_show_text");
        String hover_text = "";
        for (int i = 0; i < hover_message_list.size(); i++) { //依序將每一行文字加入list中並換行
            hover_text += ChatColor.translateAlternateColorCodes('&', hover_message_list.get(i) + "\n").replace("{player_name}", player.getName()).replace("{player_world}", player.getWorld().getName());
        }
        hover_message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, hover_command)); //設置點擊加入訊息後輸入的指令
        hover_message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover_text).create() ) ); //設置加入訊息顯示的文字
        hover_message_first_time_join.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, hover_command)); //設置點擊首次加入訊息後輸入的指令
        hover_message_first_time_join.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hover_text).create() ) ); //設置首次加入訊息顯示的文字

        boolean title_enabled = file.getBoolean("Title_enabled"); //title功能是否開啟
        String title = ChatColor.translateAlternateColorCodes('&', file.getString("Title_message")); //title訊息
        String sub_title = ChatColor.translateAlternateColorCodes('&', file.getString("Sub_Title_message")); //副title訊息
        int fadeln = file.getInt("fadeln"); //淡入時間
        int stay = file.getInt("stay"); //顯示時間
        int fadeOut = file.getInt("fadeOut"); //淡出時間

        boolean join_send_message_enabled = file.getBoolean("player_join_send_message_enabled"); //玩家登入時輸入訊息是否開啟
        String join_send_message = file.getString("player_join_send_message"); //玩家登入時輸入的訊息

        boolean sound_enabled = file.getBoolean("Sound_enabled"); //音效功能是否開啟
        Sound sound_type = Sound.valueOf(file.getString("Sound_type")); //音效類型

        if (player.hasPlayedBefore()) { //檢查玩家是否遊玩過

            event.setJoinMessage("");

            for (Player players : Bukkit.getOnlinePlayers()) {
               players.spigot().sendMessage(hover_message);
            }

            if (title_enabled) {
                player.sendTitle(title, sub_title, fadeln, stay, fadeOut);
            }

            if (join_send_message_enabled) {
                player.chat(join_send_message);
            }

            if (sound_enabled) {
                player.playSound(player.getLocation(), sound_type, 2.0F, 1.0F);
            }

            return;

        } else {
            event.setJoinMessage("");

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.spigot().sendMessage(hover_message_first_time_join);
            }

            if (title_enabled) {
                player.sendTitle(title, sub_title, fadeln, stay, fadeOut);
            }

            if (join_send_message_enabled) {
                player.chat(join_send_message);
            }

            if (sound_enabled) {
                player.playSound(player.getLocation(), sound_type, 2.0F, 1.0F);
            }

            return;

        }
    }

    @EventHandler
    public void Quit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration file = plugin.plugin.file.getConfig();

        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', file.getString("quit_message").replace("{player}", player.getName())));
    }
}
