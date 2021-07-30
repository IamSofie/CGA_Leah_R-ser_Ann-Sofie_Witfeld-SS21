package Rendering.MeshLoader;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window3D {

    private int width;
    private int height;
    private String title;
    private long glfwWindow;


    private static Window3D window = null;

    public Window3D() {
        
    }

    public static Window3D get(){
        if(Window3D.window == null){
            Window3D.window = new Window3D();
        }
        return Window3D.window;
    }

    public void  run(){
        System.out.println("Hello" + Version.getVersion());

        createDisplay();
        loop();
    }
    public void createDisplay(int width, int height, String title){
        //error Callback
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new IllegalStateException("Unable inizialize GLFW");
        }
        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(
                GLFW_CONTENT_VERSION_MINOR,
                6
        );
        glfwWindowHint(
                GLFW_RESIZABLE,
                GLFW_TRUE
        );
        glfwWindowHint(
                GLFW_MAXIMIZED,
                GLFW_TRUE
        );

        glfwWindow = glfwCreateWindow(
                this.width,
                this.height,
                this.title,
                NULL,
                NULL
        );

        if(glfwWindow == NULL){
            throw new IllegalStateException(
                    "Failed to create the GLFW window"
            );

            glfwSetKeyCallback(window, new
                     Window3DInput()
            );
            GLFWVidMode vidMode =
                    glfwGetVideoMode(
                            glfwGetPrimaryMonitor()
                    );
            assert vidMode != null;

            glfwSetWindowPos(
                    window,
                    (vidMode.width() - width) / 2
            )

        }

        //Make the OpenGL the Context current
        glfwMakeContextCurrent(glfwWindow);
        //Enable v-sync
        glfwSwapInterval(1);

        //Make the window visible

        glfwShowWindow(glfwWindow);

        //LWJGL's Interoperation. All context that is managed externally.
        GL.createCapabilities();
    }

    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow)){
            //Poll events
            glfwPollEvents();

            glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }
    }


    public static boolean DisplayIsOpen(){

        boolean b = !glfwWindowShouldClose(
                window
        );
        return b;
    }

    


    public static void updateDisplay(){

    }

}
