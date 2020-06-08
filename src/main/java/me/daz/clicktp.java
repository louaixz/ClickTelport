package me.daz;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public final class clicktp extends JavaPlugin {
    Logger log;
    HashMap<Player,Player> tpa = new HashMap<>();
    HashMap<Player, Boolean> tpahere = new HashMap<>();
    ArrayList<Player> sent = new ArrayList<>();
    public void onEnable() {
        this.log = this.getLogger();
        PluginDescriptionFile plugininfo = this.getDescription();
        this.log.info(plugininfo.getName() + " version:" + plugininfo.getVersion() + " has been enabled ");
        this.log.info("The simple click telport plugin!");
    }

    public void onDisable() {
        PluginDescriptionFile fileinfo = this.getDescription();
        this.log.info(fileinfo.getName() + " version:" + fileinfo.getVersion() + "has been disabled");
    }

    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {
        Player playerA = (Player) sender ;

        if(commandlabel.equalsIgnoreCase("tpa")) {
            Player playerB = playerA.getServer().getPlayer(args[0]);
            if (sender instanceof Player&&!sent.contains(playerA)) {
                if(args.length!=1){
                    playerA.spigot().sendMessage(new ComponentBuilder("請正確輸入玩家名稱").color(ChatColor.RED).create());
                    return true;
                }
                if (args.length==1&&playerB ==null){
                    playerA.spigot().sendMessage(new ComponentBuilder("此玩家不在線上").color(ChatColor.RED).create());
                    return true;
                }
                if (playerA.equals(playerB)){
                    playerA.spigot().sendMessage(new ComponentBuilder("請不要傳自己").color(ChatColor.RED).create());
                    return true;
                }
               // Collection players = Bukkit.getOnlinePlayers();
                if (args.length==1 && Bukkit.getPlayerExact(args[0])!=null){
                    if (!sent.contains(playerA)&&playerB != null) {
                        playerB.spigot().sendMessage((new ComponentBuilder(playerA.getDisplayName()+"想要傳送到你的位置")).color(ChatColor.GOLD).bold(true)
                                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("點擊下方接受或拒絕")).color(ChatColor.GOLD)
                                        .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("接受"))
                                .event(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpyes")).color(ChatColor.BLUE).bold(true)
                                .event(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("接受")).color(ChatColor.BLUE)
                                        .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("拒絕"))
                                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpno")).color(ChatColor.RED).bold(true)
                                .event(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("拒絕")).color(ChatColor.BLUE)
                                        .create())).create());
                        tpa.put(playerB,playerA);
                        tpahere.put(playerB,false);
                        sent.add(playerA);
                    }
                }
            }else if (sent.contains(playerA)){
                playerA.sendMessage("§c請求已存在");
                return true;
            }
        }else if (commandlabel.equalsIgnoreCase("tpahere")){
            Player playerB = playerA.getServer().getPlayer(args[0]);
            if (sender instanceof Player&&!sent.contains(playerA)) {
                if(args.length!=1){
                    playerA.spigot().sendMessage(new ComponentBuilder("請正確輸入玩家名稱").color(ChatColor.RED).create());
                    return true;
                }
                if (args.length==1&&playerB ==null){
                    playerA.spigot().sendMessage(new ComponentBuilder("此玩家不在線上").color(ChatColor.RED).create());
                    return true;
                }
                if (playerA.equals(playerB)){
                    playerA.spigot().sendMessage(new ComponentBuilder("請不要傳自己").color(ChatColor.RED).create());
                    return true;
                }
                if (args.length==1 && Bukkit.getPlayerExact(args[0])!=null){
                    if (!sent.contains(playerA)&&playerB != null) {
                        playerB.spigot().sendMessage((new ComponentBuilder(playerA.getDisplayName()+"想要傳送到你到他的位置")).color(ChatColor.GOLD).bold(true)
                                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("點擊下方接受或拒絕")).color(ChatColor.GOLD)
                                        .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("接受"))
                                .event(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpyes")).color(ChatColor.BLUE).bold(true)
                                .event(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("接受")).color(ChatColor.BLUE)
                                        .create())).create());
                        playerB.spigot().sendMessage((new ComponentBuilder("拒絕"))
                                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/tpno")).color(ChatColor.RED).bold(true)
                                .event(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder("拒絕")).color(ChatColor.BLUE)
                                        .create())).create());
                        tpa.put(playerB,playerA);
                        tpahere.put(playerB,true);
                        sent.add(playerA);
                    }
                }
            }else if (sent.contains(playerA)){
                playerA.sendMessage("§c請求已存在");
                return true;
            }
        } else if (commandlabel.equalsIgnoreCase("tpyes")){
            if (tpa.get(playerA)==null) {
                playerA.sendMessage("無任何傳送請求");
                return true;
            }else if (!tpahere.get(playerA)){
                tpa.get(playerA).teleport(playerA);
                tpa.get(playerA).sendMessage("對方已接受請求,開始傳送...");
                playerA.sendMessage("已接受請求");
            }
            else {
                playerA.teleport(tpa.get(playerA));
                tpa.get(playerA).sendMessage("已接受請求,開始傳送...");
                playerA.sendMessage("已接受請求");
            }
            sent.remove(tpa.get(playerA));
            tpa.put(playerA,null);
            tpahere.put(playerA,null);
            return true;
        }else if (commandlabel.equalsIgnoreCase("tpno")) {
            if(tpa.get(playerA)==null) {
                playerA.sendMessage("無任何傳送請求");
                return true;
            } else  {
                tpa.get(playerA).sendMessage("對方已拒絕...");
                playerA.sendMessage("已拒絕請求");
                sent.remove(tpa.get(playerA));
                tpa.put(playerA,null);
                tpahere.put(playerA,null);
                return true;
            }
        }
        if (commandlabel.equalsIgnoreCase("getpos")) {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                Player getplayerpos = playerA.getServer().getPlayer(args[0]);
                if(getplayerpos!=null){
                    Location getplayerposLocation = getplayerpos.getLocation();
                    getplayerpos.sendMessage("your position is" + getplayerposLocation.getX() + "," + getplayerposLocation.getY() + "," + getplayerposLocation.getZ());
                    playerA.sendMessage("the position is " + getplayerposLocation.toString());
                }
            }
        }
        return true;
    }
}
