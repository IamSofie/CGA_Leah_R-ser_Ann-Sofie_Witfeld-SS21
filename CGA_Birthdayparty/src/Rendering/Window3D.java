package Rendering.MeshLoader;

import Rendering.Window3DInput;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;


import java.util.Objects;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;



public class Window3D {

    private static long window;

    public static void createDisplay(int width, int height, String title) {
        //error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException(
                    "Unable inizialize GLFW"
            );

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(
                GLFW_CONTEXT_VERSION_MAJOR,
                4
        );
        glfwWindowHint(
                GLFW_CONTEXT_VERSION_MINOR,
                6
        );
        glfwWindowHint(
                GLFW_OPENGL_PROFILE,
                GLFW_OPENGL_CORE_PROFILE
        );

        glfwWindowHint(
                GLFW_VISIBLE,
                GLFW_FALSE
        );
        glfwWindowHint(
                GLFW_RESIZABLE,
                GLFW_TRUE
        );

        window = glfwCreateWindow(
                width,
                height,
                title,
                NULL,
                NULL
        );

        if (window == NULL)
            throw new IllegalStateException(
                    "Failed to create the GLFW window"
            );

            glfwSetKeyCallback(window, new Window3DInput());

           GLFWVidMode vidMode =
                    glfwGetVideoMode(
                            glfwGetPrimaryMonitor()
                    );
            assert vidMode != null;

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - width) / 2,
                    (vidMode.height() - height) / 2
            );
            //Make the OpenGL the Context current
            glfwMakeContextCurrent(
                    window
            );
            //LWJGL's Interoperation. All context that is managed externally.
            GL.createCapabilities();

            glEnable(
                    GL_DEPTH_TEST
            );

            glEnable(
                    GL_CULL_FACE
            );

            glEnable(
                    GL_BLEND
            );

            glBlendFunc(
                    GL_SRC_ALPHA,
                    GL_ONE_MINUS_SRC_ALPHA
            );

            glClearColor(
                    0.4f,
                    0.5f,
                    0.9f,
                    1.0f
            );

            System.out.println(
                    glGetString(
                            GL_VERSION
                    )
            );
            //Enable v-sync
            glfwSwapInterval(
                    1
            );
            //Make the window visible
            glfwShowWindow(
                    window
            );
        }

        public static boolean DisplayIsOpen () {
            return !glfwWindowShouldClose(
                    window
            );
        }
        public static void updateDisplay () {
            glfwSwapBuffers(window);

            glfwPollEvents();
        }

         public static void CleanUp(){
         closeDisplay();
        }

        public static void closeDisplay (){
            glfwDestroyWindow(
                    window
            );

            glfwTerminate();

            Objects.requireNonNull(
                    glfwSetErrorCallback(
                            null
                    )
            ).free();
        }
   /* public void loop(){
        while (!glfwWindowShouldClose(window)){
            //Poll events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(window);
        }
    }*/



}
