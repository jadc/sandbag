package red.jad.sandbag.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import red.jad.sandbag.Sandbag;

public class SoundRegistry {
	public static SoundEvent SANDBAG_STRIKE;
	
	public static void registerSounds() {
		SANDBAG_STRIKE = new SoundEvent(new ResourceLocation(Sandbag.MOD_ID, "strike"));
	}
}
