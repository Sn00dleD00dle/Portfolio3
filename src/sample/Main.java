package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AStarGraph graphModel = createGraph();
        AStarView AStarView = new AStarView(graphModel);
        Controller controller = new Controller(graphModel, AStarView);
        primaryStage.setTitle("AStar Graph");
        primaryStage.setScene(new Scene(AStarView.asParent(), 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public AStarGraph createGraph() {
        AStarGraph MyMaze = new AStarGraph();
        Vertex A = new Vertex("A", 0, 4);
        Vertex B = new Vertex("B", 1, 7);
        Vertex C = new Vertex("C", 4, 0);
        Vertex D = new Vertex("D", 3, 7);
        Vertex E = new Vertex("E", 3, 3);
        Vertex F = new Vertex("F", 6, 6);
        Vertex G = new Vertex("G", 7, 2);
        Vertex H = new Vertex("H", 8, 7);
        Vertex I = new Vertex("I", 9, 2);
        Vertex J = new Vertex("J", 11, 5);

        MyMaze.addvertex(A);
        MyMaze.addvertex(B);
        MyMaze.addvertex(C);
        MyMaze.addvertex(D);
        MyMaze.addvertex(E);
        MyMaze.addvertex(F);
        MyMaze.addvertex(G);
        MyMaze.addvertex(H);
        MyMaze.addvertex(I);
        MyMaze.addvertex(J);
        MyMaze.newconnection(A, B, 3.41);
        MyMaze.newconnection(A, C, 6.82);
        MyMaze.newconnection(B, D, 2.00);
        MyMaze.newconnection(C, G, 4.41);
        MyMaze.newconnection(C, I, 4.82);
        MyMaze.newconnection(D, E, 4.00);
        MyMaze.newconnection(E, F, 6.23);
        MyMaze.newconnection(F, G, 4.41);
        MyMaze.newconnection(F, H, 3.82);
        MyMaze.newconnection(G, H, 5.41);
        MyMaze.newconnection(G, I, 2.82);
        MyMaze.newconnection(H, J, 4.41);
        MyMaze.newconnection(I, J, 3.82);
        return MyMaze;
    }

}
