package red.jad.sandbag.registry;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import red.jad.sandbag.Sandbag;
import red.jad.sandbag.item.SandbagItem;

@Mod.EventBusSubscriber(modid = Sandbag.MOD_ID, bus = Bus.MOD)
@ObjectHolder(Sandbag.MOD_ID)
public class ItemRegistry {
	public static Item sandbag;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register( new SandbagItem() );
	}
}
