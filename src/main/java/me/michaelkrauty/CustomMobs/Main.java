package me.michaelkrauty.CustomMobs;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

/**
 * Created on 7/3/2015.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntity().hasMetadata("CustomMob"))
            return;
        if (event.getEntityType() == EntityType.ZOMBIE) {
            if (new Random().nextInt(99) == 0) {
                Zombie zombie = (Zombie) event.getEntity();
                Giant giant = (Giant) zombie.getWorld().spawnEntity(zombie.getLocation(), EntityType.GIANT);
                giant.setMaxHealth(750);
                giant.setHealth(750);
                giant.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 72000, 5));
                zombie.remove();
                giant.setMetadata("giant", new FixedMetadataValue(this, true));
                giant.setMetadata("CustomMob", new FixedMetadataValue(this, true));
            }
            if (new Random().nextInt(14) == 0) {
                Zombie zombie = (Zombie) event.getEntity();
                zombie.setMaxHealth(250);
                zombie.setHealth(250);
                zombie.setCustomName("Boss Zombie");
                zombie.setCustomNameVisible(true);
                zombie.setMetadata("CustomMob", new FixedMetadataValue(this, true));
                return;
            }
            if (new Random().nextInt(9) == 0) {
                Zombie zombie = (Zombie) event.getEntity();
                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 2));
                zombie.setCustomName("Runner Zombie");
                zombie.setCustomNameVisible(true);
                zombie.setMetadata("CustomMob", new FixedMetadataValue(this, true));
                return;
            }
        }

        if (event.getEntityType() == EntityType.SPIDER) {
            if (new Random().nextInt(14) == 0) {
                Spider spider = (Spider) event.getEntity();
                CaveSpider caveSpider = (CaveSpider) spider.getWorld().spawnEntity(spider.getLocation(), EntityType.CAVE_SPIDER);
                caveSpider.setMetadata("CustomMob", new FixedMetadataValue(this, true));
                spider.remove();
                return;
            }
            if (new Random().nextInt(9) == 0) {
                Spider spider = (Spider) event.getEntity();
                spider.setMaxHealth(13);
                spider.setHealth(13);
                spider.setCustomName("Venomous Spider");
                spider.setCustomNameVisible(true);
                spider.setMetadata("venom", new FixedMetadataValue(this, true));
                spider.setMetadata("CustomMob", new FixedMetadataValue(this, true));
                return;
            }
        }

        if (event.getEntityType() == EntityType.SKELETON) {
            if (new Random().nextInt(999) == 0) {
                Skeleton skeleton = (Skeleton) event.getEntity();
                skeleton.setCustomName("Boner");
                skeleton.setCustomNameVisible(true);
                skeleton.setMetadata("CustomMob", new FixedMetadataValue(this, true));
            }
        }

        if (event.getEntityType() == EntityType.GHAST) {
            if (new Random().nextInt(24) == 0) {
                Ghast ghast = (Ghast) event.getEntity();
                ghast.setMetadata("chargedghast", new FixedMetadataValue(this, true));
                ghast.setCustomName("Charged Ghast");
                ghast.setCustomNameVisible(true);
                ghast.setMaxHealth(ghast.getMaxHealth() * 2);
                ghast.setHealth(ghast.getMaxHealth());
                ghast.setMetadata("CustomMob", new FixedMetadataValue(this, true));
            }
        }

        if (event.getEntityType() == EntityType.CREEPER) {
            if (new Random().nextInt(19) == 0) {
                Creeper creeper = (Creeper) event.getEntity();
                creeper.setPowered(true);
                creeper.setMetadata("CustomMob", new FixedMetadataValue(this, true));
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager().hasMetadata("venom")) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().hasMetadata("giant"))
            event.setDroppedExp(500);
    }
}
