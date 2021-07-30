import Core.CoreBaseGame;
import Maths3D.vec2;
import Maths3D.vec3;
import Rendering.Mesh;
import Rendering.Vertex;

public class TestGame extends CoreBaseGame {

    public  void Init(){

        Vertex[] vertices = {
                new Vertex(new vec3(-0.5f, 0.5f, 0.0f), new vec2(0.0f, 0.0f)),
                new Vertex(new vec3(-0.5f, -0.5f, 0.0f), new vec2(0.0f, 0.0f)),
                new Vertex(new vec3(0.5f, -0.5f, 0.0f), new vec2(0.0f, 0.0f))
                //57:01
        };

        int[] indices = {0, 1, 2};


        //mesh
        Mesh testMesh = new Mesh(
                vertices,
                indices
        );

        //material

        //meshRendering

        //create a game object with components and a transform

        //add object to the base game
        AddObject(
                testMesh
        );
    }

}
