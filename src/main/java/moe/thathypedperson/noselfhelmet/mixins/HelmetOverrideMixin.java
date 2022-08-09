package moe.thathypedperson.noselfhelmet.mixins;

import moe.thathypedperson.noselfhelmet.NoSelfHelmet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayerArmorBase.class)
public class HelmetOverrideMixin
{
	@Inject(method = "renderLayer", at = @At(value = "HEAD"), cancellable = true)
	private void disableHelmet(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float scale, int armorSlot, CallbackInfo ci)
	{
		// maybe add hypixel locraw to disable for certain games
		if(NoSelfHelmet.helmetDisabled
				&& entity instanceof EntityPlayerSP
				&& entity == Minecraft.getMinecraft().thePlayer
				&& armorSlot == 4)
		{
			ci.cancel();
		}
	}
}
