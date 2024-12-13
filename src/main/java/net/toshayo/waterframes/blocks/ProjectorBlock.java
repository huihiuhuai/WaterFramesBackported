package net.toshayo.waterframes.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.ProjectorTileEntity;

public class ProjectorBlock extends DisplayBlock {
    public ProjectorBlock() {
        super();

        setBlockName("projector");
        setBlockTextureName(WaterFramesMod.MOD_ID + ":frame_back");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ProjectorTileEntity();
    }
}
