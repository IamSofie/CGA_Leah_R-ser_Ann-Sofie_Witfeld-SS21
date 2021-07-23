package Rendering.MeshLoader;

import Maths3D.vec2;
import Maths3D.vec3;

import java.util.ArrayList;

public class IndexModel {

    private ArrayList<vec3> m_vertices;
    private ArrayList<vec2> m_textures;
    private ArrayList<vec3> m_normals;
    private ArrayList<vec3> m_tangents;
    private ArrayList<Integer> m_indices;

    public IndexModel(){

        m_vertices = new ArrayList<>();
        m_textures = new ArrayList<>();
        m_normals = new ArrayList<>();
        m_tangents = new ArrayList<>();
        m_indices = new ArrayList<>();


    }

    public void calculateTangents(){

        for (int i = 0; i < m_indices.size(); i += 3){

            int index0 = m_indices.get(
                    i
            );

            int  index1 = m_indices.get(
                    i + 1
            );

            int index2 = m_indices.get(
                    i + 2
            );

            vec3 edge1 = m_vertices.get(
                    index1
            ).sub(
                    m_vertices.get(index0)
            );

            vec3 edge2 = m_vertices.get(
                    index2
            ).sub(
                    m_vertices.get(index0)
            );

            float deltaU1 = m_textures.get(
                    index1
            ).getX() - m_textures.get(

                    index0

            ).getX();

            float deltaV1 = m_textures.get(
                    index1
            ).getY() - m_textures.get(

                    index0

            ).getY();

            float deltaU2 = m_textures.get(
                    index2
            ).getX() - m_textures.get(

                    index0

            ).getX();

            float deltaV2 = m_textures.get(
                    index2
            ).getY() - m_textures.get(

                    index0

            ).getY();

            float divisor = (
                    deltaU1 * deltaV2 - deltaU2 * deltaV1
            );

            float factor = divisor == 0 ? 0.0f : 1.0f / divisor;

            vec3 tangent = new vec3(
                    0.0f,
                    0.0f,
                    0.0f
            );

            tangent.setX(
                    factor * (
                             deltaV2 * edge1.getX() - deltaV1 * edge2.getX()
                             )
                    );

            tangent.setY(
                    factor * (
                            deltaV2 * edge1.getY() - deltaV1 * edge2.getY()
                    )
            );

            tangent.setZ(
                    factor * (
                            deltaV2 * edge1.getZ() - deltaV1 * edge2.getZ()
                    )
            );

            m_tangents.get(
                    index0
            ).set(
                    m_tangents.get(
                            index0
                    ).add(
                            tangent
                    )
            );

            m_tangents.get(
                    index1
            ).set(
                    m_tangents.get(
                            index1
                    ).add(
                            tangent
                    )
            );

            m_tangents.get(
                    index2
            ).set(
                    m_tangents.get(
                            index2
                    ).add(
                            tangent
                    )
            );

        }//calculate Tangents main for loop

        for (vec3 m_tangent : m_tangents) {

            m_tangent.set(
                    m_tangent.normalized()
            );
        }

    }

    public ArrayList<vec3> getVertices() {
        return m_vertices;
    }

    public ArrayList<vec2> getTextures() {
        return m_textures;
    }

    public ArrayList<vec3> getNormals() {
        return m_normals;
    }

    public ArrayList<vec3> getTangents() {
        return m_tangents;
    }

    public ArrayList<Integer> getIndices() {
        return m_indices;
    }

    public void setVertices(ArrayList<vec3> m_vertices) {
        this.m_vertices = m_vertices;
    }

    public void setTextures(ArrayList<vec2> m_textures) {
        this.m_textures = m_textures;
    }

    public void setNormals(ArrayList<vec3> m_normals) {
        this.m_normals = m_normals;
    }

    public void setTangents(ArrayList<vec3> m_tangents) {
        this.m_tangents = m_tangents;
    }

    public void setIndices(ArrayList<Integer> m_indices) {
        this.m_indices = m_indices;
    }
}

