package Rendering;

public class Mesh {

    private String m_fileName;

    public Mesh(String m_fileName){

        this.m_fileName = m_fileName;
    }

    public Mesh(Vertex[] vertices, int[] indices){

        //this(vertices, indices, false);
    }
}
