package net.toshayo.waterframes.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.DisplayTileEntity;
import org.lwjgl.opengl.GL11;

public class FrameRenderer extends DisplayRenderer {
    private RenderBlocks renderBlocks;

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if(tileEntity.getWorldObj() == null || renderBlocks == null) {
            return;
        }

        if(((DisplayTileEntity) tileEntity).isVisible()) {
            Tessellator tessellator = Tessellator.instance;
            this.bindTexture(TextureMap.locationBlocksTexture);
            RenderHelper.disableStandardItemLighting();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_CULL_FACE);
            if (Minecraft.isAmbientOcclusionEnabled()) {
                GL11.glShadeModel(GL11.GL_SMOOTH);
            } else {
                GL11.glShadeModel(GL11.GL_FLAT);
            }

            tessellator.startDrawingQuads();
            tessellator.setTranslation(x - (float) tileEntity.xCoord, y - (float) tileEntity.yCoord, z - (float) tileEntity.zCoord);
            tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

            WaterFramesMod.FRAME.setBlockBoundsBasedOnState(tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
            renderBlocks.setRenderBoundsFromBlock(WaterFramesMod.FRAME);
            renderBlocks.renderStandardBlock(WaterFramesMod.FRAME, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

            tessellator.setTranslation(0.0, 0.0, 0.0);
            tessellator.draw();
            RenderHelper.enableStandardItemLighting();
        }

        super.renderTileEntityAt(tileEntity, x, y, z, partialTicks);
    }

    @Override
    public void func_147496_a(World world) {
        renderBlocks = new RenderBlocks(world);
    }

    @Override
    public EnumFacing direction(DisplayTileEntity tile) {
        return EnumFacing.getFront(tile.getBlockMetadata());
    }
}
