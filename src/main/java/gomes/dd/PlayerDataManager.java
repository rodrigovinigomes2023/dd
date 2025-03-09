package gomes.dd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataManager {
    private final Main plugin;

    public PlayerDataManager(Main plugin) {
        this.plugin = plugin;
    }

    private File getPlayerFile(Player player) {
        return new File(plugin.getDataFolder() + "/players", player.getUniqueId() + ".yml");
    }

    public void savePlayerData(Player player, String path, String value) {
        File file = getPlayerFile(player);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set(path, value);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().warning("Erro ao salvar os dados do jogador " + player.getName());
        }
    }

    public String getPlayerData(Player player, String path) {
        File file = getPlayerFile(player);
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        return config.getString(path, "N/A");
    }
}
