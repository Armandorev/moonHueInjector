package com.kamemameha.moonhueinjector;

import net.coderbot.iris.uniforms.ShaderCallback;
import net.coderbot.iris.uniforms.ShaderCallbackRegistry;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public class ShaderHelper {

    private static final Map<String, Vector3f> vec3Uniforms = new HashMap<>();
    private static final Map<String, Float> floatUniforms = new HashMap<>();

    public static void init() {
        // Replace "deferred_final.fsh" with the actual shader file name you're using
        ShaderCallbackRegistry.registerCallback("deferred_final.fsh", (shaderProgram) -> {
            vec3Uniforms.forEach((name, value) -> {
                int loc = shaderProgram.getUniformLocation(name);
                if (loc >= 0) shaderProgram.setUniform(loc, value.x, value.y, value.z);
            });

            floatUniforms.forEach((name, value) -> {
                int loc = shaderProgram.getUniformLocation(name);
                if (loc >= 0) shaderProgram.setUniform(loc, value);
            });
        });
    }

    public static void setUniform(String name, float x, float y, float z) {
        vec3Uniforms.put(name, new Vector3f(x, y, z));
    }

    public static void setUniform(String name, float value) {
        floatUniforms.put(name, value);
    }
}