package cookie.tropicraft;

import cookie.tropicraft.block.TropicraftBlocks;
import cookie.tropicraft.item.TropicraftItems;
import cookie.tropicraft.world.BiomeProviderTropics;
import cookie.tropicraft.world.WorldTypeTropics;
import cookie.tropicraft.world.biome.TropicraftBiomes;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.type.WorldTypes;

public class TropicraftPreLaunch implements PreLaunchEntrypoint {
	static {
		// DO NOT TOUCH THIS! It's an error prevention method. Thanks Useless!
		try {
			Class.forName("turniplabs.halplibe.HalpLibe");
			Class.forName("net.minecraft.core.block.Block");
			Class.forName("net.minecraft.core.item.Item");
		} catch (ClassNotFoundException ignored) {}
	}
	public static Dimension tropicsDimension;
	public static WorldType tropicsWorld;
	@Override
	public void onPreLaunch() {

		new TropicraftConfig();

		new TropicraftBlocks().initializeBlocks();
		new TropicraftItems().initializeItems();

		new TropicraftBiomes().initializeBiomes();
		BiomeProviderTropics.init();

		tropicsWorld = WorldTypes.register("worldtype.tropicraft.tropics", new WorldTypeTropics("tropicraft.worldtype.tropics"));
		tropicsDimension = new Dimension("tropicraft.tropics", Dimension.overworld, 1.0f, TropicraftBlocks.tropicsPortal.id);
		tropicsDimension.setDefaultWorldType(tropicsWorld);

		Dimension.registerDimension(TropicraftConfig.cfg.getInt("Tropicraft.tropicsDimID"), tropicsDimension);
	}
}
