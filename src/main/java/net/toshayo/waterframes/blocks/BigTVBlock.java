package net.toshayo.waterframes.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.BigTVTileEntity;
import toshayopack.team.creative.creativecore.common.util.math.AlignedBox;
import toshayopack.team.creative.creativecore.common.util.math.Axis;
import toshayopack.team.creative.creativecore.common.util.math.Facing;

public class BigTVBlock extends DisplayBlock {
    public BigTVBlock() {
        super();

        setBlockName("big_tv");
        setBlockTextureName(WaterFramesMod.MOD_ID + ":frame_back");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new BigTVTileEntity();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    public static AlignedBox box(EnumFacing direction, boolean renderMode) {
        Facing facing = Facing.get(direction);
        AlignedBox box = new AlignedBox();

        float renderMargin = renderMode ? 1f : 0;

        // fit
        if (facing.positive) {
            box.setMax(facing.axis, (4f / 16.0f));
            box.setMin(facing.axis, (2f / 16.0f));
        } else {
            box.setMax(facing.axis, 1f - (2f / 16.0f));
            box.setMin(facing.axis, 1f - (4f / 16.0f));
        }

        Axis one = facing.one();
        Axis two = facing.two();

        if (facing.axis != Axis.Z) {
            one = facing.two();
            two = facing.one();
        }

        // fit height
        box.setMin(two, (2f + renderMargin) / 16f);
        box.setMax(two, 2 - (renderMargin / 16f));

        // fit width
        box.setMin(one, (-14f + renderMargin) / 16f);
        box.setMax(one, (30f - renderMargin) / 16f);

        return box;
    }
}
