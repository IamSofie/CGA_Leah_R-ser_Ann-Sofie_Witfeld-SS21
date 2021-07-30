package Rendering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL46.*;


public class Shader {

    //Shader ressources.
    private String m_fileName;
    private int m_shaderProgram;

    public Shader(String fileName){

        this.m_fileName = fileName;
        this.m_shaderProgram = glCreateProgram();

        String vertexShaderText = LoadShader(
                fileName + ".vs"
        );

        String fragmentShaderText = LoadShader(
                fileName + ".fs"
        );

        AddVertexShader(
                vertexShaderText
        );

        /*AddFragmentShader(
                fragmentShaderText
        );*/


        //AddAllAttributes(vertexShaderText);

        //CompileShader();

        //AddAllUniforms(vertexShaderText);
        //AddAllUniforms(fragmentShaderText);

        //TODO: add Shader to ressource system

    }

    public void Bind(){
        glUseProgram(m_shaderProgram);
    }

    public void UpdateUniforms(){
     //TODO : update uniforms
    }

    private void AddVertexShader(String text) {
        AddProgram(
                text,
                GL_VERTEX_SHADER
        );
    }

        private void AddGeometryShader(String text) {
            AddProgram(
                    text,
                    GL_GEOMETRY_SHADER
            );
        }

            private void AddProgram(String text, int type){
                int shader = glCreateShader(type);

                if(shader == 0){
                    System.err.println(
                            "[Error] unable to create shader @ AddProgram stage"
                    );

                    System.exit(99);
                }

                glShaderSource(
                        shader,
                        text
                );

                glCompileShader(
                        shader
                );

                if(glGetShaderi(shader, GL_COMPILE_STATUS)== 0){

                    System.err.println(
                            glGetShaderInfoLog(
                                    shader,
                                    1024
                            )
                    );
                    System.exit(1);
                }

                glAttachShader(
                        m_shaderProgram,
                        shader
                );
            }

            private static String LoadShader(String fileName){

                final String INCLUDE_DIRECTIVE = "#include";

                StringBuilder shaderSource = new StringBuilder();
                BufferedReader shaderReader;

                try{

                    shaderReader = new BufferedReader(
                            new FileReader(
                                    "res/GLSL/" + fileName
                            )
                    );

                    String line;

                    while((line = shaderReader.readLine()) != null){
                         if(line.startsWith(INCLUDE_DIRECTIVE)){

                             shaderSource.append(
                                     LoadShader(
                                             line.substring(
                                                     INCLUDE_DIRECTIVE.length() +2,
                                                     line.length() -1
                                             )
                                     )
                             );
                         }else{

                             shaderSource.append(
                                     line
                             ).append(
                                     "\n"
                             );
                         }
                    }

                    shaderReader.close();
                } catch (IOException e){
                    e.printStackTrace();

                    System.out.println(
                            "unable to load shader:"+ fileName
                    );

                    System.exit(99);
                }

                return shaderSource.toString();
            }
        }
