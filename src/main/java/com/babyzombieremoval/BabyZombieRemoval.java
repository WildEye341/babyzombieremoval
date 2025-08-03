package com.babyzombieremoval;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.TypeFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BabyZombieRemoval implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("BabyZombieRemoval");
	@Override
	public void onInitialize() {
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			for (ZombieEntity zombie : world.getEntitiesByType(
					TypeFilter.instanceOf(ZombieEntity.class),
					ZombieEntity::isBaby
			)) {
				zombie.remove(Entity.RemovalReason.DISCARDED);
				LOGGER.info("Deleted a baby zombie.");
			}
		});
	}
}
