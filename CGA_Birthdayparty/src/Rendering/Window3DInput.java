package Rendering;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class Window3DInput extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[1024];

    public void invoke(long window, int key, int scancode, int action, int mods){
        keys[key] = (action != GLFW_RELEASE);

        if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){

            glfwSetWindowShouldClose(
                    window,
                    true
            );
        }
    }
}
