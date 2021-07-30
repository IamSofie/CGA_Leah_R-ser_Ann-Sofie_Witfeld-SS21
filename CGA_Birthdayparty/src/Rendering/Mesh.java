package Rendering;


import Rendering.MeshLoader.IndexModel;
import Rendering.MeshLoader.OBJModel;
import Utils3D.BufferUtils3D;

import java.util.ArrayList;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;


public class Mesh {

    private final String m_fileName;
    private int m_vbo;
    private int m_ibo;
    private int dataSize; //test only
    private int vao; //test


    public Mesh(String fileName) {


        this.m_fileName = fileName;
        LoadMesh(fileName);
    }

    public Mesh(Vertex[] vertices, int[] indices) {

        this(vertices, indices, false);
    }

    public Mesh(Vertex[] vertices, int[] indices, boolean calcNormals) {
        this.m_fileName = "";

        LoadToGFX(
                vertices,
                indices,
                calcNormals
        );
    }

    public void Draw() {

        //Testdraw

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glBindVertexArray(vao);

        glDrawElements(
                GL_TRIANGLES,
                dataSize,
                GL_UNSIGNED_INT,
                0
        );
        glBindVertexArray((vao));
    }

    ///////////////////Private////////////////////

    private void LoadMesh(String fileName) {
        OBJModel objModel = new OBJModel(
                "res/OBJ/" + fileName + ".obj"

        );

        IndexModel model = objModel.toIndexModel();

        ArrayList<Vertex> vertices = new ArrayList<>();

        for(int i = 0 ; i < model.getVertices().size(); i++){
            vertices.add(
                    new Vertex(
                             model.getVertices().get(i),
                             model.getTextures().get(i),
                             model.getNormals().get(i),
                             model.getTangents().get(i)
                    )
            );
        }

        Vertex[] vertexData = new Vertex[vertices.size()];

        vertices.toArray(
                vertexData
        );

        Integer[] indexData = new Integer[model.getIndices().size()];

        model.getIndices().toArray(
                indexData
        );

        LoadToGFX(
                vertexData,
                BufferUtils3D.ToIntArray(indexData),
                false
        );

    }

    private void CalcNormals() {


    }

    private void LoadToGFX(Vertex[] vertices, int[] indices, boolean calcNormals) {

        if (calcNormals) {
            //ToDo : clacNormals(vertices, indices)
        }
        //ToDo : create new ressource

        this.dataSize = vertices.length;

        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        this.m_vbo = glGenBuffers();

        glBindBuffer(
                GL_ARRAY_BUFFER,
                m_vbo
        );

        glBufferData(
                GL_ARRAY_BUFFER,
                BufferUtils3D.createVertexBuffer(
                vertices
        ),
                GL_STATIC_DRAW
        );

        glVertexAttribPointer(
                0,
                3,
                GL_FLOAT,
                false,
                0,
                0
        );



        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        this.m_vbo = glGenBuffers();

        glBindBuffer(
                GL_ARRAY_BUFFER,
                m_vbo
        );

        glBufferData(
                GL_ARRAY_BUFFER,
                BufferUtils3D.createVertexBuffer(
                        vertices
                ),
                GL_STATIC_DRAW

        );
        glVertexAttribPointer(
                0,
                3,
                GL_FLOAT,
                false,
                0,
                0
        );

        glVertexAttribPointer(
                1,
                3,
                GL_FLOAT,
                false,
                Vertex.VERTEX_SIZE * 4,
                12
        );

        glVertexAttribPointer(
                2,
                3,
                GL_FLOAT,
                false,
                Vertex.VERTEX_SIZE * 4 ,
                20
        );

        glVertexAttribPointer(
                3,
                3,
                GL_FLOAT,
                false,
                Vertex.VERTEX_SIZE * 4,
                32
        );

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);


        m_ibo = glGenBuffers();

        glBindBuffer(
                GL_ELEMENT_ARRAY_BUFFER,
                m_ibo
        );
    }



}
