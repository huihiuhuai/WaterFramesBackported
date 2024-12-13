package net.toshayo.waterframes.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.util.Facing;
import net.minecraft.util.Vec3;
import net.toshayo.waterframes.WFConfig;
import net.toshayo.waterframes.WaterFramesMod;
import net.toshayo.waterframes.tileentities.DisplayTileEntity;
import org.watermedia.api.image.ImageAPI;
import org.watermedia.api.image.ImageCache;
import org.watermedia.api.math.MathAPI;
import org.watermedia.api.player.videolan.VideoPlayer;
import org.watermedia.videolan4j.player.base.State;

import java.util.concurrent.atomic.AtomicLong;

public class TextureDisplay {
    public static final ImageCache VLC_NOT_FOUND = ImageAPI.cacheFailedVLC();

    // MEDIA AND DATA
    private VideoPlayer mediaPlayer;
    private ImageCache imageCache;
    private final DisplayTileEntity tile;
    private boolean notVideo;

    // CONFIG
    private int xCoord, yCoord, zCoord;
    private int currentVolume = 0;
    private final AtomicLong currentLastTime = new AtomicLong(Long.MIN_VALUE);
    private Mode displayMode = Mode.PICTURE;
    private boolean stream = false;
    private boolean synced = false;
    private boolean released = false;

    public TextureDisplay(DisplayTileEntity tile) {
        this.imageCache = tile.imageCache;
        this.xCoord = tile.xCoord;
        this.yCoord = tile.yCoord;
        this.zCoord = tile.zCoord;
        this.tile = tile;
        if (this.imageCache.isVideo()) {
            this.switchVideoMode();
        }
    }

    private void switchVideoMode() {
        // DO NOT USE VIDEOLAN IF I DONT WANT
        if (!WFConfig.useMultimedia()) {
            this.imageCache = VLC_NOT_FOUND;
            this.displayMode = Mode.PICTURE;
            return;
        }

        if(notVideo) {
            displayMode = Mode.PICTURE;
            return;
        }

        // START
        this.displayMode = Mode.VIDEO;
        this.mediaPlayer = new VideoPlayer(Minecraft.getMinecraft()::func_152344_a);

        // CHECK IF VLC CAN BE USED
        if (mediaPlayer.isBroken()) {
            this.imageCache = VLC_NOT_FOUND;
            this.displayMode = Mode.PICTURE;
            notVideo = true;
            return;
        }

        int x = (int) (xCoord + Facing.offsetsXForSide[this.tile.getBlockMetadata()] * tile.data.audioOffset);
        int y = (int) (yCoord + Facing.offsetsYForSide[this.tile.getBlockMetadata()] * tile.data.audioOffset);
        int z = (int) (zCoord + Facing.offsetsZForSide[this.tile.getBlockMetadata()] * tile.data.audioOffset);

        this.currentVolume = limitVolume(x, y, z, this.tile.data.volume, this.tile.data.minVolumeDistance, this.tile.data.maxVolumeDistance);

        // PLAYER CONFIG
        this.mediaPlayer.setVolume(this.currentVolume);
        this.mediaPlayer.setRepeatMode(this.tile.data.loop);
        this.mediaPlayer.setPauseMode(this.tile.data.paused);
        this.mediaPlayer.setMuteMode(this.tile.data.muted);
        this.mediaPlayer.start(this.tile.data.uri);
        DisplayControl.add(this);
    }

