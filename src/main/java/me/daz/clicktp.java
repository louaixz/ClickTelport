package me.daz;

import me.daz.tasks.ClearOldRequest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public final class clicktp extends logtask {
    public void onEnable() {
<<<<<<< HEAD

        this.loadConfig();


=======
        int getTimeOutValue = getConfig().getInt("request-timeout-seconds");
        loadConfig();
        TimeoutValue(getTimeOutValue);
>>>>>>> parent of 4e0157f... add message.yml to custom message
        logENABLE();
        new ClearOldRequest(this).runTaskTimer(this,0,20);
    }
    public void onDisable() {
        logDISABLE();
    }

    public boolean onCommand(CommandSender sender, Command command, String commandlabel, String[] args) {

        Player playerA = (Player) sender ;
        if (command.getLabel().equalsIgnoreCase("tpa")) {
<<<<<<< HEAD
            if (playerHasPermission(playerA)) {
                askTPA(getServer().getPlayer(args[0]), playerA, args);

            } else if(args.length != 1){
                    sendMessage(playerA,"null");
                    return true;
                }
                else{
                    sendMessage(playerA, ChatColor.RED, Messages.tpanoPermission);
                    return true;}
=======
            askTPA(getServer().getPlayer(args[0]),playerA,args);
            return true;
>>>>>>> parent of 4e0157f... add message.yml to custom message
        }
        if (command.getLabel().equalsIgnoreCase("tpahere")) {
            askTPAhere(getServer().getPlayer(args[0]),playerA,args);
            return true;
        }

        if (commandlabel.equalsIgnoreCase("tpyes")){
            tpACCEPT(playerA);
            return true;
        }
        if (commandlabel.equalsIgnoreCase("tpno")){
            tpDENY(playerA);
            return true;
        }
        return false;
    }

}





