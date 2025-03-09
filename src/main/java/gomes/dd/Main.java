package gomes.dd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        getLogger().info("Plugin dd ativado!");

        // Criar a pasta de dados se não existir
        getDataFolder().mkdirs();
        new File(getDataFolder() + "/players").mkdirs();

        // Inicializa o gerenciador de dados dos jogadores
        playerDataManager = new PlayerDataManager(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin dd desativado!");
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por um jogador.");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("setname")) {
            if (args.length == 1) {
                String novoNome = args[0].trim();

                player.setDisplayName(novoNome);
                player.setPlayerListName(novoNome);
                player.setCustomName(novoNome);

                player.sendMessage("Seu nome foi alterado para: " + novoNome);
                return true;
            } else {
                player.sendMessage("Uso correto: /setname <nome>");
                return false;
            }
        }

        if (cmd.getName().equalsIgnoreCase("restaurarNome")) {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            player.setCustomName(player.getName());

            player.sendMessage("Seu nome foi restaurado para o original.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("setficha")) {
            if (args.length >= 2) {
                String atributo = args[0];
                String valor = args[1];

                playerDataManager.savePlayerData(player, "ficha." + atributo, valor);
                player.sendMessage("Atributo " + atributo + " atualizado para: " + valor);
                return true;
            } else {
                player.sendMessage("Uso correto: /setficha <atributo> <valor>");
                return false;
            }
        }

        if (cmd.getName().equalsIgnoreCase("verficha")) {
            player.sendMessage("Sua ficha de personagem:");

            String[] playerInfos = {"nome", "classe", "antecedente", "raça", "alinhamento", "xp"}
            String[] atributos = {"forca", "destreza", "constituicao", "inteligencia", "sabedoria", "carisma"};


            for (String atributo : atributos) {
                String valor = playerDataManager.getPlayerData(player, "ficha." + atributo);
                if (valor != null) {
                    player.sendMessage(atributo + ": " + valor);
                }
            }
            return true;
        }

        return false;
    }
}
