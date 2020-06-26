package me.daz;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
class SendPlayerMessageTask implements Runnable {
    private CommandSender player;
    private ChatColor color;
    private String message;

    SendPlayerMessageTask(CommandSender player, ChatColor color, String message) {
        this.player = player;
        this.color = color;
        this.message = message;
    }

    public void run() {
        if (this.player == null) {
            logtask.addLogEntry(this.color + this.message);
        } else {
            logtask.sendMessage(this.player, this.color, this.message);
        }
    }
}