package ch.maxthier.manager;

import ch.maxthier.ticatactoe.TTTGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SplittableRandom;

public class GameManager {

    public static HashMap<String, String> challenge = new HashMap<>();
    public static HashMap<String, Games> challengegame = new HashMap<>();
    public static List<String> tttqeue = new ArrayList<>();
    public static HashMap<String, TTTGame> tttgames = new HashMap<>();
    public static List<String> tttingame = new ArrayList<>();
    public static HashMap<String, String> selection = new HashMap<>();
    public static List<String> tttinv = new ArrayList<>();



    public static void tttstart(Player p1, Player p2) {
        TTTGame game = new TTTGame(p1, p2);
        tttgames.put(p1.getName(), game);
        tttgames.put(p2.getName(), game);
        tttingame.add(p1.getName());
        tttingame.add(p2.getName());
    }

    public static void ChallengePlayer(Player p1, Player p2, Games game){
        if(challenge.containsKey(p1.getName())){
            challenge.remove(p1.getName());
            challengegame.remove(p1.getName());
        }
        challenge.put(p1.getName(), p2.getName());
        challengegame.put(p1.getName(), game);
    }
}