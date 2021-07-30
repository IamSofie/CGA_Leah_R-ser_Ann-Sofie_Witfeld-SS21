import Core.CoreEngine;

public class Main {


    public static void main(String[] args){
        System.out.println("Welcome to my Program");


        CoreEngine engine = new CoreEngine(
                new TestGame(),
                60
        );

        engine.CreateWindow(
                800,
                600,
                "Birthdayparty3D"
        );


        engine.Start();








    }
}
