package me.ifswitch.events;

import java.net.InetAddress;
import java.util.Random;

import me.ifswitch.ChangePlayerCount;
import me.ifswitch.Game;
import me.ifswitch.Utils;
import me.ifswitch.board.SimpleScoreboard;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvents implements Listener {
	
	private Game plugin = Game.getInstance();
	
	World w = Bukkit.getWorld("world");
	Location spawnLoc = new Location(w,-96,4,-302);
	
	@EventHandler
	public void onTalk(AsyncPlayerChatEvent event) {
		event.setFormat("\n §0§l[§f§kt§0§l[§c§lJ§6§l0§a§lK§b§lE§d§lR§0§l]§f§kt§0§l] §f§l" + event.getPlayer().getName() + " §e§l" + event.getMessage() + "\n");
	}
	
	@EventHandler
	public void stopDragonDamage(EntityExplodeEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent event) {
		Random rand = new Random();
		int randomint = rand.nextInt(3 - 0 + 1) + 0;
		switch(randomint) {
		//event.setMotd("                     §b§l§nArkham§6§l§nDot§e§l§nNetwork§f"); middlepef
		case 0:
			event.setMotd("            §f§l§o*§a§l#§f§l*    §e§l§nArkham§m.§b§l§nNetwork§r §f§l§o*§a§l#§f§l*    \n§r §c§l(§f§l*§a§l)§f§l 40% OFF CHRISTMAS SALE, New Prison! §c§l(§f§l*§a§l)");
			break;
		case 1:
			event.setMotd("            §f§l§o*§a§l#§f§l*    §e§l§nArkham§m.§b§l§nNetwork§r §f§l§o*§a§l#§f§l*    \n§r §c§l(§f§l*§a§l)§f§l 50% OFF CHRISTMAS SALE, New Prison! §c§l(§f§l*§a§l)");
			break;
		case 2:
			event.setMotd("            §f§l§o*§a§l#§f§l*    §e§l§nArkham§m.§b§l§nNetwork§r §f§l§o*§a§l#§f§l*    \n§r §c§l(§f§l*§a§l)§f§l 60% OFF CHRISTMAS SALE, New Prison! §c§l(§f§l*§a§l)");
			break;
		case 3:
			event.setMotd("            §f§l§o*§a§l#§f§l*    §e§l§nArkham§m.§b§l§nNetwork§r §f§l§o*§a§l#§f§l*    \n§r §c§l(§f§l*§a§l)§f§l 70% OFF CHRISTMAS SALE, New Prison! §c§l(§f§l*§a§l)");
			break;
			default:
				event.setMotd("\t WAWDJHKDWJHKW");
				break;
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		event.setFoodLevel(100);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if(event.getInventory().equals(Game.heromenu)) {
			event.setCancelled(true);
			event.getWhoClicked().getOpenInventory().close();
			Utils.SoundAt("world", p.getLocation(), Sound.ANVIL_BREAK, 20f, 20f);
			p.sendMessage("§cYou do not have permission to use this particle!");
		}else if(event.getInventory().equals(Game.compass)) {
			event.setCancelled(true);
			event.getWhoClicked().getOpenInventory().close();
		}else if(event.getInventory().equals(Game.herocostuomes)) {
			event.setCancelled(true);
			event.getWhoClicked().getOpenInventory().close();
			p.sendMessage("§cUnlock this costume at §nbuy.arkham.network");
		}else if(event.getInventory().equals(Game.heroicradio)) {
			event.setCancelled(true);
			event.getWhoClicked().getOpenInventory().close();
		}else if(event.getInventory().equals(Game.shopmenu)) {
			event.setCancelled(true);
			event.getWhoClicked().getOpenInventory().close();
			Utils.SoundAt("world", p.getLocation(), Sound.ANVIL_BREAK, 20f, 20f);
			p.sendMessage("§cThis isn't setup yet! Check back soon!");
		}
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent event) {
		Player p = event.getPlayer();
			if(p.getItemInHand().getType() == Material.COMPASS) {
				p.openInventory(Game.compass);
				p.sendMessage("§e§lLoading available servers ...");
			}else if(p.getItemInHand().getType() == Material.BOOK) {
				event.setCancelled(true);
				p.sendMessage("§eLoading available titles...\n§cYou have not yet unlocked any titles!\n§7Titles may be gained through accomplishing achievements in-game or purchasing them with /buy!");
			}else if(p.getItemInHand().getType() == Material.JUKEBOX) {
				p.openInventory(Game.heroicradio);
			}else if(p.getItemInHand().getType() == Material.SUGAR) {
				if(!p.hasPotionEffect(PotionEffectType.SPEED)) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 5));
					int i = 0;
					for(; i<= 30; i++) {
						p.sendMessage("");
					}
					p.sendMessage("§e\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
					p.sendMessage("");
					p.sendMessage("§bArkham§6§lDot§eNetwork");
					p.sendMessage("§7You have activated the power of Blaze!");
					p.sendMessage("");
					p.sendMessage("§b\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
					p.sendMessage("");
					Utils.SoundAt("world", p.getLocation(), Sound.ANVIL_USE, 20f, 20f);
				}else if (p.hasPotionEffect(PotionEffectType.SPEED)){
					p.removePotionEffect(PotionEffectType.SPEED);
					int i = 0;
					for(; i<= 30; i++) {
						p.sendMessage("");
					}
					p.sendMessage("§e\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
					p.sendMessage("");
					p.sendMessage("§bArkham§6§lDot§eNetwork");
					p.sendMessage("§7You have de-activated the power of Blaze!");
					p.sendMessage("");
					p.sendMessage("§b\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
					p.sendMessage("");
					Utils.SoundAt("world", p.getLocation(), Sound.ANVIL_BREAK, 20f, 20f);
				}
			}else if(p.getItemInHand().getType() == Material.ENDER_CHEST) {
				p.openInventory(Game.herocostuomes);
			}else if(p.getItemInHand().getType() == Material.BLAZE_POWDER) {
				p.openInventory(Game.heromenu);
			}else if(p.getItemInHand().getType() == Material.CHEST) {
				p.openInventory(Game.shopmenu);
			}
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent event) {
		InetAddress in = event.getAddress();
		if(plugin.getConfig().getBoolean("force-ip-match") == true){
			if(!in.toString().equals(plugin.getConfig().getString("force-login-ip"))) {
				event.setKickMessage(plugin.getConfig().getString("kick-mismatch-reason"));
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.getPlayer().getInventory().clear();
	}
	
	@EventHandler
	public void onJ(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		p.teleport(spawnLoc);
		Utils.SoundAt("world", p.getLocation(), Sound.CAT_MEOW, 20f, 20f);
		int i = 0;
		for(; i<= 11; i++) {
			p.sendMessage("");
		}
		
		p.sendMessage("§e\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
		p.sendMessage("");
		p.sendMessage("           " + "Welcome, §b§l" + p.getName() + "§f to " + "§b§lArkham§6§lDot§e§lNetwork");
		p.sendMessage("                 " + "§7§oHome of all Minecraft Negros!");
		p.sendMessage("");
		p.sendMessage("");
		p.sendMessage("                 " + "§a§lSHOP " + "§f§nbuy.arkham.network");
		p.sendMessage("              " + "§c§lFORUMS " + "§f§narkham.network/community");
		p.sendMessage("");
		p.sendMessage("            " + "§7There are currently " + "§b§l§n" + "4224§7" + "§f§7 players online.");
		p.sendMessage("");
		p.sendMessage("§b\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587\u2587");
		p.sendMessage("");
		p.sendMessage("");
		
		p.getInventory().addItem(Game.createItem(Material.COMPASS, 1, 0, "§e§lServer Selector §7(Right Click)", "§bRight click to open the server selector."));
		p.getInventory().addItem(Game.createItem(Material.CHEST, 1, 0, "§e§lServer Shop §7(Right Click)", "§bPurchase ranks, pets, trails and more!"));
		p.getInventory().addItem(Game.createItem(Material.BOOK, 1, 0, "§e§lPlayer Titles §7(Right Click)", "§bClick to open player title selector."));
		//p.getInventory().addItem(Game.createItem(Material.BONE, 1, 0, "§e§lHero Sidekicks §7(Right Click)", "§bSummon your trust adventuring companions!"));
		p.getInventory().addItem(Game.createItem(Material.BLAZE_POWDER, 1, 0, "§e§lHero Powers §7(Right Click)", "§bClick to open power selector."));
		p.getInventory().addItem(Game.createItem(Material.ENDER_CHEST, 1, 0, "§e§lHero Costumes §7(Right Click)", "§bCustomize your hero's apperance!"));
		p.getInventory().addItem(Game.createItem(Material.JUKEBOX, 1, 0, "§e§lHeroic Radio §7(Right Click)", "§bListen to awesome music while adventuring!"));
		p.getInventory().addItem(Game.createItem(Material.SUGAR, 1, 0, "§e§lBlaze Speed §7(Right Click)", "§bFeel the speed of Blaze himself!"));
		p.getInventory().addItem(Game.createItem(Material.FISHING_ROD, 1, 0, "§e§lGrappling Hook §7(Right Click)", "§bA fishingrod, yet so much more."));

		
		// create a new scoreboard with title
		SimpleScoreboard scoreboard = new SimpleScoreboard("§b§lArkham§6§lDot§e§lNetwork");
		// text with custom score
		scoreboard.blankLine();
		scoreboard.add("§6§lTokens", 14);
		scoreboard.add("§f0");
		// also supports blank lines (up to 23 of them!)
		scoreboard.blankLine();
		scoreboard.add("§a§lVotes");
		scoreboard.add("§f0");
		scoreboard.blankLine();
		// if you dont specify a score it will display them in the order you add them in
		scoreboard.add("§c§lPlayer Count");
		//scoreboard.add("§f2712");
		String playerCount = Integer.toString(ChangePlayerCount.actual_count);
		scoreboard.add(playerCount);
		scoreboard.blankLine();
		scoreboard.add("§9§lServer");
		scoreboard.add("§fHub #1");
		scoreboard.blankLine();
		scoreboard.add("§d§lTotal Joined");
		scoreboard.add("§f2,767,827");
		scoreboard.build();
		
		scoreboard.set(p);
		
		Utils.broadcastM(p.getName());
	}

}
