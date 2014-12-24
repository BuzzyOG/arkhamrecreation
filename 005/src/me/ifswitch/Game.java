package me.ifswitch;

import java.util.ArrayList;
import java.util.Arrays;

import me.ifswitch.cmds.BuyCMD;
import me.ifswitch.cmds.DragonspawnCMD;
import me.ifswitch.cmds.PlaySoundCMD;
import me.ifswitch.events.PlayerEvents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

public class Game extends JavaPlugin implements Listener {

	private static Game plugin;

	public static Scoreboard board;
	private Objective o;

	ArrayList<WrappedGameProfile> players = new ArrayList<WrappedGameProfile>();

	public static Inventory compass;
	public static Inventory heroicradio;
	public static Inventory herocostuomes;
	public static Inventory heromenu;
	public static Inventory shopmenu;

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		plugin = this;

		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		ProtocolLibrary.getProtocolManager().addPacketListener(
				new PacketAdapter(this, ListenerPriority.NORMAL,
						Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {

					@Override
					public void onPacketSending(PacketEvent event) {
						handlePing(event.getPacket().getServerPings().read(0));
					}
				});
		this.getServer().getPluginManager().registerEvents(this, this);
		create_invs();
		reg_Events();

		board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

		o = board.registerNewObjective("test", "dummy");
		o.setDisplayName("Steps");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);

