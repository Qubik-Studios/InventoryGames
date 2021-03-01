package ch.maxthier.manager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChallengeTabcompleter implements TabCompleter {

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1){
            arguments.add("accept"); arguments.add("decline");
            for (Player p : Bukkit.getOnlinePlayers()){
                arguments.add(p.getName());
            }
            List<String> result = new ArrayList<String>();
            for (String a : arguments){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(a);
                }
            }
            arguments.clear();
            return result;
        }
        if(args.length == 2){
            if(!args[0].equalsIgnoreCase("accept") && !args[0].equalsIgnoreCase("decline")){
                arguments.add("tictactoe");
                List<String> result = new ArrayList<String>();
                for (String a : arguments){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase())){
                        result.add(a);
                    }
                }
                arguments.clear();
                return result;
            }
        }
        return null;
    }
}
