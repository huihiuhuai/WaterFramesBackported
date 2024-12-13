package net.toshayo.waterframes.network.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.toshayo.waterframes.WaterFramesMod;

public class LoopPacket extends AbstractDisplayNetworkPacket {

    public boolean loop;

    public LoopPacket(int dimId, int x, int y, int z, boolean loop) {
        super(dimId, x, y, z);
        this.loop = loop;
    }

    public LoopPacket() {
        super();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        super.fromBytes(buf);
        loop = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        super.toBytes(buf);
        buf.writeBoolean(loop);
    }

    public static class Handler implements IMessageHandler<LoopPacket, IMessage> {
        @Override
        public IMessage onMessage(LoopPacket message, MessageContext ctx) {
            WaterFramesMod.proxy.handlePacket(message, ctx);
            return null;
        }
    }
}
