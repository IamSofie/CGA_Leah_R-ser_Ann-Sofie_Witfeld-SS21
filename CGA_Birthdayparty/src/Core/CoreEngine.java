package Core;
import java.lang.Thread;

import Rendering.MeshLoader.Window3D;
import Rendering.RenderEngine;

import static Rendering.MeshLoader.Window3D.DisplayIsOpen;

public class CoreEngine extends Window3D {

    private CoreBaseGame m_game;
    private RenderEngine m_renderEngine;
    private int m_width;
    private int m_height;
    private double m_frameTime;

    public CoreEngine(CoreBaseGame m_game, double desiredFrameRate){

        this.m_game = m_game;
        this.m_frameTime = 1.0 / desiredFrameRate;
    }

    public void CreateWindow(int width, int height, String title){
         this.m_width = width;
         this.m_height = height;

         createDisplay(
                 m_width,
                 m_height,
                 title
         );

         this.m_renderEngine = new RenderEngine();
    }

    public void Start(){

        Run();
    }
    public RenderEngine getRenderEngine(){

        return m_renderEngine;
    }

    //////////////Private////////////////

    private void Run(){
        int frames =0;
        double frameCounter = 0;
        double lastTime = Time.GetTime();
        double unprocessedTime =0;

        m_game.Init();

        while(DisplayIsOpen()){
            boolean render = false;
            double startTime = Time.GetTime();
            double deltaTime = startTime - lastTime;
            lastTime = startTime;
            unprocessedTime += deltaTime;
            frameCounter += deltaTime;

            while(unprocessedTime > m_frameTime){
                render = true;
                unprocessedTime -= m_frameTime;

                //game logic

                m_game.Input(
                        (float)m_frameTime
                );

                m_game.Update(
                        (float)m_frameTime
                );

                if (frameCounter >= 1.0){

                    System.out.println(frames);

                    frames = 0;
                    frameCounter = 0;
                }

                //render
                if(render){
                    m_game.Render(
                            m_renderEngine
                    );
                    m_game.Render();//test only

                    updateDisplay();

                    frames ++;
                }
                else{

                    try{

                        Thread.sleep(
                                1
                        );
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }//end of the main while loop


            CleanUp();
        }

        private void CleanUp(){
            closeDisplay();
        }
    }

}
