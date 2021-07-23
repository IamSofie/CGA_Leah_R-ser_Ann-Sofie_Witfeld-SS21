package Rendering.MeshLoader;

import Maths3D.vec2;
import Maths3D.vec3;

import java.io.IOException;
import java.lang.String;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class OBJModel {

    private ArrayList<vec3> m_vertices;
    private ArrayList<vec2> m_textures;
    private ArrayList<vec3> m_normals;
    private ArrayList<OBJindex> m_indices;
    private boolean m_hasTextures;
    private boolean m_hasNormals;

    public OBJModel(String fileName){
        m_vertices = new ArrayList<>();         //initialize variables
        m_textures = new ArrayList<>();
        m_normals = new ArrayList<>();
        m_indices = new ArrayList<>();
        m_hasTextures = false;
        m_hasNormals = false;

        BufferedReader fileIn;           //initialize file reader
        String file = "res/OBJ/" + fileName + ".obj";  //file + path
        try {
            fileIn = new BufferedReader(
                    new FileReader(
                            file            //open the file to read
                    )

            );
            String line;                    //variable to save every read line


            ////////////////Main Loop///////////////////////
            while((line = readline(fileIn)) != null){
                String[] currentLine = line.split(
                        " "             //split by Space
                );

                currentLine = removeEmptyStrings(currentLine);

                if(currentLine.length == 0 || currentLine[0].equals("#")){     //blanklines or remarks
                    continue;                     //sends us back to top of the while loop

                }
                switch (currentLine[0]) {
                    case "v" ->                //check for vertices
                        m_vertices.add(
                                new vec3(
                                        Float.parseFloat(currentLine[1]),           //x posotion
                                        Float.parseFloat(currentLine[2]),           //y posotion
                                        Float.parseFloat(currentLine[3])            //z posotion
                                )
                        );

                    case "vt" ->               //check for UV texture coordinates

                            m_textures.add(
                                    new vec2(
                                            Float.parseFloat(currentLine[1]),        //U posotion
                                            Float.parseFloat(currentLine[2])        //V posotion

                                    )
                            );

                    case "vn" ->                      //check for normals

                            m_normals.add(
                                    new vec3(
                                            Float.parseFloat(currentLine[1]),        //x posotion
                                            Float.parseFloat(currentLine[2]),        //y posotion
                                            Float.parseFloat(currentLine[3])         //z posotion
                                    )
                            );

                    case "f" -> {
                        if (currentLine.length > 4) {
                            System.out.println("[Error OBJ file is need to be in triangle format not Quads.");
                            System.exit(99);        //we cant continue with no data.
                        }
                        m_indices.add(

                                readOBJIndex(

                                        currentLine[1]

                                )

                        );
                        m_indices.add(

                                readOBJIndex(

                                        currentLine[2]

                                )

                        );
                        m_indices.add(

                                readOBJIndex(

                                        currentLine[3]

                                )

                        );
                    }
                }
            break;
            }//end of the switch

            closeFile(fileIn);


            ///////////////////////////////////////////////////
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.exit(99);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }//end of the constructor

        public IndexModel toIndexModel(){

             IndexModel result = new IndexModel();
             IndexModel normalModel = new IndexModel();
             HashMap<OBJindex, Integer> resultIndexMap = new HashMap<>();
             HashMap<Integer, Integer> normalIndexMap = new HashMap<>();
             HashMap<Integer,Integer> indexMap = new HashMap<>();


            for (OBJindex currentIndex : m_indices) {

                vec3 currentVertex = m_vertices.get(currentIndex.getVertexIndex()
                );
                vec2 currentTextures;
                vec3 currentNormal;

                if (m_hasTextures) {

                    currentTextures = m_textures.get(currentIndex.getTextureIndex());

                } else {

                    currentTextures = new vec2(
                            0.0f,
                            0.0f
                    );

                }

                if (m_hasNormals) {

                    currentNormal = m_normals.get(currentIndex.getNormalIndex());

                } else {

                    currentNormal = new vec3(

                            0.0f,
                            0.0f,
                            0.0f

                    );

                }

                Integer vertexModelIndex = resultIndexMap.get(currentIndex);

                if (vertexModelIndex == null) {

                    vertexModelIndex = result.getVertices().size();

                    resultIndexMap.put(
                            currentIndex,
                            vertexModelIndex
                    );

                    result.getVertices().add(currentVertex);

                    result.getTextures().add(currentTextures);

                    if (m_hasNormals) {


                        result.getNormals().add(

                                currentNormal

                        );

                    }
                }

                Integer normalModelIndex = normalIndexMap.get(

                        currentIndex.getVertexIndex()

                );

                if (normalModelIndex == null) {

                    normalModelIndex = normalModel.getVertices().size();

                    normalIndexMap.put(
                            currentIndex.getVertexIndex(),

                            normalModelIndex

                    );

                    normalModel.getVertices().add(

                            currentVertex

                    );
                    normalModel.getTextures().add(
                            currentTextures
                    );

                    normalModel.getNormals().add(

                            currentNormal

                    );

                    normalModel.getTangents().add(

                            new vec3(

                                    0.0f,
                                    0.0f,
                                    0.0f

                            )

                    );

                }

                result.getIndices().add(
                        vertexModelIndex
                );

                normalModel.getIndices().add(

                        normalModelIndex

                );

                indexMap.put(

                        vertexModelIndex,
                        normalModelIndex

                );

            } // end of main loop

            if(!m_hasNormals){
                for(int i = 0; i < result.getVertices().size(); i++){

                    result.getNormals().add(

                            normalModel.getNormals().get(
                                    indexMap.get(
                                            i
                                    )
                            )

                    );

                }
            }

            normalModel.calculateTangents();

            for(int i = 0; i < result.getVertices().size(); i++){

                result.getTangents().add(
                        normalModel.getTangents().get(
                                indexMap.get(
                                        i
                                )
                        )
                );
            }

            return result;
         }// end of Method


             /////////////////////Private/////////////////////
    private String readline( BufferedReader reader) throws IOException {
        String line;

        line = reader.readLine();

        return line;
    }

    private void closeFile(BufferedReader fileIn){
        try{

            fileIn.close();

        }catch (IOException e){

            e.printStackTrace();

        }

    }
    private String[] removeEmptyStrings(String[] data){
        ArrayList<String> result = new ArrayList<>();

        int index = 0;
        while (index <data.length) {

            if(!data[index].equals("")){            //check the data is not equal to empty string

                result.add(//add ro result the contents of data

                        data[index]

                );
            }
            index++;
        }

        String[] res = new String[result.size()];

        result.toArray(             //convert arrayList back to an Array
                res
        );
        return res;
    }

    private OBJindex readOBJIndex(String data){

        OBJindex result = new OBJindex();

        String[] values = data.split(
                "/"                 //split the data by /
        );

        result.setVertexIndex(
                Integer.parseInt(          //covert to int
                        values[0]          //string holding the calues
                ) -  1                     //obj files stat counting from 1 (start at 0)
        );

        if(values.length > 1){

            if(values[1].isEmpty()){

                m_hasTextures = true;      //texture index has been found

                result.setTextureIndex(

                        Integer.parseInt(
                                values[1]
                        ) -1

                );

            }

            if(values.length > 2){

                m_hasNormals = true;        //normals Index has been found


                result.setNormalIndex(

                        Integer.parseInt(
                                values[2]
                        ) -1

                );
            }

        }

        return result;
    }

}
