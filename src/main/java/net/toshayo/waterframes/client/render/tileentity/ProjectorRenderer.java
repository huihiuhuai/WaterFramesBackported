package net.toshayo.waterframes.client.render.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.DisplayTileEntity;

public class ProjectorRenderer extends DisplayRenderer {
    private static final ResourceLocation PROJECTOR_TEXTURE = new ResourceLocation(WaterFramesMod.MOD_ID, "textures/blocks/projector.png");
    private static final IModelCustom PROJECTOR_MODEL = AdvancedModelLoader.loadModel(new ResourceLocation(WaterFramesMod.MOD_ID, "models/projector.obj"));


    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        renderModel(PROJECTOR_TEXTURE, PROJECTOR_MODEL, x, y, z, ((DisplayTileEntity) tileEntity).blockFacing, true);
        super.renderTileEntityAt(tileEntity, x, y, z, partialTicks);
    }
}
