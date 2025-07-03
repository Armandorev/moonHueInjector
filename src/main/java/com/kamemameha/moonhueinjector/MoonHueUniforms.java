package com.kamemameha.moonhueinjector;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;

public class MoonHueUniforms {
    private static DynamicTexture uniformTex;
    private static ResourceLocation texLoc;

    /**
     * Call this during mod initialization (client setup) to prepare the dynamic uniform texture.
     */
    public static void init() {
        uniformTex = new DynamicTexture(1, 1, false);
        texLoc = Minecraft.getInstance().getTextureManager().register("moonhueinjector_uniform", uniformTex);
    }

    /**
     * Updates the 1x1 texture with the provided RGB and strength values.
     * Each parameter should be in the range [0, 1].
     * @param r Red channel (0-1)
     * @param g Green channel (0-1)
     * @param b Blue channel (0-1)
     * @param strength Alpha channel (0-1)
     */
    public static void updateUniform(float r, float g, float b, float strength) {
        int ir = ((int)(r * 255)) & 0xFF;
        int ig = ((int)(g * 255)) & 0xFF;
        int ib = ((int)(b * 255)) & 0xFF;
        int ia = ((int)(strength * 255)) & 0xFF;
        int pixel = (ir << 24) | (ig << 16) | (ib << 8) | ia;
        uniformTex.getPixels().setPixelRGBA(0, 0, pixel);
        uniformTex.upload();
    }

    /**
     * Returns the ResourceLocation of the dynamic uniform texture.
     * Bind this before rendering with: RenderSystem.setShaderTexture(unit, MoonHueUniforms.getTextureLocation());
     */
    public static ResourceLocation getTextureLocation() {
        return texLoc;
    }
}