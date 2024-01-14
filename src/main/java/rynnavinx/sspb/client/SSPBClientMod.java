package rynnavinx.sspb.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rynnavinx.sspb.client.gui.SSPBGameOptions;
import rynnavinx.sspb.reflection.ReflectionAoFaceData;
import rynnavinx.sspb.reflection.ReflectionSmoothLightPipeline;

@Mod(SSPBClientMod.MODID)
@OnlyIn(Dist.CLIENT)
public class SSPBClientMod {
	public static final String MODID = "sspb";
	public static final Logger LOGGER = LoggerFactory.getLogger("SSPB");
	private static SSPBGameOptions CONFIG;


	public static SSPBGameOptions options() {
		return CONFIG;
	}

	public SSPBClientMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInitializeClient);

		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

	}

	//@Override
	public void onInitializeClient(final FMLClientSetupEvent event) {
		try {
			ReflectionAoFaceData.InitReflectionAoFaceData();
			ReflectionSmoothLightPipeline.InitReflectionSmoothLightPipeline();

			CONFIG = SSPBGameOptions.load();
			CONFIG.updateShadowyness(CONFIG.shadowynessPercent);

			LOGGER.info("[SSPB] Broken dirt path lighting is best dirt path lighting lol");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
