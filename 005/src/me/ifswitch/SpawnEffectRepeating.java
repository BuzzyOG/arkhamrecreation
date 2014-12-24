package me.ifswitch;

import me.ifswitch.ref.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SpawnEffectRepeating implements Runnable {
	
	World w = Bukkit.getWorld("world");
	Location emiteLoc = new Location(w, -96,4,-285);

	@Override
	public void run() { 
			try {
				ParticleEffect.REDSTONE.display(25, 0, 25, 100, 1024*38, emiteLoc.add(0, 6, 0), 380);
				emiteLoc.add(0,-6,0);
			}catch(Exception e) {}
	}

}
