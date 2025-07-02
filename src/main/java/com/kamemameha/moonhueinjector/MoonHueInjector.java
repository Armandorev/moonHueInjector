package com.kamemameha.moonhueinjector;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.joml.Vector3f;
import net.minecraft.client.Minecraft;

import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.EnhancedCelestialsContext;
import corgitaco.enhancedcelestials.lunarevent.LunarForecast;

@Mod("moonhueinjector")
public class MoonHueInjector {

public MoonHueInjector() {
    MinecraftForge.EVENT_BUS.register(this);
    ShaderHelper.init(); // ✅ Initialize the shader callback
}

    @SubscribeEvent
    public void onRender(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;

        Minecraft mc = Minecraft.getInstance();
        if (!(mc.level instanceof EnhancedCelestialsWorldData data)) return;

        EnhancedCelestialsContext context = data.getLunarContext();
        if (context == null) return;

        LunarForecast forecast = context.getLunarForecast();
        Vector3f tint = forecast.getBlend(); // RGB tint from EC

        ShaderHelper.setUniform("moonHueOverlay", tint.x(), tint.y(), tint.z());
    }
}
