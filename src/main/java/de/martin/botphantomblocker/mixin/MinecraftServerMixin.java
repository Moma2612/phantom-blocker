package de.martin.botphantomblocker.mixin;

import de.martin.botphantomblocker.BotPhantomBlocker;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Inject(method = "tickServer", at = @At("TAIL"))
	private void botPhantomBlocker$onTick(BooleanSupplier hasTimeLeft, CallbackInfo ci) {
		BotPhantomBlocker.onServerTick((MinecraftServer) (Object) this);
	}
}
