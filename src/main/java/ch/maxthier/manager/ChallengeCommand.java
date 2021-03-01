package ch.maxthier.manager;

import ch.maxthier.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ChallengeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = Main.getPlugin().getConfig();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("Minigames.Challenge") || !config.getBoolean("NeedsPermission")) {
                if (args.length == 0) {
                    p.sendMessage("§a-----InventoryGames-----");
                    p.sendMessage("§aInventoryGames version " + Main.Version);
                    p.sendMessage("§aCreated by Maxthier. Published by Qubik-Studios!");
                    p.sendMessage("§aTo challenge a player with a Command use §6/challenge <Player> <Game>§a!");
                    p.sendMessage("§aTo challenge a player with a GUI use §6/challenge <Player>§a!");
                } else {
                    if (!p.getName().equalsIgnoreCase(args[0])) {
                        if (args.length == 1) {
                            if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("decline")) {
                                p.sendMessage("§cPlease use §6/challenge <accept/decline> <name>");
                            } else {
                                Player target = Bukkit.getPlayer(args[0]);
                                if (target != null) {
                                    GameManager.selection.put(p.getName(), target.getName());
                                    p.openInventory(GameSelecter.getInv());
                                } else {
                                    p.sendMessage("§cNo player with the name §6" + args[0] + " §ccould be found!");
                                }
                            }
                        } else {
                            if (args.length == 2) {
                                if (args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("decline")) {
                                    if (args[0].equalsIgnoreCase("accept")) {
                                        Player target = Bukkit.getPlayer(args[1]);
                                        if (GameManager.challenge.containsValue(p.getName())) {
                                            if (GameManager.challenge.containsKey(target.getName())) {
                                                p.sendMessage("§aYou have accepted the challenge!");
                                                target.sendMessage("§6" + p.getName() + " §ahas accepted your request!");
                                                GameManager.tttstart(target, p);
                                                GameManager.challenge.remove(target.getName());
                                                GameManager.challenge.remove(target.getName());
                                            } else {
                                                p.sendMessage("§cYou were not challenged by §6" + args[1] + "§c!");
                                            }
                                        } else {
                                            p.sendMessage("§cYou were not challenged by anyone!");
                                        }
                                    } else {
                                        if (args[0].equalsIgnoreCase("decline")) {
                                            Player target = Bukkit.getPlayer(args[1]);
                                            if (GameManager.challenge.containsValue(p.getName())) {
                                                if (GameManager.challenge.containsKey(target.getName())) {
                                                    target.sendMessage("§6" + p.getName() + " §chas declined your challenge!");
                                                    p.sendMessage("§aYou have declined the challenge!");
                                                    GameManager.challenge.remove(target.getName());
                                                    GameManager.challengegame.remove(target.getName());
                                                } else {
                                                    p.sendMessage("§cYou were not challenged by §6" + args[1] + "§c!");
                                                }
                                            } else {
                                                p.sendMessage("§cYou were not challenged by anyone!");
                                            }
                                        } else {
                                            p.sendMessage("§cPlease use §6/challenge <Spieler> accept/decline§c!");
                                        }
                                    }
                                } else {
                                    if (args[1].equalsIgnoreCase("tictactoe")) {
                                        Player target = Bukkit.getPlayer(args[0]);
                                        if (target != null) {
                                            target.sendMessage("§aYou got challanged from §6" + p.getName() + " §awith §6/challenge accept/decline " + p.getName() + " §ayou can accept or decline the challenge!");
                                            p.sendMessage("§aYou have challenged §6" + target.getName() + "§a!");
                                            GameManager.challenge.put(p.getName(), target.getName());
                                            GameManager.challengegame.put(p.getName(), Games.TICTACTOE);
                                        } else {
                                            p.sendMessage("§cNo player with the name §6" + args[0] + " §ccould be found!");
                                        }
                                    }
                                }
                            } else {
                                p.sendMessage("§cPlease use §6/challenge <Spieler> §core §6/challenge <Spieler> <tictactoe>§c!");
                            }
                        }
                    } else {
                        p.sendMessage("§cYou can not challenge yourself!");
                    }
                }
            } else {
                p.sendMessage("§cYou do not have permission to do this!");
            }
        } else {
            sender.sendMessage("§cOnly players can execute this command!");
        }
        return false;
    }
}
