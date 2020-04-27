package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class AStarView {
    private GridPane startView;
    private final AStarGraph model;
    Button exitButton = new Button("Exit");
    Label startVertexLbl = new Label("Select start vertex: ");
    ComboBox<Vertex> startVertexCombobox = new ComboBox<Vertex>();
    //Button HeuristicsButton = new Button("????"); // Make button to change heuristics
    Label endVertexLbl = new Label("Select destination: ");
    ComboBox<Vertex> endVertexCombobox = new ComboBox<Vertex>();
    Button printButton = new Button("Print shortest path to destination: ");
    TextArea shortestPathTA = new TextArea();

    public AStarView(AStarGraph graphModel){
        this.model = graphModel;
        startView = new GridPane();
        startView.setMinSize(300,200);
        startView.setPadding(new Insets(10, 10, 10, 10));
        startView.setVgap(5);
        startView.setHgap(1);

        ObservableList<Vertex> vertexList = FXCollections.observableArrayList(model.vertices);

        Callback<ListView<Vertex>, ListCell<Vertex>> VertexcellFactory = new Callback<ListView<Vertex>, ListCell<Vertex>>() {
            @Override
            public ListCell<Vertex> call(ListView<Vertex> vertexListView) {
                return new ListCell<Vertex>() {
                    @Override
                    protected void updateItem(Vertex vertex, boolean empty) {
                        super.updateItem(vertex, empty); // to call method from parent class
                        if (vertex == null || empty) {
                            setText(null);
                        } else {
                            setText(vertex.getid());
                        }
                    }
                };
            }
        };
        startVertexCombobox.setItems(vertexList);
        startVertexCombobox.setButtonCell(VertexcellFactory.call(null));
        startVertexCombobox.setCellFactory(VertexcellFactory);
        startVertexCombobox.setValue(model.vertices.get(0));

        endVertexCombobox.setItems((vertexList));
        endVertexCombobox.setButtonCell(VertexcellFactory.call(null));
        endVertexCombobox.setCellFactory(VertexcellFactory);
        endVertexCombobox.setValue(model.vertices.get(0));

        shortestPathTA.setPrefColumnCount(1);
        // Add controls to pane
        startView.add(startVertexLbl,1,1);
        startView.add(startVertexCombobox,15,1);
        //startView.add(HeuristicsButton,15,2);
        startView.add(endVertexLbl,1,3);
        startView.add(endVertexCombobox,15,3);
        startView.add(printButton,15,4);
        startView.add(shortestPathTA,15,5);
        startView.add(exitButton, 20, 6);
    }

    public Parent asParent(){
        return startView;
    }
}
