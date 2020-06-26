package me.daz;

import me.daz.tasks.ClearOldRequest;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public final class clicktp extends logtask implements Listener {

    public void onEnable() {

        int getTimeOutValue = getConfig().getInt("request-timeout-seconds");
        this.loadConfig();


        TimeoutValue(getTimeOutValue);

        logENABLE();
        new ClearOldRequest(this).runTaskTimer(this,0,20);
    }
    public void onDisable() {
        logDISABLE();
    }


    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {

        Player playerA = (Player) sender ;

        if (command.getLabel().equalsIgnoreCase("tpa")) {
            if (playerHasPermission(playerA)) {
                askTPA(getServer().getPlayer(args[0]), playerA, args);

            } else if(args.length != 1){
                    sendMessage(playerA,"null");
                    return true;
                } else if (playerA.equals(getServer().getPlayer(args[0]))) {
                playerA.spigot().sendMessage(new ComponentBuilder("請不要傳自己").color(ChatColor.RED).create());
                return true;
            }

                else{
                    sendMessage(playerA, ChatColor.RED, Messages.tpanoPermission);
                    return true;}
        }
        if (command.getLabel().equalsIgnoreCase("tpahere")) {
            if (playerHasPermission(playerA)) {
                askTPAhere(getServer().getPlayer(args[0]), playerA, args);
            } else {
                sendMessage(playerA, ChatColor.RED, Messages.tpahereNopermission);
                return true;
            }
        }

        if (commandlabel.equalsIgnoreCase("tpyes")){
            tpACCEPT(playerA);
            return true;
        }
        if (commandlabel.equalsIgnoreCase("tpno")){
            tpDENY(playerA);
            return true;
        }

        if (commandlabel.equalsIgnoreCase("getpos")) {
            getPOS(playerA,args);
        }
        return false;
    }
    @EventHandler
    public void PlayerChat(AsyncPlayerChatEvent event) {

        Player p = event.getPlayer();
        if (p.hasPermission("Clicktp.tpa")|p.hasPermission("Clicktp.tpahere")) {
            p.setPlayerListName(ChatColor.GRAY + "[MEMBER] " + p.getDisplayName());
        }
    }

}