    public int width() {
        switch (displayMode) {
            case PICTURE:
                return this.imageCache.getRenderer() != null ? this.imageCache.getRenderer().width : 1;
            case VIDEO:
                return this.mediaPlayer.width();
            case AUDIO:
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int height() {
        switch (displayMode) {
            case PICTURE:
                return this.imageCache.getRenderer() != null ? this.imageCache.getRenderer().height : 1;
            case VIDEO:
                return this.mediaPlayer.height();
            case AUDIO:
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int texture() {
        switch (displayMode) {
            case PICTURE:
                return this.imageCache.getRenderer().texture(tile.data.tick, (!tile.data.paused ? MathAPI.tickToMs(WaterFramesMod.proxy.deltaFrames()) : 0), tile.data.loop);
            case VIDEO:
                return mediaPlayer.isBroken() ?
                        imageCache.getRenderer().texture(
                                tile.data.tick,
                                (!tile.data.paused ? MathAPI.tickToMs(WaterFramesMod.proxy.deltaFrames()) : 0),
                                tile.data.loop
                        ) : this.mediaPlayer.texture();
            case AUDIO:
                return -1;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void preRender() {
        switch (displayMode) {
            case PICTURE:
                break;
            case VIDEO:
                this.mediaPlayer.preRender();
                break;
        }
    }

    public int getTextureId() {
        return texture();
    }

    public int durationInTicks() {
        return MathAPI.msToTick(duration());
    }

    public long duration() {
        switch (displayMode) {
            case PICTURE:
                return this.imageCache.getRenderer() != null ? this.imageCache.getRenderer().duration : 0;
            case VIDEO:
                return this.mediaPlayer.getDuration();
            case AUDIO:
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean canTick() {
        switch (displayMode) {
            case PICTURE:
                return this.imageCache.getStatus().equals(ImageCache.Status.READY);
            case VIDEO:
                return this.mediaPlayer.isSafeUse() && this.mediaPlayer.isValid();
            case AUDIO: // MISSING IMPL
                return this.mediaPlayer.isSafeUse();
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean canRender() {
        switch (displayMode) {
            case PICTURE:
                return (imageCache.getStatus() == ImageCache.Status.READY && !this.imageCache.isVideo() && tile.data.active) || notVideo;
            case VIDEO:
                return this.mediaPlayer.isValid() && tile.data.active;
            case AUDIO:
                return false;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void syncDuration() {
        if (tile.data.tickMax == -1) {
            tile.data.tick = 0;
        }
        tile.syncTime(tile.data.tick, durationInTicks());
    }

    public void tick(int x, int y, int z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
        switch (displayMode) {
            case PICTURE:
                if (imageCache.isVideo() && !notVideo) {
                    switchVideoMode();
                }
                break;
            case VIDEO:
            case AUDIO:
                if(mediaPlayer.isBroken()) {
                    break;
                }
                x += (int) (Facing.offsetsXForSide[tile.getBlockMetadata()] * this.tile.data.audioOffset);
                y += (int) (Facing.offsetsYForSide[tile.getBlockMetadata()] * this.tile.data.audioOffset);
                z += (int) (Facing.offsetsZForSide[tile.getBlockMetadata()] * this.tile.data.audioOffset);
                int volume = limitVolume(x, y, z, this.tile.data.volume, this.tile.data.minVolumeDistance, this.tile.data.maxVolumeDistance);

                if (currentVolume != volume) {
                    mediaPlayer.setVolume(currentVolume = volume);
                }
                if (mediaPlayer.isSafeUse() && mediaPlayer.isValid()) {
                    if (mediaPlayer.getRepeatMode() != tile.data.loop) {
                        mediaPlayer.setRepeatMode(tile.data.loop);
                    }
                    if (mediaPlayer.isMuted() != tile.data.muted) {
                        mediaPlayer.setMuteMode(tile.data.muted);
                    }
                    if (!stream && mediaPlayer.isLive()) {
                        stream = true;
                    }

                    boolean mayPause = tile.data.paused || !tile.data.active || Minecraft.getMinecraft().isGamePaused();

                    if (mediaPlayer.isPaused() != mayPause) {
                        mediaPlayer.setPauseMode(mayPause);
                    }
                    if (!stream && mediaPlayer.isSeekAble()) {
                        long time = MathAPI.tickToMs(tile.data.tick) + (!mayPause ? MathAPI.tickToMs(WaterFramesMod.proxy.deltaFrames()) : 0);
                        if (time > mediaPlayer.getTime() && tile.data.loop) {
                            long mediaDuration = mediaPlayer.getMediaInfoDuration();
                            time = (time == 0 || mediaDuration == 0) ? 0 : Math.floorMod(time, mediaPlayer.getMediaInfoDuration());
                        }

                        if (Math.abs(time - mediaPlayer.getTime()) > DisplayControl.SYNC_TIME && Math.abs(time - currentLastTime.get()) > DisplayControl.SYNC_TIME) {
                            currentLastTime.set(time);
                            mediaPlayer.seekTo(time);
                        }
                    }
                }
                break;
        }

        if (!synced && canRender()) {
            syncDuration();
            synced = true;
        }
    }

    public boolean isBuffering() {
        switch (displayMode) {
            case PICTURE:
                return false;
            case VIDEO:
            case AUDIO:
                return mediaPlayer.isBuffering() || mediaPlayer.isLoading() || mediaPlayer.raw().mediaPlayer().status().state() == State.OPENING;
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean isLoading() {
        return imageCache.getStatus() == ImageCache.Status.LOADING;
    }

    public boolean isReleased() {
        return released;
    }

    public void setPauseMode(boolean pause) {
        switch (displayMode) {
            case PICTURE:
                break;
            case VIDEO:
            case AUDIO:
                mediaPlayer.seekTo(MathAPI.tickToMs(this.tile.data.tick));
                mediaPlayer.setPauseMode(pause);
                mediaPlayer.setMuteMode(this.tile.data.muted);
                break;
        }
    }

    public void setMuteMode(boolean mute) {
        switch (displayMode) {
            case PICTURE:
                break;
            case VIDEO:
            case AUDIO:
                mediaPlayer.setMuteMode(mute);
                break;
        }
    }

    public void release() {
        if (this.isReleased()) {
            return;
        }
        this.released = true;
        imageCache.deuse();
        switch (displayMode) {
            case PICTURE:
                if (imageCache != null) imageCache.deuse();
                break;
            case VIDEO:
            case AUDIO:
                mediaPlayer.release();
                DisplayControl.remove(this);
                break;
        }
    }

    public static int limitVolume(int x, int y, int z, int volume, int min, int max) {
        assert Minecraft.getMinecraft().thePlayer != null;
        Vec3 position = Minecraft.getMinecraft().thePlayer.getPosition(WaterFramesMod.proxy.deltaFrames());

        x -= (int) position.xCoord;
        y -= (int) position.yCoord;
        z -= (int) position.zCoord;
        double distance = Math.sqrt(x * x + y * y + z * z);
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }

        if (distance > min)
            volume = (distance > max + 1) ? 0 : (int) (volume * (1 - ((distance - min) / ((1 + max) - min))));
        return (int)(volume * Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RECORDS));
    }

    public enum Mode {
        VIDEO, PICTURE, AUDIO
    }
}
