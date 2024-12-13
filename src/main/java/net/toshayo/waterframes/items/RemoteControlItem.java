package net.toshayo.waterframes.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.toshayo.waterframes.WFConfig;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.DisplayTileEntity;
import net.toshayo.waterframes.utils.ChatUtils;

public class RemoteControlItem extends Item {
    public RemoteControlItem() {
        setUnlocalizedName("remote");
        setTextureName(WaterFramesMod.MOD_ID + ":remote");
        setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return handleClick(stack, player, world, x, y, z, true);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        handleClick(stack, player, world, 0, 0, 0, false);
        return super.onItemRightClick(stack, world, player);
    }

    private boolean handleClick(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, boolean isPosValid) {
        if(!WFConfig.canInteractItem(player)) {
            if(world.isRemote) {
                ChatUtils.fatalMessage(player, "waterframes.common.access.denied");
            }
            return false;
        }

        NBTTagCompound nbt = stack.getTagCompound();
        if(nbt != null && nbt.hasNoTags()) {
            if(world.isRemote) {
                ChatUtils.errorMessage(player, "waterframes.remote.bound.failed");
            }
            return false;
        }


        if(player.isSneaking()) {
            if(nbt != null && !nbt.hasNoTags()) {
                stack.setTagCompound(null);
                if(world.isRemote) {
                    ChatUtils.successMessage(player, "waterframes.remote.unbound.success");
                }
                return true;
            } else {
                if(!isPosValid) {
                    return false;
                }
                TileEntity tile = world.getTileEntity(x, y, z);
                if(!(tile instanceof DisplayTileEntity)) {
                    if(world.isRemote) {
                        ChatUtils.errorMessage(player, "waterframes.remote.display.invalid");
                    }
                    return false;
                }
                if(nbt == null) {
                    nbt = new NBTTagCompound();
                }
                nbt.setInteger("DIM", world.provider.dimensionId);
                nbt.setIntArray("POS", new int[]{x, y, z});
                stack.setTagCompound(nbt);
                if(world.isRemote) {
                    ChatUtils.successMessage(player, "waterframes.remote.bound.success");
                }
                return true;
            }
        }

        if(nbt == null || !nbt.hasKey("DIM") || !nbt.hasKey("POS")) {
            if(world.isRemote) {
                ChatUtils.errorMessage(player, "waterframes.remote.bound.failed");
            }
            return false;
        }

        int dimId = nbt.getInteger("DIM");
        int[] pos = nbt.getIntArray("POS");
        if(pos.length != 3) {
            if(world.isRemote) {
                ChatUtils.errorMessage(player, "waterframes.remote.bound.failed");
            }
            return false;
        }

        if(world.provider.dimensionId != dimId || Vec3.createVectorHelper(pos[0] + 0.5, pos[1] + 0.5, pos[2] + 0.5)
                .squareDistanceTo(player.posX, player.posY, player.posZ) >= WFConfig.maxRcDis() * WFConfig.maxRcDis()) {
            if(world.isRemote) {
                ChatUtils.errorMessage(player, "waterframes.remote.distance.failed");
            }
            return false;
        }

        TileEntity tile = world.getTileEntity(pos[0], pos[1], pos[2]);

        if(!(tile instanceof DisplayTileEntity)) {
            if(world.isRemote) {
                ChatUtils.errorMessage(player, "waterframes.remote.display.invalid");
            }
            return false;
        }

        if(world.isRemote)
            player.openGui(WaterFramesMod.INSTANCE, 1, world, pos[0], pos[1], pos[2]);

        return false;
    }
}
