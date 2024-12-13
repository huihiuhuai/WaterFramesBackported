package net.toshayo.waterframes.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.toshayo.waterframes.WaterFramesMod;
import org.lwjgl.opengl.GL11;

public class RemoteControlItemRenderer implements IItemRenderer {
    private static final ResourceLocation REMOTE_TEXTURE = new ResourceLocation(WaterFramesMod.MOD_ID, "textures/items/remote.png");
    private static final IModelCustom REMOTE_MODEL = AdvancedModelLoader.loadModel(new ResourceLocation(WaterFramesMod.MOD_ID, "models/remote.obj"));


    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(REMOTE_TEXTURE);

        GL11.glDisable(GL11.GL_CULL_FACE);

        switch (type) {
            case ENTITY:
                GL11.glTranslatef(0, 0.5F, 0);
                break;
            case EQUIPPED:
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                GL11.glRotatef(220, 0, 1, 0);
                GL11.glRotatef(90, 1, 0, 0);
                break;
            case EQUIPPED_FIRST_PERSON:
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
                GL11.glRotatef(130, 0, 1, 0);
                GL11.glRotatef(70, 1, 0, 0);
                break;
            case INVENTORY:
                GL11.glRotatef(180, 0, 1, 0);
                GL11.glTranslatef(0F, -0.2F, 0F);
                GL11.glScalef(0.8F, 0.8F, 0.8F);
                break;
            default:
                break;
        }

        REMOTE_MODEL.renderAll();
        GL11.glPopMatrix();
    }
}
