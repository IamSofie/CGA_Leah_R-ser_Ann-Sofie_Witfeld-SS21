package Core;

import Rendering.Mesh;
import Rendering.RenderEngine;

public abstract class CoreBaseGame {

    private GameObject m_root;

    private Mesh mesh;  //test only

    public void  Init(){

    }


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
