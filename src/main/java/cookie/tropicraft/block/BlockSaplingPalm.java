package cookie.tropicraft.block;

import cookie.tropicraft.world.feature.WorldFeatureTreeCitrus;
import cookie.tropicraft.world.feature.WorldFeatureTreePalm;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSaplingBase;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

import java.util.Random;

public class BlockSaplingPalm extends BlockSaplingBase {

	public BlockSaplingPalm(String key, int id) {
		super(key, id);
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random random) {
		world.setBlock(x, y, z, 0);
		WorldFeature tree = new WorldFeatureTreePalm();
		if (!tree.generate(world, random, x, y, z))
			world.setBlock(x, y, z, this.id);
	}
}
