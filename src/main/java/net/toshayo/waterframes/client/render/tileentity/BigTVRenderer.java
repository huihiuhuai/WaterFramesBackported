package net.toshayo.waterframes.client.render.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.DisplayTileEntity;

public class BigTVRenderer extends DisplayRenderer {
    private static final ResourceLocation BIG_TV_TEXTURE = new ResourceLocation(WaterFramesMod.MOD_ID, "textures/blocks/big_tv.png");
    private static final IModelCustom BIG_TV_MODEL = AdvancedModelLoader.loadModel(new ResourceLocation(WaterFramesMod.MOD_ID, "models/big_tv.obj"));


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        renderModel(BIG_TV_TEXTURE, BIG_TV_MODEL, x, y, z, ((DisplayTileEntity) tileEntity).blockFacing, false);
        super.renderTileEntityAt(tileEntity, x, y, z, partialTicks);
    }
}
