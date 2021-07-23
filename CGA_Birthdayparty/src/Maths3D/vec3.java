package Maths3D;

public class vec3 {
    private float m_x;
    private float m_y;
    private float m_z;

    public vec3(float m_x, float m_y, float m_z) {
        this.m_x = m_x;
        this.m_y = m_y;
        this.m_z = m_z;
    }

    public float length(){
        return (float)Math.sqrt(
                m_x * m_x +
                m_y * m_y +
                m_z * m_z
        );
    }

    public vec3 normalized(){

        float length = length();

        return new vec3(

                m_x / length,
                m_y / length,
                m_z / length
        );
    }

    public vec3 add(vec3 right){

        return new vec3(
                m_x + right.getX(),
                m_y + right.getY(),
                m_z + right.getZ()
        );
    }


    public vec3 sub(vec3 right) {
        return new vec3(
          m_x - right.getX(),
          m_y - right.getY(),
          m_z - right.getZ()
        );

    }

    public vec3 set(float x, float y, float z){
        this.m_x = x;
        this.m_y = y;
        this.m_z = z;

        return this;
    }

    public vec3 set(vec3 right){
        this.set(
                right.getX(),
                right.getY(),
                right.getZ()
                );
        return this;
    }



    public float getX() {
        return m_x;
    }

    public void setX(float m_x) {
        this.m_x = m_x;
    }

    public float getY() {
        return m_y;
    }

    public void setY(float m_y) {
        this.m_y = m_y;
    }

    public float getZ() {
        return m_z;
    }

    public void setZ(float m_z) {
        this.m_z = m_z;
    }
}