		//Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new BossBarRepeating(), 0, 10);
		new Thread(new BossBarRepeating()).start();
		
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new AnnouncerRepeating(), 0, 20*15);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ChangePlayerCount(), 0, 20*1);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new SpawnEffectRepeating(), 0, 20*3);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PlayerHeartRepeating(), 0, 20*2);
		
		//Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ScoreboardRepeating(), 0, 20);
		
		getCommand("spawndragon").setExecutor(new DragonspawnCMD());
		getCommand("buy").setExecutor(new BuyCMD());
		getCommand("play").setExecutor(new PlaySoundCMD());

		for(Entity e : Bukkit.getWorld("world").getEntities()) {
			if(!(e instanceof Player)) {
				e.remove();
			}
		}

		saveDefaultConfig();
	}

	@Override
	public void onDisable() {
		this.reloadConfig(); this.saveConfig();
		
	}
	
	

	private void handlePing(WrappedServerPing ping){
		ping.setPlayersMaximum(ChangePlayerCount.actual_count + 1);
		ping.setPlayersOnline(ChangePlayerCount.actual_count);
	}

	private void reg_Events() {
		this.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
	}
	//compass.setItem(0, createItem(Material. , 1, 0, "§", "§"));
	private void create_invs() {
		shopmenu = Bukkit.createInventory(null, 18, "§4Buycraft - Choose a category");
		shopmenu.setItem(0, createItem(Material.DIAMOND_BLOCK, 1, 0, "§dRank Upgrades", "§eID: §d1"));
		shopmenu.setItem(1, createItem(Material.EMERALD_BLOCK , 1, 0, "§dGlobal Ranks", "§eID: §d2"));
		shopmenu.setItem(2, createItem(Material.EXP_BOTTLE , 1, 0, "§dGlobal Buffs", "§eID: §d3"));
		shopmenu.setItem(3, createItem(Material.LEASH , 1, 0, "§dHeroic Sidekicks", "§eID: §d4"));
		shopmenu.setItem(4, createItem(Material.PORTAL , 1, 0, "§dHeroic Powers", "§eID: §d5"));
		shopmenu.setItem(5, createItem(Material.ENDER_CHEST , 1, 0, "§dHero Costumes", "§eID: §d6"));
		shopmenu.setItem(6, createItem(Material.NAME_TAG , 1, 0, "§dCasual", "§eID: §d7"));
		shopmenu.setItem(7, createItem(Material.NAME_TAG , 1, 0, "§dPremium", "§eID: §d8"));
		shopmenu.setItem(8, createItem(Material.NAME_TAG , 1, 0, "§dHardcore", "§eID: §d9"));

		shopmenu.setItem(9, createItem(Material.NAME_TAG , 1, 0, "§dLimited Edition", "§eID: §d10"));
		shopmenu.setItem(10, createItem(Material.NAME_TAG , 1, 0, "§dMonthly Limited Edition", "§eID: §11"));
		shopmenu.setItem(11, createItem(Material.NAME_TAG , 1, 0, "§dCustom", "§eID: §d12"));


		compass = Bukkit.createInventory(null, 36, "§8Arkham.Network Servers");
		compass.setItem(3, createItem(Material.DIAMOND_SWORD, 1, 0, "§f >> §f§l Factions §f <<", "§eClick to browse §nFaction §eservers."));
		
		compass.setItem(5, createItem(Material.MUSHROOM_SOUP, 1, 0, "§f >> §f§l KitPvp §f <<", "§eClick to browse §nKitPvp §eservers."));

		heroicradio = Bukkit.createInventory(null, 9, "§0[§blArkham§elRadio§0]");
		heroicradio.setItem(2, createItem(Material.CLAY, 1, 0, "§2Start Radio", "§eListen to our in-game radio!"));
		heroicradio.setItem(6, createItem(Material.CLAY , 1, 0, "§4Stop Radio", "§eStop da radio!"));

		herocostuomes = Bukkit.createInventory(null, 36, "§0Arkham Hero Costumes");
		herocostuomes.setItem(0, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lBat Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(1, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lBlaze Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(2, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lCavespider Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(2, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lChicken Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(3, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lCow Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(4, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lCreeper Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(5, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lEnderdragon Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(6, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lGhast Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(7, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lGiant Costume", "§f§7unlock at §b§nbuy.arkham.network"));

		herocostuomes.setItem(8, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lHorse Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(9, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lDonkey Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(10, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lMule Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(11, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSkeletonhorse Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(12, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lMagmacube Custume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(13, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lMushroomcow Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(14, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lOcelot Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(15, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lPig Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(16, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lPigzombie Costume", "§f§7unlock at §b§nbuy.arkham.network"));

		herocostuomes.setItem(17, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSleep Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(18, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSilverfish Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(19, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSkeleton Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(20, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lWithersheketon Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(21, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSlime Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(22, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSnowman Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(23, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSpider Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(24, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lSquid Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(25, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lVillager Costume", "§f§7unlock at §b§nbuy.arkham.network"));

		herocostuomes.setItem(26, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lWitch Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(27, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lWither Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(28, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lWolfs Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(29, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lZombie Costume", "§f§7unlock at §b§nbuy.arkham.network"));
		herocostuomes.setItem(30, createItem(Material.STAINED_GLASS_PANE, 1, 0, "§c§lZombievillager Costume", "§f§7unlock at §b§nbuy.arkham.network"));

		heromenu = Bukkit.createInventory(null, 54, "§0Arkham Hero Auras");
		heromenu.setItem(10, createItem(Material.WATER, 1, 0, "§aWater Drip", "§7Left click to active"));
		heromenu.setItem(11, createItem(Material.FIREWORK, 1, 0, "§aFirework", "§7Left click to active"));
		heromenu.setItem(12, createItem(Material.LAPIS_BLOCK, 1, 0, "§aColor", "§7Left click to active"));
		heromenu.setItem(13, createItem(Material.ENDER_CHEST, 1, 0, "§aEnchantment", "§7Left click to active"));
		heromenu.setItem(14, createItem(Material.ENDER_PEARL, 1, 0, "§aEnder", "§7Left click to active"));
		heromenu.setItem(15, createItem(Material.TNT, 1, 0, "§aExplosion", "§7Left click to active"));
		heromenu.setItem(16, createItem(Material.LAVA, 1, 0, "§aLava Drip", "§7Left click to active"));

		heromenu.setItem(19, createItem(Material.RED_ROSE, 1, 0, "§aLove", "§7Left click to active"));
		heromenu.setItem(20, createItem(Material.NETHER_STAR, 1, 0, "§aMagic", "§7Left click to active"));
		heromenu.setItem(21, createItem(Material.NOTE_BLOCK, 1, 0, "§aMusic", "§7Left click to active"));
		heromenu.setItem(22, createItem(Material.ENDER_PORTAL_FRAME, 1, 0, "§aPortal", "§7Left click to active"));
		heromenu.setItem(23, createItem(Material.SLIME_BALL, 1, 0, "§aSlime", "§7Left click to active"));
		heromenu.setItem(24, createItem(Material.TORCH, 1, 0, "§aSmoke", "§7Left click to active"));
		heromenu.setItem(25, createItem(Material.SNOW_BALL, 1, 0, "§aSnowball", "§7Left click to active"));

		heromenu.setItem(28, createItem(Material.GLASS_BOTTLE, 1, 0, "§aSpell", "§7Left click to active"));
		heromenu.setItem(29, createItem(Material.WATER_BUCKET, 1, 0, "§aSplash", "§7Left click to active"));
		heromenu.setItem(30, createItem(Material.BEDROCK, 1, 0, "§aVoid", "§7Left click to active"));
		heromenu.setItem(31, createItem(Material.WEB, 1, 0, "§aCloud", "§7Left click to active"));
		heromenu.setItem(32, createItem(Material.FIREWORK_CHARGE, 1, 0, "§aSpark", "§7Left click to active"));
		heromenu.setItem(33, createItem(Material.FLINT_AND_STEEL, 1, 0, "§aFlame", "§7Left click to active"));
		heromenu.setItem(34, createItem(Material.DIAMOND_SWORD, 1, 0, "§aCrit", "§7Left click to active"));

		heromenu.setItem(48, createItem(Material.EYE_OF_ENDER, 1, 0, "§eDeactive current particle!", "§7Left click to deactive a particle"));
		heromenu.setItem(50, createItem(Material.ARROW, 1, 0, "§eNext Page ->", "§7Left click to go to the next page"));

	}

	public static ItemStack createItem(Material material, int amount, int shrt, String displayname, String lore) {
		ItemStack item = new ItemStack(material, amount, (short) shrt);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}

	public static Game getInstance() {
		return plugin;
	}

}
