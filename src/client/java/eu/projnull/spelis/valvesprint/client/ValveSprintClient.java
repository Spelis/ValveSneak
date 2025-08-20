package eu.projnull.spelis.valvesprint.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ValveSprintClient implements ClientModInitializer {
    private KeyBinding walkBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.valvesprint.walk",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            "key.categories.movement"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null) {
                if (client.player.input.hasForwardMovement()) {
                    client.player.setSprinting(!walkBind.isPressed() && !client.player.isSneaking());
                }
            }
        });
    }
}
