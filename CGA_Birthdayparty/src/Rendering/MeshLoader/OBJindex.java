package Rendering.MeshLoader;

public class OBJindex {
    private int m_vertexIndex;
    private int m_textureIndex;
    private int m_normalIndex;

    public int getVertexIndex() { return m_vertexIndex; }

    public void setVertexIndex(int m_vertexIndex) {
        this.m_vertexIndex = m_vertexIndex;
    }

    public int getTextureIndex() {
        return m_textureIndex;
    }

    public void setTextureIndex(int m_textureIndex) {
        this.m_textureIndex = m_textureIndex;
    }

    public int getNormalIndex() {
        return m_normalIndex;
    }

    public void setNormalIndex(int m_normalIndex) {
        this.m_normalIndex = m_normalIndex;
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object obj) {

        OBJindex index = (OBJindex)obj;

        return m_vertexIndex == index.m_vertexIndex &&
                m_textureIndex == index.m_textureIndex &&
                m_normalIndex == index.m_normalIndex;
    }

    @Override
    public int hashCode(){

        final int BASE = 17;
        final int MULTIPLIER = 31;

        int result = BASE;

        result = result * MULTIPLIER + m_vertexIndex;
        result = result * MULTIPLIER + m_textureIndex;
        result = result * MULTIPLIER + m_normalIndex;

        return  result;
    }

}
