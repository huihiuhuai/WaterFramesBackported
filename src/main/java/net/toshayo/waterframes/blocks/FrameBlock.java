package net.toshayo.waterframes.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.FrameTileEntity;

public class FrameBlock extends DisplayBlock {
    public static final float THICKNESS = 0.0625F / 2F;

    @SideOnly(Side.CLIENT)
    private IIcon faceIcon;

    public FrameBlock() {
        super();

        setBlockName("frame");
        setBlockTextureName(WaterFramesMod.MOD_ID + ":frame_back");
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
        //return side;
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if(side == 5) {
            side = 4;
        } else if(side == 4) {
            side = 5;
        }
        if (side == meta) {
            return faceIcon;
        }
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        faceIcon = register.registerIcon(WaterFramesMod.MOD_ID + ":frame_face");
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0:
                this.setBlockBounds(0, 1 - THICKNESS, 0, 1, 1, 1);
                break;
            case 1:
                this.setBlockBounds(0, 0, 0, 1, THICKNESS, 1);
                break;
            case 2:
                this.setBlockBounds(0, 0, 1 - THICKNESS, 1, 1, 1);
                break;
            case 3:
                this.setBlockBounds(0, 0, 0, 1, 1, THICKNESS);
                break;
            case 4:
                this.setBlockBounds(0, 0, 0, THICKNESS, 1, 1);
                break;
            case 5:
                this.setBlockBounds(1 - THICKNESS, 0, 0, 1, 1, 1);
                break;
            default:
                break;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new FrameTileEntity();
    }

    @Override
    public boolean canHideModel() {
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
    }
}
