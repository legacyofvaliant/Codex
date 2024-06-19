package cx.ajneb97.configs;

import cx.ajneb97.Codex;
import cx.ajneb97.managers.MensajesManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class MensajesConfigManager {

    private Codex plugin;
    private FileConfiguration messages = null;
    private File messagesFile = null;
    private String rutaMessages;

    public MensajesConfigManager(Codex plugin) {
        this.plugin = plugin;
    }

    public void configurar() {
        registerMessages();
        this.plugin.setMensajesManager(new MensajesManager(getMessages().getString("prefix")));
    }

    public void registerMessages() {
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        rutaMessages = messagesFile.getPath();
        if (!messagesFile.exists()) {
            this.getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }

    public void saveMessages() {
        try {
            messages.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getMessages() {
        if (messages == null) {
            reloadMessages();
        }
        return messages;
    }

    public void reloadMessages() {
        if (messages == null) {
            messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        try {
            defConfigStream = new InputStreamReader(plugin.getResource("messages.yml"), "UTF8");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messages.setDefaults(defConfig);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.plugin.setMensajesManager(new MensajesManager(getMessages().getString("prefix")));
    }

    public String getPath() {
        return rutaMessages;
    }
}
