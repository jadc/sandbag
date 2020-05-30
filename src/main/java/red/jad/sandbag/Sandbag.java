package red.jad.sandbag;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import red.jad.sandbag.client.entity.render.SandbagEntityRender;
import red.jad.sandbag.registry.EntityRegistry;
import red.jad.sandbag.registry.SoundRegistry;

@Mod("sandbag")
public class Sandbag {
	public static final String MOD_ID = "sandbag";

    public Sandbag() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        EntityRegistry.ENTITY_TYPES.register(modEventBus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void setup(final FMLCommonSetupEvent event) {}

    private void doClientStuff(final FMLClientSetupEvent event) {
    	RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.SANDBAG.get(), SandbagEntityRender::new);
    	SoundRegistry.registerSounds();
    }
}
