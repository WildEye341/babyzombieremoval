package com.babyzombieremoval;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.TypeFilter;

public class BabyZombieRemoval implements ModInitializer {
	@Override
	public void onInitialize() {
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			for (ZombieEntity zombie : world.getEntitiesByType(
					TypeFilter.instanceOf(ZombieEntity.class),
					ZombieEntity::isBaby
			)) {
				zombie.remove(Entity.RemovalReason.DISCARDED);
				System.out.println("[BabyZombieRemoval] Deleted a baby zombie.");
			}
		});
	}
}
