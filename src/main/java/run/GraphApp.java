package run;

import grafos.grafosfinal.Grafo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Grafo;
import model.Nodo;

import java.util.ArrayList;
import java.util.List;

public class GraphApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the graph
        Grafo grafo = new Grafo();

        // Create the graph pane
        Pane graphPane = new Pane();

        // Add click event handler to the graph pane
        graphPane.setOnMouseClicked(event -> {
            // Get the coordinates of the click event
            double x = event.getX();
            double y = event.getY();

            // Create a new node at the clicked coordinates
            Nodo newNode = new Nodo("Node" + (grafo.getNodes().size() + 1), x, y);
            grafo.addNode(newNode);

            // Add the node to the graph pane
            Circle circle = new Circle(x, y, 10);
            graphPane.getChildren().add(circle);
        });

        // Create the scene
        Scene scene = new Scene(graphPane, 400, 300);

        // Set the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
