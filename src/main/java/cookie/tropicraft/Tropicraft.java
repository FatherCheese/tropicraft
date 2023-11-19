package cookie.tropicraft;

import cookie.tropicraft.entity.TropicraftEntities;
import cookie.tropicraft.util.ContainerPlayerExtender;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.helper.TextureHelper;

public class Tropicraft implements ModInitializer {
    public static final String MOD_ID = "tropicraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // These are used by RenderBlocksMixin
    public static final int[] citrusOverlay = TextureHelper.getOrCreateBlockTexture(MOD_ID, "leaves_citrus_overlay.png");
	public static final int[] floweringOverlay = TextureHelper.getOrCreateBlockTexture(MOD_ID, "leaves_flowering_overlay.png");
	public static final int[][] torchTextures = new int[][]{
		TextureHelper.getOrCreateBlockTexture(Tropicraft.MOD_ID, "tikitorch_bottom.png"),
		TextureHelper.getOrCreateBlockTexture(Tropicraft.MOD_ID, "tikitorch_top.png")
	};
    @Override
    public void onInitialize() {
		SoundHelper.addSound(MOD_ID, "bonk.wav");
        SoundHelper.addSound(MOD_ID, "gulp.wav");
		SoundHelper.addSound(MOD_ID, "frog.wav");
		SoundHelper.addSound(MOD_ID, "boom.wav");

		new TropicraftEntities().initializeEntities();

		new ContainerPlayerExtender().initializeExtender();

        LOGGER.info("Tropicraft initialized. Have a happy vacation!");
    }
}
