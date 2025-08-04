package com.babyzombieremoval;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.TypeFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class BabyZombieRemoval implements ModInitializer {
	public static final Logger LOGGER = LogManager.getRootLogger();
	@Override
	public void onInitialize() {
		LogManager.getRootLogger().info("[BabyZombieRemoval] initialized");
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			for (ZombieEntity zombie : world.getEntitiesByType(
					TypeFilter.instanceOf(ZombieEntity.class),
					ZombieEntity::isBaby
			)) {
				zombie.remove(Entity.RemovalReason.DISCARDED);
				System.out.println("[BabyZombieRemoval] has deleted a baby zombie.");
				LogManager.getRootLogger().warn("[BabyZombieRemoval] has deleted a baby zombie.");
			}
		});
	}
}
