package io.github.whoisjeb.bettergrass.mixin;

import io.github.whoisjeb.bettergrass.config.Config;
import io.github.whoisjeb.bettergrass.config.Quality;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static io.github.whoisjeb.bettergrass.config.Quality.*;

@Environment(EnvType.CLIENT)
@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {
    @Shadow private BlockView blockView;
    @Unique private Quality quality = Config.betterGrassConfig.quality;

    @Unique private int resolveTexture(double x, double y, double z, int texture) {
        if (quality == OFF) return texture;

        if (texture == 38) return byGrass(x, y, z) || quality == FAST ? 0 : texture;
        if (texture == 68) return bySnow(x, y, z) || quality == FAST ? 66 : texture;

        return texture;
    }

    @Unique private boolean byGrass(double x, double y, double z) {
        return blockView.getBlockId((int) x, (int) y - 1, (int) z) == 2;
    }

    @Unique private boolean bySnow(double x, double y, double z) {
        return blockView.getBlockId((int) x, (int) y, (int) z) == 78 || blockView.getBlockId((int) x, (int) y - 1, (int) z) == 80;
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderEastFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setEastTextureSmoothLight(Args args) {
        args.set(4, resolveTexture(args.get(1), args.get(2), (double) args.get(3) - 1.0D, args.get(4)));
    }

    @ModifyArgs(
            method="renderFlat",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderEastFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setEastTextureNormalLight(Args args) {
        args.set(4, resolveTexture(args.get(1), args.get(2), (double) args.get(3) - 1.0D, args.get(4)));
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderWestFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setWestTextureSmoothLight(Args args) {
        args.set(4, resolveTexture(args.get(1), args.get(2), (double) args.get(3) + 1.0D, args.get(4)));
    }

    @ModifyArgs(
            method="renderFlat",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderWestFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setWestTextureNormalLight(Args args) {
        args.set(4, resolveTexture(args.get(1), args.get(2), (double) args.get(3) + 1.0D, args.get(4)));
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderNorthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setNorthTextureSmoothLight(Args args) {
        args.set(4, resolveTexture((double) args.get(1) - 1.0D, args.get(2), args.get(3), args.get(4)));
    }

    @ModifyArgs(
            method="renderFlat",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderNorthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setNorthTextureNormalLight(Args args) {
        args.set(4, resolveTexture((double) args.get(1) - 1.0D, args.get(2), args.get(3), args.get(4)));
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderSouthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setSouthTextureSmoothLight(Args args) {
        args.set(4, resolveTexture((double) args.get(1) + 1.0D, args.get(2), args.get(3), args.get(4)));
    }

    @ModifyArgs(
            method="renderFlat",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderSouthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setSouthTextureNormalLight(Args args) {
        args.set(4, resolveTexture((double) args.get(1) + 1.0D, args.get(2), args.get(3), args.get(4)));
    }
}
