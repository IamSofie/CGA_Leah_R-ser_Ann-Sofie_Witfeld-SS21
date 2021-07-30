package Utils3D;

import Rendering.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferUtils3D {



    private void BufferUtils3D() {}


        /*public static void createIntBuffer(int[] indices, double[] bla ){

        }*/
        public static ByteBuffer createByteBuffer ( byte[] array){

            ByteBuffer result = ByteBuffer.allocateDirect(
                    array.length
            ).order(
                    ByteOrder.nativeOrder()
            );
            result.put(
                    array
            ).flip();

            return result;
        }



        public static FloatBuffer createVertexBuffer (Vertex[]vertices){
            FloatBuffer buffer = BufferUtils.createFloatBuffer(
                    vertices.length * Vertex.VERTEX_SIZE
            );

            for (int i = 0; i < vertices.length; i++) {
                buffer.put(
                        vertices[i].getVertex().getX()
                );

                buffer.put(
                        vertices[i].getVertex().getY()
                );
                buffer.put(
                        vertices[i].getVertex().getZ()
                );

                buffer.put(
                        vertices[i].getTextures().getX()
                );
            }
            buffer.flip();
            return buffer;
        }

        public static int[] ToIntArray(Integer[] data){
        int[] result = new int[data.length];

        for(int i = 0; i<data.length; i++){
            result[i] = data[i].intValue();
        }
        return result;
    }


}


