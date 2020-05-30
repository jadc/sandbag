package red.jad.sandbag.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import red.jad.sandbag.Sandbag;
import red.jad.sandbag.entity.SandbagEntity;

public class EntityRegistry {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
			Sandbag.MOD_ID);

	public static final RegistryObject<EntityType<SandbagEntity>> SANDBAG = ENTITY_TYPES.register("sandbag",
			() -> EntityType.Builder.<SandbagEntity>create(SandbagEntity::new, EntityClassification.MISC)
					.size(0.9f, 2.0f).build(new ResourceLocation(Sandbag.MOD_ID, "sandbag").toString()));
}
