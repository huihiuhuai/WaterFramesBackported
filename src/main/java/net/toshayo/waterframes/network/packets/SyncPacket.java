package net.toshayo.waterframes.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.toshayo.waterframes.WaterFramesMod;

import java.io.IOException;

public class SyncPacket extends AbstractDisplayNetworkPacket {
    public NBTTagCompound nbt;

    public SyncPacket(int dimId, int x, int y, int z, NBTTagCompound nbt) {
        super(dimId, x, y, z);
        this.nbt = nbt;
    }

    public SyncPacket() {
        super();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        try {
            nbt = new PacketBuffer(buf).readNBTTagCompoundFromBuffer();
        } catch (IOException e) {
            WaterFramesMod.LOGGER.error("Failed to parse NBT of SyncPacket: {}", e.getMessage());
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        try {
            new PacketBuffer(buf).writeNBTTagCompoundToBuffer(nbt);
        } catch (IOException e) {
            WaterFramesMod.LOGGER.error("Failed to write NBT to SyncPacket: {}", e.getMessage());
        }
    }

    public static class Handler implements IMessageHandler<SyncPacket, IMessage> {
        @Override
        public IMessage onMessage(SyncPacket message, MessageContext ctx) {
            WaterFramesMod.proxy.handlePacket(message, ctx);
            return null;
        }
    }
}
