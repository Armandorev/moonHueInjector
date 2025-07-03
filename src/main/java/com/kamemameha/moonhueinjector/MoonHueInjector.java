package com.kamemameha.moonhueinjector;

import org.joml.Vector3f;
import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.context.EnhancedCelestialsContext;
import corgitaco.enhancedcelestials.lunar.LunarForecast;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraft.client.Minecraft;

@Mod("moonhueinjector")
public class MoonHueInjector {

    public MoonHueInjector() {
        // Register client setup event
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        // Initialize the dynamic uniform texture
        MoonHueUniforms.init();
    }

    @Mod.EventBusSubscriber(modid = "moonhueinjector", value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        public static void onRenderLevelStage(RenderLevelStageEvent event) {
            if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SKY) return;

            Minecraft mc = Minecraft.getInstance();
            if (!(mc.level instanceof EnhancedCelestialsWorldData data)) return;

            EnhancedCelestialsContext context = data.getLunarContext();
            if (context == null) return;

            LunarForecast forecast = context.getLunarForecast();
            Vector3f tint = forecast.getBlend(); // RGB tint from EC

            // You can use 1.0f for full strength, or replace with your dynamic value
            MoonHueUniforms.updateUniform(tint.x(), tint.y(), tint.z(), 1.0f);
        }
    }
}