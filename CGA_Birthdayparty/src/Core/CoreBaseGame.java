package Core;

import Rendering.Mesh;
import Rendering.RenderEngine;
import org.lwjgl.glfw.GLFWErrorCallback;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;

public abstract class CoreBaseGame {

    private GameObject m_root;

    private Mesh mesh;

    public void  Init(){}


    public void Input(float deltaTime){
    }

    public void Update(float deltaTime){
    }

    public void Render(RenderEngine renderEngine){

    }

    public void Render(){ //test only
        mesh.Draw();
    }

    public void AddObject(GameObject object){

    }

    public void AddObject(Mesh mesh){  //test only
        this.mesh = mesh;
    }

    public void SetEngine(CoreEngine engine){

    }

    /////////Private/////////////
    private GameObject GetRootObject(){
        if(m_root == null) {
            m_root = new GameObject();

        }
        return m_root;
    }

}
