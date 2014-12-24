package me.ifswitch.cmds;

import java.io.File;

import me.ifswitch.Game;
import me.ifswitch.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

public class PlaySoundCMD implements CommandExecutor {
	
	private Game plugin = Game.getInstance();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String commandLabel, String[] args) {
		Utils.broadcastMsg("Now playing " + args[0]);
		
		
	        if (args.length > 0) {
	            if (args[0].equalsIgnoreCase(args[0])) {
	            		if(new File(plugin.getDataFolder(), args[0]).exists()) {
	            			Song song = NBSDecoder.parse(new File(plugin.getDataFolder(), args[0]));
	    	        		SongPlayer sp = new RadioSongPlayer(song);
	    	        		sp.setAutoDestroy(true);
	    	        		for(Player p : Bukkit.getOnlinePlayers()) {
	    	        			sp.addPlayer(p);
	    	        		}
	    	        		sp.setPlaying(true);
	            		}else {
	            			s.sendMessage("§0[§b§lArkham§6§lDot§e§lNetwork§0]" + "§aI'm having a hard time finding that file...");
	            		}
	            }
	        }
		
		
		return false;
	}

}
