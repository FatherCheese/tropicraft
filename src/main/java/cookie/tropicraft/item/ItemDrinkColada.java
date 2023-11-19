package cookie.tropicraft.item;

import cookie.tropicraft.Tropicraft;
import cookie.tropicraft.TropicraftConfig;
import cookie.tropicraft.TropicraftPreLaunch;
import cookie.tropicraft.block.TropicraftBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.Dimension;
import net.minecraft.core.world.World;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.season.Seasons;

public class ItemDrinkColada extends ItemDrink {

    public ItemDrinkColada(int id) {
        super(id);
    }

	private void seasonTimeShift(World world, Season season, int sunsetTime, int nightTime) {
		if (world.seasonManager.getCurrentSeason() == season && world.dimension.id != TropicraftConfig.cfg.getInt("Tropicraft.tropicsDimID"))
			if (world.getWorldTime() > sunsetTime && world.getWorldTime() < nightTime)
				dimensionShift(TropicraftPreLaunch.tropicsDimension.id);
	}

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isClientSide) {
			seasonTimeShift(world, Seasons.OVERWORLD_SPRING, 10250, 14000);
			seasonTimeShift(world, Seasons.OVERWORLD_SUMMER, 13750, 15000);
			seasonTimeShift(world, Seasons.OVERWORLD_FALL, 13250, 11500);
			seasonTimeShift(world, Seasons.OVERWORLD_WINTER, 9750, 11000);
		}
        return super.onItemRightClick(itemstack, world, entityplayer);
    }

	public static void dimensionShift(int targetDimension){
		Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
		EntityPlayer player = mc.thePlayer;
		World world = mc.theWorld;

		Dimension lastDim = Dimension.getDimensionList().get(player.dimension);
		Dimension newDim = Dimension.getDimensionList().get(targetDimension);
		System.out.println("Switching to dimension \"" + newDim.getTranslatedName() + "\"!!");
		player.dimension = targetDimension;
		world.setEntityDead(player);
		player.removed = false;

		player.moveTo(player.x *= Dimension.getCoordScale(lastDim, newDim), player.y, player.z *= Dimension.getCoordScale(lastDim, newDim), player.yRot, player.xRot);
		if (player.isAlive()) {
			world.updateEntityWithOptionalForce(player, false);
		}

		if (player.isAlive()) {
			world.updateEntityWithOptionalForce(player, false);
		}

		world = new World(world, newDim);
		if (newDim == lastDim.homeDim) {
			mc.changeWorld(world, "Leaving " + lastDim.getTranslatedName(), player);
		} else {
			mc.changeWorld(world, "Entering " + newDim.getTranslatedName(), player);
		}
		player.world = world;
		if (player.isAlive()) {
			player.moveTo(player.x, world.worldType.getMaxY()+1, player.z, player.yRot, player.xRot);
			world.updateEntityWithOptionalForce(player, false);
		}
	}
}
