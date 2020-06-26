package me.daz;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MessageData extends JavaPlugin {
    private  String[] messages;
    private static final String dataLayerFolderPath;
    static String configFilePath;
    static String messagesFilePath;
    HashMap<String, customMessage> defaults = new HashMap();
    FileConfiguration config = YamlConfiguration.loadConfiguration(new File(messagesFilePath));
    public MessageData(){
        this.loadMessage();
    }

    private void loadMessage() {
        Messages[] MessageIDs = Messages.values();
        this.messages = new String[Messages.values().length];
        this.addDefault(defaults, Messages.tpahereNopermission, "you has no permission to Use tpahere.");
        this.addDefault(defaults, Messages.tpanoPermission,"you has no permission to Use tpa.");

        for(int i = 0; i < MessageIDs.length; ++i) {
            Messages messageID = MessageIDs[i];
            customMessage messageData = (customMessage) defaults.get(messageID.name());
            this.messages[messageID.ordinal()] = config.getString("Messages."+ messageID.name()  + ".Text", messageData.text);
            config.set("Messages." + messageID.name() + ".Text", this.messages[messageID.ordinal()]);
            // this.messages[messageID.ordinal()] = this.messages[messageID.ordinal()].replace('$', '§');
        }
            /*this.messages[messageID.ordinal()] = config.getString("Messages."+ messageID.name()  + ".Text", messageData.text);
            config.set("Messages." + messageID.name() + ".Text", this.messages[messageID.ordinal()]);*/

                try {
                    config.save(messagesFilePath);
                } catch (IOException e) {
                    e.printStackTrace();

                }
                System.gc();
    }

    public synchronized String getMessage(Messages messageID) {
        String message = this.messages[messageID.ordinal()];

        /*for(int i = 0; i < args.length; ++i) {
            String param = args[i];
            message = message.replace("{" + i + "}", param);
        }*/

        return message;
    }

        private void addDefault (HashMap < String, customMessage > defaults, Messages id, String text){
            customMessage message = new customMessage(id, text);
            defaults.put(id.name(), message);
        }
        static {
            dataLayerFolderPath = "plugins" + File.separator + "Clicktp";
            messagesFilePath = dataLayerFolderPath + File.separator + "messages.yml";
        }


}



