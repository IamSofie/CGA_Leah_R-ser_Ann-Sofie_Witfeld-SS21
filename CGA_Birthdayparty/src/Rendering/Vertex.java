package Rendering;

import Maths3D.vec2;
import Maths3D.vec3;

public class Vertex {
    public static final int VERTEX_SIZE = 11; //for test

    private vec3 m_vertex;
    private vec2 m_textures;
    private vec3 m_normal;
    private vec3 m_tangents;

    public Vertex(vec3 vertex) {

        this(
                vertex,
                new vec2(
                        0.0f,
                        0.0f
                )
        );
    }

    public Vertex(vec3 vertex, vec2 textures){
        this(
                vertex,
                textures,
                new vec3(
                        0.0f,
                        0.0f,
                        0.0f
                )
        );
    }

    public Vertex(vec3 vertex, vec2 textures, vec3 normal) {
        this(
                vertex,
                textures,
                normal,
                new vec3(
                        0.0f,
                        0.0f,
                        0.0f
                )
        );
    }

    public Vertex(vec3 vertex, vec2 textures, vec3 normal, vec3 tangent){

        this.m_vertex = vertex;
        this.m_textures = textures;
        this.m_tangents = tangent;
        this.m_normal = normal;
    }

    public vec3 getVertex (){
        return m_vertex;
    }
    public vec2 getTextures(){
        return m_textures;
    }
    public vec3 getNormal(){
        return m_normal;
    }

    public vec3 getTangents(){
        return m_tangents;
    }
    public void setVertex(vec3 m_vertex){
        this.m_vertex = m_vertex;
    }
    public void setTextures(vec2 m_textures){
        this.m_textures = m_textures;
    }
    public void setNormal(vec3 m_normal){
        this.m_normal = m_normal;
    }
    public void setTangents(vec3 m_tangents){
        this.m_tangents = m_tangents;
    }
}
