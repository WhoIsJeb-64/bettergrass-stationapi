package io.github.whoisjeb.bettergrass.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {
    @Shadow private BlockView blockView;

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderEastFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setEastTexture(Args args) {
        double x = args.get(1);
        double y = args.get(2);
        double z = args.get(3);

        int texture = args.get(4);
        if (texture == 38) {
            args.set(4, (blockView.getBlockId((int) x, (int) y - 1, (int) z - 1) == 2) ? 0 : texture);
        } else if (texture == 68) {
            args.set(4, (blockView.getBlockId((int) x, (int) y, (int) z - 1) == 78) ? 66 : texture);
        }
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderWestFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setWestTexture(Args args) {
        double x = args.get(1);
        double y = args.get(2);
        double z = args.get(3);

        int texture = args.get(4);
        if (texture == 38) {
            args.set(4, (blockView.getBlockId((int) x, (int) y - 1, (int) z + 1) == 2) ? 0 : texture);
        } else if (texture == 68) {
            args.set(4, (blockView.getBlockId((int) x, (int) y, (int) z + 1) == 78) ? 66 : texture);
        }
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderNorthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setNorthTexture(Args args) {
        double x = args.get(1);
        double y = args.get(2);
        double z = args.get(3);

        int texture = args.get(4);
        if (texture == 38) {
            args.set(4, (blockView.getBlockId((int) x - 1, (int) y - 1, (int) z) == 2) ? 0 : texture);
        } else if (texture == 68) {
            args.set(4, (blockView.getBlockId((int) x - 1, (int) y, (int) z) == 78) ? 66 : texture);
        }
    }

    @ModifyArgs(
            method="renderSmooth",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderSouthFace(Lnet/minecraft/block/Block;DDDI)V"))
    private void setSouthTexture(Args args) {
        double x = args.get(1);
        double y = args.get(2);
        double z = args.get(3);

        int texture = args.get(4);
        if (texture == 38) {
            args.set(4, (blockView.getBlockId((int) x + 1, (int) y - 1, (int) z) == 2) ? 0 : texture);
        } else if (texture == 68) {
            args.set(4, (blockView.getBlockId((int) x + 1, (int) y, (int) z) == 78) ? 66 : texture);
        }
    }
}
