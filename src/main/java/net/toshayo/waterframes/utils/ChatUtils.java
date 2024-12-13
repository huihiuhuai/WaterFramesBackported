package net.toshayo.waterframes.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtils {
    public static void fatalMessage(EntityPlayer player, String key, Object ...args) {
        ChatComponentTranslation msg = new ChatComponentTranslation(key, args);
        msg.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
        player.addChatMessage(msg);
    }

    public static void errorMessage(EntityPlayer player, String key, Object ...args) {
        ChatComponentTranslation msg = new ChatComponentTranslation(key, args);
        msg.getChatStyle().setColor(EnumChatFormatting.RED);
        player.addChatMessage(msg);
    }

    public static void successMessage(EntityPlayer player, String key, Object ...args) {
        ChatComponentTranslation msg = new ChatComponentTranslation(key, args);
        msg.getChatStyle().setColor(EnumChatFormatting.AQUA);
        player.addChatMessage(msg);
    }
}
